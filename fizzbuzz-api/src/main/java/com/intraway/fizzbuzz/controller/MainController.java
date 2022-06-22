package com.intraway.fizzbuzz.controller;

import com.intraway.fizzbuzz.exception.InvalidParameterException;
import com.intraway.fizzbuzz.model.Operation;
import com.intraway.fizzbuzz.service.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("intraway/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    @Autowired
    private IOperationService operationService;

    public MainController(){}

    @RequestMapping(value = "fizzbuzz/{first}/{second}", method = RequestMethod.GET)
    public Operation get(@PathVariable int first, @PathVariable int second) throws InvalidParameterException {
        return operationService.performOperation(first, second);
    }
}
