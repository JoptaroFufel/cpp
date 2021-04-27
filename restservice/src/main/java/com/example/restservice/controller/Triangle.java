package com.example.restservice.controller;

import java.util.Objects;

public class Triangle {

    public Integer num1;
    public Integer num2;
    public Integer num3;
    public String answer;
    String Equilateral;
    String Isosceles;
    String Rectangular;
    String in1 = "no";
    String in2 = "no";
    String in3 = "no";
    Integer num4;

    public Triangle(Integer num1, Integer num2, Integer num3) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;


    }

    public Triangle(Integer num1, Integer num2, Integer num3, String str) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;

        Equilateral = in1;
        Isosceles = in2;
        Rectangular = in3;

        answer = str;
    }


    public void TriangleCalc(){
        if(this.num1.equals(this.num2)){
            in2 = "yes";
            if(this.num1.equals(this.num3))
                in1 = "yes";
        }
        else
        if(this.num1.equals(this.num3))
            in2 = "yes";
        else if(this.num2.equals(this.num3))
            in2 = "yes";
        if(in1.equals("no")){
            this.num4 = this.num1 * this.num1 + this.num2 * this.num2;
            if(this.num4 == this.num3 * this.num3)
                in3 = "yes";
            else{
                this.num4 = this.num1 * this.num1 + this.num3 * this.num3;
                if(this.num4 == this.num2 * this.num2)
                    in3 = "yes";
                else{
                    this.num4 = this.num2 * this.num2 + this.num3 * this.num3;
                    if(this.num4 == this.num1 * this.num1)
                        in3 = "yes";
                }
            }
        }

        Equilateral = in1;
        Isosceles = in2;
        Rectangular = in3;

        answer="Equilateral: " + in1 + ", Isosceles: " + in2 + ", Rectangular: " + in3;
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

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(num1, triangle.num1) && Objects.equals(num2, triangle.num2) && Objects.equals(num3, triangle.num3) && Objects.equals(answer, triangle.answer) && Objects.equals(Equilateral, triangle.Equilateral) && Objects.equals(Isosceles, triangle.Isosceles) && Objects.equals(Rectangular, triangle.Rectangular) && Objects.equals(in1, triangle.in1) && Objects.equals(in2, triangle.in2) && Objects.equals(in3, triangle.in3) && Objects.equals(num4, triangle.num4);
    }

}