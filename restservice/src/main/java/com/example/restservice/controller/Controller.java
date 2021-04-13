package com.example.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RestController
public class Controller {

    @Autowired
    private Cache cache;

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/")
    public Triangle GetParams(@RequestParam(value = "num1", defaultValue = "0") Integer num1,
                              @RequestParam(value = "num2", defaultValue = "0") Integer num2,
                              @RequestParam(value = "num3", defaultValue = "0") Integer num3) {
        if(num1 < 0 || num2 <=0 || num3 < 0) {
            String errorMessage = "Invalid data";
            logger.error(errorMessage);
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        if(num1 + num2 == num3 || num1 + num3 == num2 || num2 + num3 == num1){
            String errorMessage = "Not a triangle";
            logger.error(errorMessage);
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST, errorMessage);
        }
        return new Triangle(num1,num2,num3);
    }

}