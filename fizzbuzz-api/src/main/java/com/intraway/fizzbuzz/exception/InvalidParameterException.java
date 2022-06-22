package com.intraway.fizzbuzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends Exception {
    public InvalidParameterException(final String message) {
        super(message);
    }
}
