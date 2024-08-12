package com.carParts.model.dto;

import com.carParts.validation.annotation.PhoneValidator;
import jakarta.validation.constraints.NotNull;

public class PhoneChangeDTO {

    private Long id;

    @PhoneValidator(message = "{account_phone_number_valid}")
    @NotNull(message = "{account_phone_number_empty}")
    private String phone;

    public PhoneChangeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
