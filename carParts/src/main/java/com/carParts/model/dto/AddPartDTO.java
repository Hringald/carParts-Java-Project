package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPartDTO {

    private Long id;

    @NotNull(message = "You must enter part name!")
    @Length(min = DataConstants.Part.NameMinLength, max = DataConstants.Part.NameMaxLength)
    private String name;

    @NotBlank(message = "You must enter image URL")
    private String imageUrl;
    private String makeName;

    private String modelName;

    private String categoryName;
    @NotNull(message = "You must enter part price!")
    @DecimalMin(DataConstants.Part.DecimalMinValue)
    @DecimalMax(DataConstants.Part.DecimalMaxValue)
    private double price;

    @NotNull(message = "You must enter part quantity!")
    @Size(min = DataConstants.Part.QuantityMinValue, max = DataConstants.Part.QuantityMaxValue)
    private int quantity;

    @NotBlank(message = "You must enter part description!")
    @Length(min = DataConstants.Part.DescriptionMinLength, max = DataConstants.Part.DescriptionMaxLength)
    private String description;

    public AddPartDTO() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
