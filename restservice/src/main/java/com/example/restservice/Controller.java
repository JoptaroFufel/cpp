package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public Triangle GetParams(@RequestParam(value = "num1", defaultValue = "0") Integer num1,
                            @RequestParam(value = "num2", defaultValue = "0") Integer num2,
                            @RequestParam(value = "num3", defaultValue = "0") Integer num3) {
        return new Triangle(num1,num2,num3);
    }

}