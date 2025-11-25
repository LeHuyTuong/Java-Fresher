package com.example.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private final Calculator calc = new Calculator();
    private static final double EPS = 1e-9; // tolerance cho số thực

    @ParameterizedTest
    @CsvFileSource(resources = "/add.csv", numLinesToSkip = 1)
    @DisplayName("CSV: add")
    void add_fromCsv(double a, double b, double expected) {
        assertEquals(expected, calc.add(a, b), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sub.csv", numLinesToSkip = 1)
    @DisplayName("CSV: subtract")
    void subtract_fromCsv(double a, double b, double expected) {
        assertEquals(expected, calc.subtract(a, b), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mul.csv", numLinesToSkip = 1)
    @DisplayName("CSV: multiply")
    void multiply_fromCsv(double a, double b, double expected) {
        assertEquals(expected, calc.multiply(a, b), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/div.csv", numLinesToSkip = 1)
    @DisplayName("CSV: divide")
    void divide_fromCsv(double a, double b, double expected) {
        assertEquals(expected, calc.divide(a, b), EPS);
    }

    @Test
    @DisplayName("Divide by zero should throw")
    void divideByZero_throws() {
        assertThrows(ArithmeticException.class, () -> calc.divide(1, 0));
    }
}
