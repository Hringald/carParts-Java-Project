package com.carParts.validation;

import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.validation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AdminServiceImpl adminService;

    public UniqueUsernameValidator(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.adminService.findAdminByUsername(value) == null;
    }
}