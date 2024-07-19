package com.carParts.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

public class MakeAdminDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    public MakeAdminDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
