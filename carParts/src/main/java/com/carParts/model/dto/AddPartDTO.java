package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class AddPartDTO {

    private Long id;

    @NotNull(message = "{part_name_empty_validation}")
    @Length(min = DataConstants.Part.NameMinLength, max = DataConstants.Part.NameMaxLength, message = "{part_name_length_validation}")
    private String name;

    @NotBlank(message = "{part_url_empty_validation}")
    private String imageUrl;
    @NotBlank(message = "{part_make_name_empty_validation}")
    private String makeName;
    @NotBlank(message = "{part_model_name_empty_validation}")
    private String modelName;

    private String categoryName;
    @NotNull(message = "{part_price_empty_validation}")
    @DecimalMin(value = DataConstants.Part.DecimalMinValue, message = "{part_price_minimum_error}")
    @DecimalMax(value = DataConstants.Part.DecimalMaxValue, message = "{part_price_maximum_error}")
    private double price;

    @NotNull(message = "{part_quantity_empty_validation}")
    @Min(value = DataConstants.Part.QuantityMinValue,message = "{part_quantity_min_error}")
    @Max(value = DataConstants.Part.QuantityMaxValue,message = "{part_quantity_max_error}")
    private int quantity;

    @NotBlank(message = "{part_description_empty_validation}")
    @Length(min = DataConstants.Part.DescriptionMinLength, max = DataConstants.Part.DescriptionMaxLength, message = "{part_description_error}")
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