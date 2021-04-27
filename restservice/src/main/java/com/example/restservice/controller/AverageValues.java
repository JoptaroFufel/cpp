package com.example.restservice.controller;

import java.util.LinkedList;
import java.util.List;

public class AverageValues {
    private List<Triangle> TriangleAnswerList = new LinkedList<>();
    private double numAverage1;
    private double numAverage2;
    private double numAverage3;

    public void calcAverage(List<Triangle> TriangleList){
        numAverage1 = TriangleList.stream().
                mapToInt(Triangle::getNum1).average().getAsDouble();
        numAverage2 = TriangleList.stream().
                mapToInt(Triangle::getNum2).average().getAsDouble();
        numAverage3 = TriangleList.stream().
                mapToInt(Triangle::getNum3).average().getAsDouble();
    }

    public AverageValues() {
    }

    public List<Triangle> getTriangleAnswerList() {
        return TriangleAnswerList;
    }

    public void setTriangleAnswerList(List<Triangle> triangleAnswerList) {
        TriangleAnswerList = triangleAnswerList;
    }

    public double getNumAverage1() {
        return numAverage1;
    }

    public void setNumAverage1(double numAverage1) {
        this.numAverage1 = numAverage1;
    }

    public double getNumAverage2() {
        return numAverage2;
    }

    public void setNumAverage2(double numAverage2) {
        this.numAverage2 = numAverage2;
    }

    public double getNumAverage3() {
        return numAverage3;
    }

    public void setNumAverage3(double numAverage3) {
        this.numAverage3 = numAverage3;
    }
    public AverageValues(double numAverage1, double numAverage2, double numAverage3) {
        this.numAverage1 = numAverage1;
        this.numAverage2 = numAverage2;
        this.numAverage3 = numAverage3;
    }


}
