package com.carParts.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmailChangeDTO {

    private Long id;

    @Email(message = "Email is not valid!")
    @NotNull(message = "Please enter an email address!")
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
