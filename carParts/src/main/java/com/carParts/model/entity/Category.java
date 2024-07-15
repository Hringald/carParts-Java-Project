package com.carParts.model.entity;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false, length = DataConstants.Category.NameMaxLength)
    private String userName;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    private Set<Part> parts;

    public Category() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
