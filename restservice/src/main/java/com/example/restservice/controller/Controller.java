package com.example.restservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Cache cache;

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/result")
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

    @PostMapping("/result")
    public ResponseEntity<?> bulkParams(@RequestBody List<IntermediateBody> bodyList) {
        if (bodyList.isEmpty()) {
            return new ResponseEntity<>("400 error", HttpStatus.BAD_REQUEST);
        }
        //invalid input check
        List<Triangle> TriangleList = new LinkedList<>();
        for (IntermediateBody tmp : bodyList) {
            try {
                Triangle temper = new Triangle(Integer.parseInt(tmp.getIn1().trim()),
                        Integer.parseInt(tmp.getIn2().trim()),
                        Integer.parseInt(tmp.getIn3().trim()));
                temper.TriangleCalc();
                TriangleList.add(temper);
            } catch (Exception e) {
                return new ResponseEntity<>("400 error", HttpStatus.BAD_REQUEST);
            }

        }
        //triangle save
        AverageValues values = new AverageValues();
        TriangleList.forEach((triangle) -> {
            String s = triangle.getNum1() + " " + triangle.getNum2() + " " + triangle.getNum3();
            if (cache.isStored(s)) {
                logger.info("Value restored from cache: " + triangle.getNum1() + " " + triangle.getNum2() + " " + triangle.getNum3());
                triangle.setAnswer(cache.get(s));
            }
            else {
                triangle.TriangleCalc();
                logger.info("Successfully compute");
                cache.insert(s, triangle.getAnswer());
            }
        });

        values.setTriangleAnswerList(TriangleList);
        values.calcAverage(TriangleList);

        return new ResponseEntity<>(values, HttpStatus.OK);
    }


}