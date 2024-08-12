package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import com.carParts.validation.annotation.PhoneValidator;
import com.carParts.validation.annotation.ZipValidator;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


public class AddOfferDTO {

    private Long id;

    @NotNull(message = "{offer_name_empty_validation}")
    @Length(min = DataConstants.Offer.NameMinLength, max = DataConstants.Offer.NameMaxLength, message = "{offer_name_length_validation}")
    public String name;

    @Email(message = "{login_email_error_message}")
    @NotBlank(message = "{login_email_error_not_empty_message}")
    private String email;

    @NotNull(message = "{account_phone_number_empty}")
    @PhoneValidator(message = "{account_phone_number_valid}")
    private String phone;
    @NotNull(message = "{offer_address_empty_validation}")
    @Length(min = DataConstants.Offer.AddressMinLength, max = DataConstants.Offer.AddressMaxLength, message = "{offer_address_length_validation}")
    private String address;
    @NotNull(message = "{offer_city_empty_validation}")
    @Length(min = DataConstants.Offer.CityMinLength, max = DataConstants.Offer.CityMaxLength, message = "{offer_city_length_validation}")
    private String city;
    @NotNull(message = "{offer_zip_empty_validation}")
    @ZipValidator
    @Length(min = DataConstants.Offer.ZipMinLength, max = DataConstants.Offer.ZipMaxLength, message = "{offer_zip_length_validation}")
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
