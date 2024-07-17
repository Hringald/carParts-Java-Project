package com.carParts.model.entity;

import jakarta.persistence.*;

import javax.validation.constraints.Email;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Column(nullable = false, length = DataConstants.Offer.NameMaxLength)
    public String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String phone;
    @OneToOne(mappedBy = "offer")
    private Part part;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private int zipCode;
    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    public Offer() {
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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
