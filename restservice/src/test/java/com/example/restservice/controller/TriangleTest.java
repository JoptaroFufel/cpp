package com.example.restservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TriangleTest /*extends ControllerTest*/ {

    @Test
    void TriangleEquilateral(){
        Triangle obj = new Triangle(1,1,1);
        Assertions.assertEquals(obj.Equilateral,"yes");
        Assertions.assertEquals(obj.Isosceles,"yes");
        Assertions.assertEquals(obj.Rectangular,"no");
    }

    @Test
    void TriangleRectangular(){
        Triangle obj = new Triangle(3,4,5);
        Assertions.assertEquals(obj.Equilateral,"no");
        Assertions.assertEquals(obj.Isosceles,"no");
        Assertions.assertEquals(obj.Rectangular,"yes");
    }

    @Test
    void TriangleIsosceles(){
        Triangle obj = new Triangle(2,2,1);
        Assertions.assertEquals(obj.Equilateral,"no");
        Assertions.assertEquals(obj.Isosceles,"yes");
        Assertions.assertEquals(obj.Rectangular,"no");
    }

    @Test
    void TriangleError1(){
        Triangle obj = new Triangle(2,2,0);
        Assertions.assertNotEquals(obj.Equilateral,"yes");
        Assertions.assertNotEquals(obj.Isosceles,"no");
        Assertions.assertNotEquals(obj.Rectangular,"no");
    }
}
