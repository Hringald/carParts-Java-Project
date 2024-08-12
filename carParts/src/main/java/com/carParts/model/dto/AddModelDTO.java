package com.carParts.model.dto;

import com.carParts.model.entity.DataConstants;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddModelDTO {

    private Long id;

    @NotNull(message = "You must enter model name!")
    @Length(min = DataConstants.Model.NameMinLength, max = DataConstants.Model.NameMaxLength)
    private String name;

    @NotBlank(message = "{part_url_empty_validation}")
    private String imageUrl;

    @NotBlank(message = "{part_make_name_empty_validation}")
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
