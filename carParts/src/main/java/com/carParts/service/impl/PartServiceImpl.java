package com.carParts.service.impl;

import com.carParts.model.entity.Admin;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.AdminRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.AdminService;
import com.carParts.service.PartService;
import com.carParts.util.AdminUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final UserRepo userRepo;

    private final PartRepo partRepo;

    public PartServiceImpl(UserRepo userRepo, PartRepo partRepo) {
        this.userRepo = userRepo;
        this.partRepo = partRepo;
    }
    @Override
    public List<Part> findOwnedParts(User user){
     return this.partRepo.findBySeller(user);
    }
}
