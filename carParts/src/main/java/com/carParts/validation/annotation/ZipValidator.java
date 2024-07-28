package com.carParts.validation.annotation;

import com.carParts.validation.LegitZipValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LegitZipValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipValidator {
    String message() default "Invalid Zip code!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
