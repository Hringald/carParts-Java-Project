package com.carParts.validation.annotation;

import com.carParts.validation.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "{account_admin_username_validation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
