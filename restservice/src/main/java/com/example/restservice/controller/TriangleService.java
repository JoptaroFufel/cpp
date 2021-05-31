package com.example.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TriangleService {

    @Autowired
    private Cache cache;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Triangle getTriangle(int num1, int num2, int num3)
        throws ResponseStatusException {
        validateParameters(num1, num2, num3);

        String s = num1 + " " + num2 + " " + num3;
        if (cache.isStored(s)) {
            logger.info("Value restored from cache: " + num1 + " " + num2 + " " + num3);
            return new Triangle(num1, num2, num2, cache.get(s));
        }

        Triangle triangleObj;
        try {
            triangleObj = new Triangle(num1, num2, num3);
            triangleObj.TriangleCalc();
        } catch(RuntimeException ex) {
            String errorMessage =
                    "Computation error";
            logger.error(errorMessage);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }

        cache.insert(s, triangleObj.getAnswer());

        logger.info("Successfully compute");

        return triangleObj;
    }

    private void validateParameters(int num1, int num2, int num3) throws ResponseStatusException {
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
    }
}
