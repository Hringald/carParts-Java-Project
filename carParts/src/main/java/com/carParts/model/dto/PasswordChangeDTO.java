package com.carParts.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PasswordChangeDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "{register_valid_password}")
    @NotBlank(message = "{register_valid_not_blank_password}")
    private String newPassword;

    @Size(min = 3, max = 20, message = "{register_valid_password}")
    @NotBlank(message = "{register_valid_not_blank_password}")
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
