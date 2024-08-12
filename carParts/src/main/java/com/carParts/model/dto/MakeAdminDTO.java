package com.carParts.model.dto;

import com.carParts.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

public class MakeAdminDTO {

    @Size(min = 3, max = 20, message = "{account_admin_username_size_validation}")
    @NotBlank(message = "{account_admin_username_size_empty}")
    @UniqueUsername
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
