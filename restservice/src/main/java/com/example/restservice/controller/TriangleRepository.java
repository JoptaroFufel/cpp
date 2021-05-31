package com.example.restservice.controller;

import org.springframework.data.repository.CrudRepository;

public interface TriangleRepository extends CrudRepository<TriangleEntity, Integer> {
    TriangleEntity findOneByNum1AndNum2AndNum3(Integer num1, Integer num2, Integer num3);
}
