package com.example.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AmortizedLoanTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/div.csv", numLinesToSkip = 1)
    void testNormalCase() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 6, 10);
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1110.21)));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/div.csv", numLinesToSkip = 1)
    void testZeroLoan() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.ZERO, 6, 10);
        assertEquals(BigDecimal.ZERO.setScale(2), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/div.csv", numLinesToSkip = 1)
    void testZeroInterest() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 0, 10);
        assertEquals(BigDecimal.ZERO.setScale(2), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/div.csv", numLinesToSkip = 1)
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(-1000), 5, 10));
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), -5, 10));
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 5, 0));
    }
}
