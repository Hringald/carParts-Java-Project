package com.carParts.validation.annotation;

import com.carParts.validation.LegitPhoneValidator;
import com.carParts.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LegitPhoneValidator .class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LegitPhone {
    String message() default "Invalid Phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
