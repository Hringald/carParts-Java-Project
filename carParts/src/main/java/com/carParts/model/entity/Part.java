package com.carParts.model.entity;

import jakarta.persistence.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {
    @Column(nullable = false, length = DataConstants.Model.NameMaxLength)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @OneToOne(mappedBy = "part")
    private Make make;
    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;

    @OneToOne
    private Offer offer;

    @Column(nullable = false, length = DataConstants.Part.DescriptionMaxLength)
    private String description;

    @Column(nullable = false)
    @Size(min = DataConstants.Part.QuantityMinValue, max = DataConstants.Part.QuantityMaxValue)
    private int quantity;

    @Column(nullable = false)
    @DecimalMin(DataConstants.Part.DecimalMinValue)
    @DecimalMax(DataConstants.Part.DecimalMaxValue)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;


    public Part() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
