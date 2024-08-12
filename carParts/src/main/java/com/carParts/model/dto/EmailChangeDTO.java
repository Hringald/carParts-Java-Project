package com.carParts.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailChangeDTO {

    private Long id;

    @Email(message = "{login_email_error_message}")
    @NotEmpty(message = "{login_email_error_not_empty_message}")
    private String emailChange;

    public EmailChangeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(String emailChange) {
        this.emailChange = emailChange;
    }
}
