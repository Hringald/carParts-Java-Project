package com.carParts.service.impl;

import com.carParts.model.entity.User;
import com.carParts.model.enums.UserRoleEnum;
import com.carParts.repository.UserRepo;
import com.carParts.repository.UserRoleRepo;
import com.carParts.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    public AdminServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
    }


}
