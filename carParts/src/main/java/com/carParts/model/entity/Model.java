package com.carParts.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Column(nullable = false, length = DataConstants.Model.NameMaxLength)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Make make;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Part> parts;

    public Model() {
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

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }


    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
