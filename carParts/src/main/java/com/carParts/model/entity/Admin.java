package com.carParts.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {

    @Column(nullable = false, length = DataConstants.Admin.NameMaxLength)
    private String name;

    @OneToOne(mappedBy = "admin")
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Model> models;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Make> makes;

    public Admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public Set<Make> getMakes() {
        return makes;
    }

    public void setMakes(Set<Make> makes) {
        this.makes = makes;
    }
}
