package com.carParts.model.dto;

import com.carParts.validation.annotation.LegitPhone;
import jakarta.validation.constraints.NotNull;

public class PhoneChangeDTO {

    private Long id;

    @LegitPhone(message = "Phone number is invalid")
    @NotNull(message = "Please enter a phone number!")
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
