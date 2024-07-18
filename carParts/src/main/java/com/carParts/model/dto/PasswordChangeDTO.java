package com.carParts.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PasswordChangeDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull(message = "Password must not be empty!")
    private String newPassword;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull(message = "Confirm Password must not be empty!")
    private String confirmNewPassword;

    public PasswordChangeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
