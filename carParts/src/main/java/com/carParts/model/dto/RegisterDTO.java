package com.carParts.model.dto;

import com.carParts.validation.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    private Long id;

    @UniqueEmail
    @Email(message = "{login_email_error_message}")
    @NotBlank(message = "{login_email_error_not_empty_message}")
    private String email;

    @Size(min = 3, max = 20, message = "{register_valid_password}")
    @NotNull(message = "{register_valid_not_blank_password}")
    private String password;

    @Size(min = 3, max = 20, message = "{register_valid_password}")
    @NotNull(message = "{register_valid_not_blank_confirm_password}")
    private String confirmPassword;

    public RegisterDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
