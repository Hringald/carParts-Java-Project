package com.carParts.service;

import com.carParts.model.entity.Admin;
import com.carParts.model.entity.User;

public interface AdminService {

    void makeAdmin(User user);

    Admin findAdminByUsername(String username);
}
