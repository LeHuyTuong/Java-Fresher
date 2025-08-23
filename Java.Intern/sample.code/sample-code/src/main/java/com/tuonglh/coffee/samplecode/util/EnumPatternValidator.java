package com.tuonglh.coffee.samplecode.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern,Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(EnumPattern constraintAnnotation) {
        try{
            pattern = Pattern.compile(constraintAnnotation.regex()); // so sánh với reg
        }catch(PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid");
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        Matcher m = pattern.matcher(value.name());
        return m.matches();
    }
}
