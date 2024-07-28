package com.carParts.validation;


import com.carParts.service.impl.UserServiceImpl;
import com.carParts.validation.annotation.ZipValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegitZipValidator implements ConstraintValidator<ZipValidator, String> {


    public LegitZipValidator(UserServiceImpl userService) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[0-9]{4}(?:-[0-9]{4})?$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}