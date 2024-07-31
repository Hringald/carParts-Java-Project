package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddModelDTO {

    private Long id;

    @NotNull(message = "You must enter model name!")
    @Length(min = DataConstants.Model.NameMinLength, max = DataConstants.Model.NameMaxLength)
    private String name;

    @NotBlank(message = "You must enter image URL!")
    private String imageUrl;

    @NotBlank(message = "You must enter make name!")
    private String makeName;

    public AddModelDTO() {
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
}
