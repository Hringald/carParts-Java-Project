package com.carParts.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "makes")
public class Make extends BaseEntity {
    @Column(nullable = false, length = DataConstants.Make.NameMaxLength)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Admin admin;
    @OneToOne(cascade = CascadeType.ALL)
    private Part part;
    @OneToMany(mappedBy = "make")
    private Set<Model> models;

    public Make() {
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
