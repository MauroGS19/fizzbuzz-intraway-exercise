package com.intraway.fizzbuzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class FizzBuzzExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<BadRequest> exceptionHandler(final Exception ex, final WebRequest request) {
        BadRequest errorDetails = new BadRequest();
        if (ex instanceof InvalidParameterException) {
            errorDetails = new BadRequest(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI());
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
