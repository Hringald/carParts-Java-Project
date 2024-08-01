package com.carParts.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Model> parts;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "make", cascade = CascadeType.ALL)
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

    public Set<Model> getParts() {
        return parts;
    }

    public void setParts(Set<Model> parts) {
        this.parts = parts;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
