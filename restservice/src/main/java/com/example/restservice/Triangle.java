package com.example.restservice;

public class Triangle {

    public Integer num1;
    public Integer num2;
    public Integer num3;
    public String in1="no";
    public String in2="no";
    public String in3="no";
    Integer num4;
    Integer ok = 0;

    public Triangle(Integer num1, Integer num2, Integer num3) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;

        if(this.num1<=0 || this.num2<=0 || this.num3<=0) ok=1;
        if(this.num1+this.num2==this.num3 || this.num1+this.num3==this.num2 || this.num2+this.num3==this.num1) ok=1;

        if(ok==0)
        if(this.num1==this.num2){
            in2 = "yes";
            if(this.num1==this.num3)
                in1 = "yes";
        }
        else
            if(this.num1==this.num3)
                in2 = "yes";
            else if(this.num2==this.num3)
                    in2 = "yes";

        if(in1=="no"){
            this.num4=this.num1*this.num1+this.num2*this.num2;
            if(this.num4==this.num3*this.num3)
                in3="yes";
            else{
                this.num4=this.num1*this.num1+this.num3*this.num3;
                if(this.num4==this.num2*this.num2)
                    in3="yes";
                else{
                    this.num4=this.num2*this.num2+this.num3*this.num3;
                    if(this.num4==this.num1*this.num1)
                        in3="yes";
                }
            }
        }
    }

}