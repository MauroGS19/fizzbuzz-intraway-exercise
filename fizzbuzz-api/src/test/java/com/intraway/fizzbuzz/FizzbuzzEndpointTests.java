package com.intraway.fizzbuzz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intraway.fizzbuzz.exception.BadRequest;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.service.IOperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.IOException;
import java.io.InputStream;

import static com.intraway.fizzbuzz.utils.Constants.ONE;

@AutoConfigureWebTestClient(timeout = "20000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FizzbuzzEndpointTests {

    final IOperationService operationService;

    private final ObjectMapper objectMapper;

    private WebTestClient webClient;

    private static final String SUCCESS_JSON = "api/success.json";

    private static final String ERROR_JSON = "api/error.json";

    private static final String SUCCESS_ENDPOINT = "intraway/v1/fizzbuzz/1/100";

    private static final String ERROR_ENDPOINT = "intraway/v1/fizzbuzz/100/1";

    @Autowired
    FizzbuzzEndpointTests(final IOperationService operationService, final ObjectMapper objectMapper, final WebTestClient webClient) {
        this.operationService = operationService;
        this.objectMapper = objectMapper;
        this.webClient = webClient;
    }

    @Test()
    @DisplayName("Check success endpoint")
    void checkEndpoint() throws IOException {
        final ClassPathResource requestResource = new ClassPathResource(SUCCESS_JSON);
        final BodyInserter<ClassPathResource, ReactiveHttpOutputMessage> body = BodyInserters.fromResource(requestResource);
        final WebTestClient.ResponseSpec spec = webClient.method(HttpMethod.GET).uri(SUCCESS_ENDPOINT).contentType(MediaType.APPLICATION_JSON).body(body).exchange();
        spec.expectStatus().isOk().expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON).expectBody(Operation.class);
        final Operation response = getResponse(spec, Operation.class);
        response.setId((long) ONE);
        response.setTimestamp(null);
        compareJsonResult(SUCCESS_JSON, response);
    }

    @Test()
    @DisplayName("Check exception endpoint")
    void checkExceptionEndpoint() throws IOException {
        final ClassPathResource requestResource = new ClassPathResource(ERROR_JSON);
        final BodyInserter<ClassPathResource, ReactiveHttpOutputMessage> body = BodyInserters.fromResource(requestResource);
        final WebTestClient.ResponseSpec spec = webClient.method(HttpMethod.GET).uri(ERROR_ENDPOINT).contentType(MediaType.APPLICATION_JSON).body(body).exchange();
        spec.expectStatus().isBadRequest().expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON).expectBody(BadRequest.class);
        final BadRequest response = getResponse(spec, BadRequest.class);
        response.setTimestamp(null);
        compareJsonResult(ERROR_JSON, response);
    }

    public <T> void compareJsonResult(final String responsePath, final T result) throws IOException {
        Assertions.assertEquals(objectMapper.readTree(getInputStream(responsePath)).toString(), objectMapper.valueToTree(result).toString());
    }

    private InputStream getInputStream(final String requestPath) throws IOException {
        return (new ClassPathResource(requestPath)).getInputStream();
    }

    public <T> T getResponse(final WebTestClient.ResponseSpec spec, final Class<T> type) throws IOException {
        final byte[] response = spec.returnResult(type).getResponseBodyContent();
        Assertions.assertNotNull(response);
        return objectMapper.readValue(response, type);
    }
}
