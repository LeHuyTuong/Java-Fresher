package com.tuonglh.coffee.samplecode.dto.validation.annotation;


import com.tuonglh.coffee.samplecode.dto.validation.enums.Gender;
import com.tuonglh.coffee.samplecode.dto.validation.validator.GenderSubsetValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderSubsetValidator.class)
public @interface GenderSubset {
    Gender[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
