package com.example.junit5;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LoanCalculator {

        public static BigDecimal calculateMonthlyPayment(BigDecimal principal, double annualInterestRate, int years) {
            // Validate input
            // lãi âm và năm âm thì lỗi
            if (principal.compareTo(BigDecimal.ZERO) < 0 || annualInterestRate < 0 || years <= 0) {
                throw new IllegalArgumentException("Invalid input values");
            }

            // Trường hợp vay hoặc lãi = 0
            if (principal.compareTo(BigDecimal.ZERO) == 0 || annualInterestRate == 0) {
                return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            }

            // tỉ lệ % lời mỗi tháng từ tổng lãi trên năm
            double monthlyRate = annualInterestRate / 100 / 12;
            // số năm vay thành tổng số tháng
            int totalMonths = years * 12;

            // Tính tử số: r * (1+r)^n
            double numerator = monthlyRate * Math.pow(1 + monthlyRate, totalMonths);

            // Tính mẫu số: (1+r)^n - 1
            double denominator = Math.pow(1 + monthlyRate, totalMonths) - 1;

            // Tính kết quả cuối: L * (tử số / mẫu số)
            double monthlyPayment = principal.doubleValue() * (numerator / denominator);

            return BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.HALF_UP);
        }
    }
