package com.carParts.util;

import com.carParts.model.entity.Part;
import com.carParts.repository.PartRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;

@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogged() {
        return this.email != null && this.id != null;
    }

}
