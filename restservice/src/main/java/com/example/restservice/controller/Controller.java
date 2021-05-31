package com.example.restservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CounterService counterService;
    @Autowired
    private TriangleService triangleService;
    @Autowired
    private Cache cache;
    @Autowired
    private DatabaseService databaseService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/result")
    public ResponseEntity<Object> GetParams(@RequestParam(value = "num1", defaultValue = "0") Integer num1,
                                              @RequestParam(value = "num2", defaultValue = "0") Integer num2,
                                              @RequestParam(value = "num3", defaultValue = "0") Integer num3) {
        counterService.incrementCounter();
        Triangle triangle = triangleService.getTriangle(num1, num2, num3);
        triangle.TriangleCalc();
        if(!databaseService.isStored(num1, num2, num3)){
            databaseService.insertTriangle((triangle));
            return new ResponseEntity<>(triangle, HttpStatus.OK);
        }
        return new ResponseEntity<>(databaseService.getTriangle(num1, num2, num3), HttpStatus.OK);
    }

    @GetMapping("/counter")
    public ResponseEntity<Object> getCounter() {
        return new ResponseEntity<>(counterService.getCounter(), HttpStatus.OK);
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
            if (databaseService.isStored(triangle.getNum1(), triangle.getNum2(), triangle.getNum3())) {
                triangle.setAnswer(databaseService.getTriangle(triangle.getNum1(), triangle.getNum2(), triangle.getNum3()).getAnswer());
            }
            else {
                triangle.TriangleCalc();
                logger.info("Successfully compute");
                databaseService.insertTriangle(triangle);
            }
        });

        values.setTriangleAnswerList(TriangleList);
        values.calcAverage(TriangleList);

        counterService.incrementCounter();
        return new ResponseEntity<>(values, HttpStatus.OK);
    }


}