package com.intraway.fizzbuzz.service;

import com.intraway.fizzbuzz.exception.InvalidParameterException;
import com.intraway.fizzbuzz.model.Operation;

public interface IOperationService {

    Operation performOperation(final int firstNumber, final int secondNumber) throws InvalidParameterException;
}
