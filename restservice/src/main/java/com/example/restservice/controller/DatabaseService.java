package com.example.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.Semaphore;

@Service
public class DatabaseService {
    Semaphore semaphore = new Semaphore(1);
    @Autowired
    TriangleRepository triangleRepository;

    @Autowired
    Cache cache;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Triangle getTriangle(int num1, int num2, int num3) throws ResponseStatusException{
        TriangleEntity triangleEntity;
        try{
            semaphore.acquire();
            triangleEntity = triangleRepository.findOneByNum1AndNum2AndNum3(num1,num2,num3);
        }catch(InterruptedException e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }finally{
            semaphore.release();
        }
        if(triangleEntity == null){
            String errorMessage = "Trying get non-existing triangle from database";
            logger.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }

        logger.info("Value restored from database: " + String.valueOf(num1) + " " + String.valueOf(num2) + " " + String.valueOf(num3));

        return new Triangle(num1,num2,num3,triangleEntity.getAnswer());
    }

    public boolean isStored(int num1, int num2, int num3){
        TriangleEntity triangleEntity;
        try{
            semaphore.acquire();
            triangleEntity = triangleRepository.findOneByNum1AndNum2AndNum3(num1,num2,num3);
        }catch(InterruptedException e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }finally{
            semaphore.release();
        }

        return triangleEntity != null;
    }

    public void insertTriangle(Triangle triangle){
        try {
            semaphore.acquire();
            triangleRepository.save(new TriangleEntity(triangle));
            logger.info("Value inserted to database: " + String.valueOf(triangle.getNum1())
                    + " " + String.valueOf(triangle.getNum2()) + " " + String.valueOf(triangle.getNum3()));
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}
