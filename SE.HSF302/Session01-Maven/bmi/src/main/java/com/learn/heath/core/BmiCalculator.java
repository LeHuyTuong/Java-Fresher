package com.learn.heath.core;

public class BmiCalculator {
    // ham statis tinh chi so khoi luong co the
    // bmi = can nang / chieu cao binh phuong
    public BmiCalculator(){

    }
    public double getBmi(double weight, double height) {
        return weight / (height * height);
    }
}
