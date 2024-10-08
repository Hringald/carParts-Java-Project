package com.carParts.validation.annotation;

import com.carParts.validation.LegitPhoneValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LegitPhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValidator {
    String message() default "{account_manage_valid_phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
