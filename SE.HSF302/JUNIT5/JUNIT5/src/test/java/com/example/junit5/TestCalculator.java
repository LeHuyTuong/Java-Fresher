package com.example.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
public class TestCalculator {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Nested
    @DisplayName("Addition Tests")
    class AdditionTests {
        
        @Test
        @DisplayName("Add two positive numbers")
        void testAddPositiveNumbers() {
            assertEquals(5.0, calculator.add(2.0, 3.0), 0.001);
        }
        
        @Test
        @DisplayName("Add two negative numbers")
        void testAddNegativeNumbers() {
            assertEquals(-5.0, calculator.add(-2.0, -3.0), 0.001);
        }
        
        @Test
        @DisplayName("Add positive and negative numbers")
        void testAddPositiveAndNegative() {
            assertEquals(1.0, calculator.add(3.0, -2.0), 0.001);
            assertEquals(-1.0, calculator.add(-3.0, 2.0), 0.001);
        }
        
        @Test
        @DisplayName("Add with zero")
        void testAddWithZero() {
            assertEquals(5.0, calculator.add(5.0, 0.0), 0.001);
            assertEquals(5.0, calculator.add(0.0, 5.0), 0.001);
            assertEquals(0.0, calculator.add(0.0, 0.0), 0.001);
        }
        
        @Test
        @DisplayName("Add boundary values")
        void testAddBoundaryValues() {
            assertEquals(Double.MAX_VALUE, calculator.add(Double.MAX_VALUE, 0.0), 0.001);
            assertEquals(Double.MIN_VALUE, calculator.add(Double.MIN_VALUE, 0.0), 0.001);
            assertEquals(Double.POSITIVE_INFINITY, calculator.add(Double.MAX_VALUE, Double.MAX_VALUE));
        }
        
