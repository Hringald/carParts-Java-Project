package com.carParts.validation.annotation;

import com.carParts.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "{register_email_exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
