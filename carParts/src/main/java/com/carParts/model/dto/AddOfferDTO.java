package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import com.carParts.validation.annotation.PhoneValidator;
import com.carParts.validation.annotation.ZipValidator;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


public class AddOfferDTO {

    private Long id;

    @NotNull(message = "You must enter your first name!")
    @Length(min = DataConstants.Offer.NameMinLength, max = DataConstants.Offer.NameMaxLength, message = "First name bust be between 2 and 100 characters long!")
    public String name;

    @Email(message = "Email must be in valid format!")
    @NotBlank(message = "You must enter your email!")
    private String email;

    @NotNull(message = "You must enter your phone number!")
    @PhoneValidator(message = "Phone number must be in valid format!")
    private String phone;
    @NotNull(message = "You must enter your address!")
    @Length(min = DataConstants.Offer.AddressMinLength, max = DataConstants.Offer.AddressMaxLength, message = "Address mut be between 8 and 189 characters long!")
    private String address;
    @NotNull(message = "You must enter your city!")
    @Length(min = DataConstants.Offer.CityMinLength, max = DataConstants.Offer.CityMaxLength, message = "City mut be between 8 and 189 characters long!")
    private String city;
    @NotNull(message = "You must enter zip code!")
    @ZipValidator
    @Length(min = DataConstants.Offer.ZipMinLength,max = DataConstants.Offer.ZipMaxLength ,message = "Zip code must be minimum 4 characters long!")
    @Column(nullable = false)
    private String zipCode;

    public AddOfferDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
