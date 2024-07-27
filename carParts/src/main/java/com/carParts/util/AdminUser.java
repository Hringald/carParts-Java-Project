package com.carParts.util;

import com.carParts.model.entity.User;
import com.carParts.repository.UserRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class AdminUser {

    private Long id;
    private String username;
    private final UserRepo userRepo;

    public AdminUser(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        if (this.id == null){
            return false;
        }

        User currentUser = this.userRepo.findById(this.id).orElse(null);
        return currentUser != null;
    }
}
