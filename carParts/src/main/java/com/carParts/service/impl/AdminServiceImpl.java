package com.carParts.service.impl;

import com.carParts.model.entity.Admin;
import com.carParts.model.entity.User;
import com.carParts.repository.AdminRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.AdminService;
import com.carParts.util.AdminUser;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final AdminUser adminUser;
    private final AdminRepo adminRepo;

    public AdminServiceImpl(UserRepo userRepo, AdminRepo adminRepo, AdminUser adminUser) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.adminUser = adminUser;
    }
    @Override
    public void makeAdmin(User user){
     Admin admin = new Admin();


     admin.setUser(user);
     admin.setName(user.getUsername());

     this.adminUser.setId(user.getId());
     this.adminUser.setUsername(user.getUsername());

     this.adminRepo.save(admin);
     this.userRepo.save(user);
    }
}
