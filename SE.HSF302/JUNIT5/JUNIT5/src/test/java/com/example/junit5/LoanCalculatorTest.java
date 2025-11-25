package com.example.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class LoanCalculatorTest {

    // ===== PHẦN 1: KIỂM THỬ VỚI DỮ LIỆU TỪ FILE CSV CHO CÁC TRƯỜNG HỢP HỢP LỆ =====
    @ParameterizedTest
    @CsvFileSource(resources = "/loan_test_data.csv", numLinesToSkip = 1)
    @DisplayName("Should calculate correct monthly payment for valid inputs from CSV")
    void testCalculateMonthlyPayment_FromCsvFile(
            BigDecimal principal,
            double annualInterestRate,
            int years,
            BigDecimal expectedResult,
            String description) { // Thêm cả cột description để thông điệp lỗi rõ ràng hơn

        // Hành động: Gọi hàm cần test
        BigDecimal actualResult = LoanCalculator.calculateMonthlyPayment(principal, annualInterestRate, years);

        // Khẳng định: So sánh kết quả thực tế với kết quả mong đợi
        assertEquals(0, expectedResult.compareTo(actualResult),
                "Test failed for case: " + description);
    }


    // ===== PHẦN 2: KIỂM THỬ CÁC TRƯỜNG HỢP NÉM RA NGOẠI LỆ (ĐẦU VÀO KHÔNG HỢP LỆ) =====
    @Test
    @DisplayName("Should throw IllegalArgumentException for negative principal")
    void testCalculateMonthlyPayment_NegativePrincipal_ThrowsExceptionv() {
        // Khẳng định rằng hàm sẽ ném ra ngoại lệ IllegalArgumentException
        // khi được gọi với các tham số này.
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(-200), 6, 10);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for negative interest rate")
    void testCalculateMonthlyPayment_NegativeInterestRate_ThrowsExceptionhehe() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), -6, 10);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for negative years")
    void testCalculateMonthlyPayment_NegativeYears_ThrowsExceptionhehe() {
        assertThrows(IllegalArgumentException.class, () -> {
            LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 6, -5);
        });
    }

    @Test
    @DisplayName("Calculates correct monthly payment for standard loan inputs")
    void testCalculateMonthlyPayment_StandardLoan_ComputesExpectedPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(200000), 5.0, 30);
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1073.64)));
    }

    @Test
    @DisplayName("Calculates correct monthly payment for example case: 100,000 at 6% for 10 years")
    void testCalculateMonthlyPayment_ExampleCase_ReturnsExpectedPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 6.0, 10);
        assertEquals(0, result.compareTo(BigDecimal.valueOf(1110.21)));
    }

    @Test
    @DisplayName("Rounds result to two decimals using HALF_UP")
    void testCalculateMonthlyPayment_RoundsToTwoDecimals_HalfUp() {
        BigDecimal principal = BigDecimal.valueOf(12345);
        double annualInterestRate = 3.333;
        int years = 3;

        BigDecimal result = LoanCalculator.calculateMonthlyPayment(principal, annualInterestRate, years);

        double monthlyRate = annualInterestRate / 100 / 12;
        int totalMonths = years * 12;
        double numerator = monthlyRate * Math.pow(1 + monthlyRate, totalMonths);
        double denominator = Math.pow(1 + monthlyRate, totalMonths) - 1;
        double monthlyPayment = principal.doubleValue() * (numerator / denominator);
        BigDecimal expected = BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.HALF_UP);

        assertEquals(2, result.scale());
        assertEquals(0, result.compareTo(expected));
    }

    @Test
    @DisplayName("Handles large principal and long term while returning a two-decimal payment")
    void testCalculateMonthlyPayment_LargePrincipalLongTerm_ReturnsScaledValue() {
        BigDecimal principal = BigDecimal.valueOf(1_000_000_000L);
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(principal, 8.5, 50);

        assertEquals(2, result.scale());
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Returns 0.00 when annual interest rate is zero")
    void testCalculateMonthlyPayment_ZeroInterest_ReturnsZeroPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 0.0, 10);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    @DisplayName("Returns 0.00 when principal is zero")
    void testCalculateMonthlyPayment_ZeroPrincipal_ReturnsZeroPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.ZERO, 7.0, 15);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    @DisplayName("Returns 0.00 when both principal and interest rate are zero")
    void testCalculateMonthlyPayment_ZeroPrincipalZeroInterest_ReturnsZeroPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.ZERO, 0.0, 5);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for negative principal")
    void testCalculateMonthlyPayment_NegativePrincipal_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(-1000), 5.0, 10));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for negative interest rate")
    void testCalculateMonthlyPayment_NegativeInterestRate_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), -5.0, 10));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for zero years")
    void testCalculateMonthlyPayment_ZeroYears_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 5.0, 0));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for negative years")
    void testCalculateMonthlyPayment_NegativeYears_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 5.0, -5));
    }

    // Boundary Value Analysis Tests
    @Test
    @DisplayName("BVA: Minimum valid principal (0.01)")
    void testCalculateMonthlyPayment_MinimumPrincipal_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(0.01), 5.0, 1);
        assertTrue(result.compareTo(BigDecimal.ZERO) >= 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("BVA: Minimum valid interest rate (0.01%)")
    void testCalculateMonthlyPayment_MinimumInterestRate_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 0.01, 5);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("BVA: Minimum valid years (1)")
    void testCalculateMonthlyPayment_MinimumYears_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 5.0, 1);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("BVA: Maximum reasonable interest rate (99.99%)")
    void testCalculateMonthlyPayment_HighestInterestRate_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(10000), 99.99, 5);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("BVA: Long term loan (50 years)")
    void testCalculateMonthlyPayment_LongTerm_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(100000), 4.0, 50);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    // Equivalence Partitioning Tests
    @Test
    @DisplayName("EP: Small loan amount (< 10,000)")
    void testCalculateMonthlyPayment_SmallLoan_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(5000), 4.5, 3);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("EP: Medium loan amount (10,000 - 500,000)")
    void testCalculateMonthlyPayment_MediumLoan_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(250000), 3.75, 15);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("EP: Low interest rate (0-5%)")
    void testCalculateMonthlyPayment_LowInterestRate_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(50000), 2.5, 10);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("EP: High interest rate (10%+)")
    void testCalculateMonthlyPayment_HighInterestRate_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(50000), 15.0, 10);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("EP: Short term loan (1-5 years)")
    void testCalculateMonthlyPayment_ShortTerm_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(20000), 6.0, 3);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }

    @Test
    @DisplayName("EP: Medium term loan (6-20 years)")
    void testCalculateMonthlyPayment_MediumTerm_ReturnsValidPayment() {
        BigDecimal result = LoanCalculator.calculateMonthlyPayment(BigDecimal.valueOf(150000), 5.5, 15);
        assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, result.scale());
    }
}

