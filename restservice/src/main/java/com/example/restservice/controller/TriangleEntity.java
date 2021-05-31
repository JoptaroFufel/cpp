package com.example.restservice.controller;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TriangleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer num1;
    private Integer num2;
    private Integer num3;
    private String answer;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public void setNum3(Integer num3) {
        this.num3 = num3;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public TriangleEntity() {}

    public TriangleEntity(int num1, int num2, int num3, String answer) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.answer = answer;
    }

    public TriangleEntity(Triangle triangle) {
        this.num1 = triangle.getNum1();
        this.num2 = triangle.getNum2();
        this.num3 = triangle.getNum3();
        this.answer = triangle.getAnswer();
    }


    public Integer getId() {
        return id;
    }

    public Integer getNum1() {
        return num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public Integer getNum3() {
        return num3;
    }

    public String getAnswer() {
        return answer;
    }
}
