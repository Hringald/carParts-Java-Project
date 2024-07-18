package com.carParts.validation;


import com.carParts.service.impl.UserServiceImpl;
import com.carParts.validation.annotation.LegitPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegitPhoneValidator implements ConstraintValidator<LegitPhone, String> {


    public LegitPhoneValidator(UserServiceImpl userService) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}