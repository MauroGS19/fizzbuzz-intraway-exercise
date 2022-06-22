package com.intraway.fizzbuzz.service;

import com.intraway.fizzbuzz.exception.InvalidParameterException;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.repository.IOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.intraway.fizzbuzz.utils.Constants.*;

@Service
public class OperationServiceImpl implements IOperationService {

    private final IOperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(final IOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation performOperation(final int firstNumber, final int secondNumber) throws InvalidParameterException {
        if (firstNumber > secondNumber) {
            throw new InvalidParameterException(INVALID_PARAMETER_EXCEPTION);
        }
        final Operation operation = this.makeFizzBuzz(firstNumber, secondNumber);
        this.operationRepository.save(operation);
        return operation;
    }

    private Operation makeFizzBuzz(final int first, final int second) {
        final Operation operation = new Operation();
        final ArrayList<String> tempList = new ArrayList<>();
        boolean multipleOfThree = false;
        boolean multipleOfFive = false;
        for (int i = first; i <= second; i++) {
            if (i % THREE == ZERO && i % FIVE == ZERO) {
                tempList.add(String.format("%s%s", FIZZ, BUZZ));
                multipleOfThree = true;
                multipleOfFive = true;
            } else if (i % THREE == ZERO) {
                multipleOfThree = true;
                tempList.add(FIZZ);
            } else if (i % FIVE == ZERO) {
                tempList.add(BUZZ);
                multipleOfFive = true;
            } else {
                tempList.add(String.valueOf(i));
            }
            operation.setList(tempList);
            if (multipleOfThree && multipleOfFive) {
                operation.setDescription(BOTH_MESSAGE);
            } else if (multipleOfThree) {
                operation.setDescription(THREE_MESSAGE);
            } else if (multipleOfFive) {
                operation.setDescription(FIVE_MESSAGE);
            } else {
                operation.setDescription(NOTHING_FOUND);
            }
        }
        return operation;
    }
}
