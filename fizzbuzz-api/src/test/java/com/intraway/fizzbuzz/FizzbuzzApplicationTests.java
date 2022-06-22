package com.intraway.fizzbuzz;

import com.intraway.fizzbuzz.exception.InvalidParameterException;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.service.IOperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.intraway.fizzbuzz.utils.Constants.*;


@SpringBootTest
class FizzbuzzApplicationTests {

    final IOperationService operationService;

    @Autowired
    FizzbuzzApplicationTests(final IOperationService operationService) {
        this.operationService = operationService;
    }

    @Test
    @DisplayName("Check Both")
    void checkFizzBuzz() throws InvalidParameterException {
        final Operation operation = this.operationService.performOperation(ONE, FIFTEEN);
        Assertions.assertTrue(operation.getList().contains(String.format("%s%s", FIZZ, BUZZ)));
        Assertions.assertEquals(BOTH_MESSAGE, operation.getDescription());
    }

    @Test
    @DisplayName("Check only Fizz")
    void checkFizz() throws InvalidParameterException {
        final Operation operation = this.operationService.performOperation(ONE, THREE);
        Assertions.assertTrue(operation.getList().contains(FIZZ));
        Assertions.assertEquals(THREE_MESSAGE, operation.getDescription());
    }

    @Test
    @DisplayName("Check only Buzz")
    void checkBuzz() throws InvalidParameterException {
        final Operation operation = this.operationService.performOperation(FOUR, FIVE);
        Assertions.assertTrue(operation.getList().contains(BUZZ));
        Assertions.assertEquals(FIVE_MESSAGE, operation.getDescription());
    }

    @Test()
    @DisplayName("Check throw exception")
    void checkException() {
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            this.operationService.performOperation(FIVE, THREE);
        });
    }

}
