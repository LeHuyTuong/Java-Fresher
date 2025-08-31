package com.tuonglh.coffee.samplecode.dto.validation.validator;

import com.tuonglh.coffee.samplecode.dto.validation.annotation.GenderSubset;
import com.tuonglh.coffee.samplecode.dto.validation.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderSubsetValidator implements ConstraintValidator<GenderSubset, Gender> {
    private Gender[] genders;

    @Override
    public void initialize(GenderSubset constraint) {
        this.genders = constraint.anyOf();
    }

    @Override
    public boolean isValid(Gender value , ConstraintValidatorContext context) {
        return value == null || Arrays.asList(genders).contains(value);
    }

}