        @ParameterizedTest
        @CsvSource({
            "1.5, 2.5, 4.0",
            "0.1, 0.2, 0.3",
            "100.0, 200.0, 300.0",
            "-50.0, 25.0, -25.0"
        })
        @DisplayName("Add various number combinations")
        void testAddVariousCombinations(double a, double b, double expected) {
            assertEquals(expected, calculator.add(a, b), 0.001);
        }
    }
    
    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractionTests {
        
        @Test
        @DisplayName("Subtract two positive numbers")
        void testSubtractPositiveNumbers() {
            assertEquals(2.0, calculator.subtract(5.0, 3.0), 0.001);
        }
        
        @Test
        @DisplayName("Subtract two negative numbers")
        void testSubtractNegativeNumbers() {
            assertEquals(1.0, calculator.subtract(-2.0, -3.0), 0.001);
        }
        
        @Test
        @DisplayName("Subtract positive and negative numbers")
        void testSubtractPositiveAndNegative() {
            assertEquals(5.0, calculator.subtract(3.0, -2.0), 0.001);
            assertEquals(-5.0, calculator.subtract(-3.0, 2.0), 0.001);
        }
        
        @Test
        @DisplayName("Subtract with zero")
        void testSubtractWithZero() {
            assertEquals(5.0, calculator.subtract(5.0, 0.0), 0.001);
            assertEquals(-5.0, calculator.subtract(0.0, 5.0), 0.001);
            assertEquals(0.0, calculator.subtract(0.0, 0.0), 0.001);
        }
        
        @Test
        @DisplayName("Subtract boundary values")
        void testSubtractBoundaryValues() {
            assertEquals(Double.MAX_VALUE, calculator.subtract(Double.MAX_VALUE, 0.0), 0.001);
            assertEquals(Double.MIN_VALUE, calculator.subtract(Double.MIN_VALUE, 0.0), 0.001);
            assertEquals(Double.NEGATIVE_INFINITY, calculator.subtract(-Double.MAX_VALUE, Double.MAX_VALUE));
        }
        
        @ParameterizedTest
        @CsvSource({
            "5.0, 3.0, 2.0",
            "10.5, 5.5, 5.0",
            "0.0, 1.0, -1.0",
            "-10.0, -5.0, -5.0"
        })
        @DisplayName("Subtract various number combinations")
        void testSubtractVariousCombinations(double a, double b, double expected) {
            assertEquals(expected, calculator.subtract(a, b), 0.001);
        }
    }
    
    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplicationTests {
        
        @Test
        @DisplayName("Multiply two positive numbers")
        void testMultiplyPositiveNumbers() {
            assertEquals(15.0, calculator.multiply(3.0, 5.0), 0.001);
        }
        
        @Test
        @DisplayName("Multiply two negative numbers")
        void testMultiplyNegativeNumbers() {
            assertEquals(15.0, calculator.multiply(-3.0, -5.0), 0.001);
        }
        
        @Test
        @DisplayName("Multiply positive and negative numbers")
        void testMultiplyPositiveAndNegative() {
            assertEquals(-15.0, calculator.multiply(3.0, -5.0), 0.001);
            assertEquals(-15.0, calculator.multiply(-3.0, 5.0), 0.001);
        }
        
        @Test
        @DisplayName("Multiply with zero")
        void testMultiplyWithZero() {
            assertEquals(0.0, calculator.multiply(5.0, 0.0), 0.001);
            assertEquals(0.0, calculator.multiply(0.0, 5.0), 0.001);
            assertEquals(0.0, calculator.multiply(0.0, 0.0), 0.001);
        }
        
        @Test
        @DisplayName("Multiply with one")
        void testMultiplyWithOne() {
            assertEquals(5.0, calculator.multiply(5.0, 1.0), 0.001);
            assertEquals(5.0, calculator.multiply(1.0, 5.0), 0.001);
            assertEquals(1.0, calculator.multiply(1.0, 1.0), 0.001);
        }
        
        @Test
        @DisplayName("Multiply boundary values")
        void testMultiplyBoundaryValues() {
            assertEquals(0.0, calculator.multiply(Double.MAX_VALUE, 0.0), 0.001);
            assertEquals(0.0, calculator.multiply(0.0, Double.MAX_VALUE), 0.001);
            assertEquals(Double.POSITIVE_INFINITY, calculator.multiply(Double.MAX_VALUE, 2.0));
        }
        
        @ParameterizedTest
        @CsvSource({
            "2.0, 3.0, 6.0",
            "0.5, 4.0, 2.0",
            "-2.0, 3.0, -6.0",
            "1.5, 2.0, 3.0"
        })
        @DisplayName("Multiply various number combinations")
        void testMultiplyVariousCombinations(double a, double b, double expected) {
            assertEquals(expected, calculator.multiply(a, b), 0.001);
        }
    }
    
    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {
        
        @Test
        @DisplayName("Divide two positive numbers")
        void testDividePositiveNumbers() {
            assertEquals(2.0, calculator.divide(6.0, 3.0), 0.001);
        }
        
        @Test
        @DisplayName("Divide two negative numbers")
        void testDivideNegativeNumbers() {
            assertEquals(2.0, calculator.divide(-6.0, -3.0), 0.001);
        }
        
        @Test
        @DisplayName("Divide positive and negative numbers")
        void testDividePositiveAndNegative() {
            assertEquals(-2.0, calculator.divide(6.0, -3.0), 0.001);
            assertEquals(-2.0, calculator.divide(-6.0, 3.0), 0.001);
        }
        
        @Test
        @DisplayName("Divide by one")
        void testDivideByOne() {
            assertEquals(5.0, calculator.divide(5.0, 1.0), 0.001);
            assertEquals(-5.0, calculator.divide(-5.0, 1.0), 0.001);
        }
        
        @Test
        @DisplayName("Divide zero by number")
        void testDivideZeroByNumber() {
            assertEquals(0.0, calculator.divide(0.0, 5.0), 0.001);
            assertEquals(0.0, calculator.divide(0.0, -5.0), 0.001);
        }
        
        @Test
        @DisplayName("Division by zero throws ArithmeticException")
        void testDivisionByZero() {
            ArithmeticException exception = assertThrows(ArithmeticException.class, 
                () -> calculator.divide(5.0, 0.0));
            assertEquals("Division by zero", exception.getMessage());
        }
        
        @Test
        @DisplayName("Division by negative zero throws ArithmeticException")
        void testDivisionByNegativeZero() {
            ArithmeticException exception = assertThrows(ArithmeticException.class, 
                () -> calculator.divide(5.0, -0.0));
            assertEquals("Division by zero", exception.getMessage());
        }
        
        @Test
        @DisplayName("Division by positive zero throws ArithmeticException")
        void testDivisionByPositiveZero() {
            ArithmeticException exception = assertThrows(ArithmeticException.class, 
                () -> calculator.divide(-5.0, 0.0));
            assertEquals("Division by zero", exception.getMessage());
        }
        
        @Test
        @DisplayName("Divide boundary values")
        void testDivideBoundaryValues() {
            assertEquals(Double.MAX_VALUE, calculator.divide(Double.MAX_VALUE, 1.0), 0.001);
            assertEquals(Double.MIN_VALUE, calculator.divide(Double.MIN_VALUE, 1.0), 0.001);
            assertEquals(Double.POSITIVE_INFINITY, calculator.divide(Double.MAX_VALUE, Double.MIN_VALUE));
        }
        
        @ParameterizedTest
        @CsvSource({
            "10.0, 2.0, 5.0",
            "15.0, 3.0, 5.0",
            "7.5, 2.5, 3.0",
            "-12.0, 4.0, -3.0"
        })
        @DisplayName("Divide various number combinations")
        void testDivideVariousCombinations(double a, double b, double expected) {
            assertEquals(expected, calculator.divide(a, b), 0.001);
        }
        
        @ParameterizedTest
        @ValueSource(doubles = {0.0, -0.0})
        @DisplayName("Division by zero variants")
        void testDivisionByZeroVariants(double zero) {
            assertThrows(ArithmeticException.class, () -> calculator.divide(10.0, zero));
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Values")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Operations with infinity")
        void testOperationsWithInfinity() {
            assertEquals(Double.POSITIVE_INFINITY, calculator.add(Double.POSITIVE_INFINITY, 1.0));
            assertEquals(Double.NEGATIVE_INFINITY, calculator.subtract(Double.NEGATIVE_INFINITY, 1.0));
            assertEquals(Double.POSITIVE_INFINITY, calculator.multiply(Double.POSITIVE_INFINITY, 2.0));
            assertEquals(Double.POSITIVE_INFINITY, calculator.divide(Double.POSITIVE_INFINITY, 2.0));
        }
        
        @Test
        @DisplayName("Operations with NaN")
        void testOperationsWithNaN() {
            assertTrue(Double.isNaN(calculator.add(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.subtract(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.multiply(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.divide(Double.NaN, 1.0)));
        }
        
        @Test
        @DisplayName("Very small numbers")
        void testVerySmallNumbers() {
            double verySmall = 1e-10;
            assertEquals(2e-10, calculator.add(verySmall, verySmall), 1e-15);
            assertEquals(0.0, calculator.subtract(verySmall, verySmall), 1e-15);
            assertEquals(1e-20, calculator.multiply(verySmall, verySmall), 1e-25);
            assertEquals(1.0, calculator.divide(verySmall, verySmall), 0.001);
        }
        
        @Test
        @DisplayName("Very large numbers")
        void testVeryLargeNumbers() {
            double veryLarge = 1e10;
            assertEquals(2e10, calculator.add(veryLarge, veryLarge), 1e5);
            assertEquals(0.0, calculator.subtract(veryLarge, veryLarge), 1e5);
            assertEquals(1e20, calculator.multiply(veryLarge, veryLarge), 1e15);
            assertEquals(1.0, calculator.divide(veryLarge, veryLarge), 0.001);
        }
    }
    
    @Nested
    @DisplayName("Calculator Behavior Tests")
    class CalculatorBehaviorTests {

        @Test
        @DisplayName("Dividing two non-zero doubles returns the correct quotient")
        void testDivideReturnsQuotientForNonZeroDivisor() {
            assertEquals(2.5, calculator.divide(10.0, 4.0), 1e-12);
        }

        @Test
        @DisplayName("Adding doubles with mixed signs and fractional parts returns the correct sum")
        void testAddMixedSignsAndFractions() {
            assertEquals(0.5, calculator.add(1.25, -0.75), 1e-12);
            assertEquals(1.25, calculator.add(-2.5, 3.75), 1e-12);
            assertEquals(-0.2, calculator.add(-0.1, -0.1), 1e-12);
        }

        @Test
        @DisplayName("Multiplying positive and negative doubles yields the correct product and sign")
        void testMultiplyPositiveAndNegative() {
            assertEquals(-6.25, calculator.multiply(2.5, -2.5), 1e-12);
            assertEquals(-7.0, calculator.multiply(-3.5, 2.0), 1e-12);
        }

        @Test
        @DisplayName("Dividing by zero throws ArithmeticException with the expected message")
        void testDivideByZeroThrowsArithmeticException() {
            ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calculator.divide(123.456, 0.0));
            assertEquals("Division by zero", ex.getMessage());
        }

        @Test
        @DisplayName("Dividing by negative zero is treated as zero and throws ArithmeticException")
        void testDivideByNegativeZeroThrowsArithmeticException() {
            ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calculator.divide(5.0, -0.0));
            assertEquals("Division by zero", ex.getMessage());
        }

        @Test
        @DisplayName("Operations with NaN operands propagate NaN results")
        void testOperationsWithNaNPropagateNaN() {
            assertTrue(Double.isNaN(calculator.add(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.add(1.0, Double.NaN)));

            assertTrue(Double.isNaN(calculator.subtract(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.subtract(1.0, Double.NaN)));

            assertTrue(Double.isNaN(calculator.multiply(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.multiply(1.0, Double.NaN)));

            assertTrue(Double.isNaN(calculator.divide(Double.NaN, 1.0)));
            assertTrue(Double.isNaN(calculator.divide(1.0, Double.NaN)));
        }
    }
}
