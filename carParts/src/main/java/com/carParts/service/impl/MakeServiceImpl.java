package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.entity.Admin;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.User;
import com.carParts.repository.MakeRepo;
import com.carParts.repository.PartRepo;
import com.carParts.service.AdminService;
import com.carParts.service.MakeService;
import com.carParts.util.AdminUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepo makeRepo;

    private final PartServiceImpl partService;

    private final ModelServiceImpl modelService;

    private final AdminService adminService;

    private final AdminUser adminUser;

    public MakeServiceImpl(MakeRepo makeRepo, AdminService adminService, AdminUser adminUser, PartServiceImpl partService, ModelServiceImpl modelService) {

        this.makeRepo = makeRepo;
        this.adminService = adminService;
        this.adminUser = adminUser;
        this.partService = partService;
        this.modelService = modelService;
    }

    @Override
    public List<Make> getAllMakes() {
        return this.makeRepo.findAll();
    }

    @Override
    public Make findMakeByName(String makeName) {
        return this.makeRepo.findByName(makeName).orElse(null);
    }

    @Override
    public void initMakes() {
        Make make = new Make();

        make.setName("BMW");
        make.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/800px-BMW.svg.png");

        this.makeRepo.save(make);
    }

    @Override
    public Make findMakeById(Long id) {
        return this.makeRepo.findById(id).orElse(null);
    }

    @Override
    public void editMakeById(AddMakeDTO addMakeDTO, Long id) {
        Make make = this.makeRepo.findById(id).orElse(null);

        make.setName(addMakeDTO.getName());

        make.setImageUrl(addMakeDTO.getImageUrl());

        this.makeRepo.save(make);
    }

    @Override
    public void createMake(AddMakeDTO addMakeDTO) {
        Make make = new Make();

        make.setName(addMakeDTO.getName());

        make.setImageUrl(addMakeDTO.getImageUrl());

        Admin admin = adminService.findAdminByUsername(adminUser.getUsername());

        make.setAdmin(admin);

        this.makeRepo.save(make);
    }

    @Override
    public void deleteMakeById(Long id) {
        Make currentMake = this.makeRepo.findById(id).orElse(null);

        this.modelService.deleteAllModelsByMake(currentMake);
        this.partService.deleteAllPartsByMake(currentMake);
        this.makeRepo.delete(currentMake);
    }
}
