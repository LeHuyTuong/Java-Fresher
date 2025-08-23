package com.tuonglh.coffee.samplecode.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)   // chạy trong môi trường runtime
public @interface PhoneNumber {
    String message() default "Invalid phone number";  // message mặc định
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
