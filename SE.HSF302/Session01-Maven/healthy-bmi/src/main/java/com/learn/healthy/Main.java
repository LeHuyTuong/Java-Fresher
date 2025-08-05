package com.learn.healthy;

import com.learn.heath.core.BmiCalculator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("BMI 70kg 1.7m: " +
                new BmiCalculator().getBmi(70, 1.7));
    }
}