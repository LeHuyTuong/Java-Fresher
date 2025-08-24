package com.tuonglh.coffee.samplecode.dto.validation.annotation;


import com.tuonglh.coffee.samplecode.dto.validation.validator.EnumPatternValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
    String name();
    String regexp();
    String message() default "{invalid.enum}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
