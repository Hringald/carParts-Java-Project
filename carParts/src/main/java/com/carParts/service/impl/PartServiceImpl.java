package com.carParts.service.impl;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.*;
import com.carParts.repository.*;
import com.carParts.service.PartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PartServiceImpl implements PartService {

    private final UserRepo userRepo;

    private final PartRepo partRepo;
    private final MakeRepo makeRepo;
    private final ModelRepo modelRepo;

    private final CategoryRepo categoryRepo;

    public PartServiceImpl(UserRepo userRepo, PartRepo partRepo, CategoryRepo categoryRepo, MakeRepo makeRepo, ModelRepo modelRepo) {
        this.userRepo = userRepo;
        this.partRepo = partRepo;
        this.categoryRepo = categoryRepo;
        this.makeRepo = makeRepo;
        this.modelRepo = modelRepo;
    }

    @Override
    public List<Part> findOwnedParts(User user) {
        return this.partRepo.findBySeller(user);
    }

    @Override
    public void addPart(AddPartDTO addPartDTO, User currentUser) {
        Part part = new Part();

        part.setName(addPartDTO.getName());
        part.setImageUrl(addPartDTO.getImageUrl());
        Category category = this.categoryRepo.findByName(addPartDTO.getCategoryName()).orElse(null);
        part.setCategory(category);
        Make make = this.makeRepo.findByName(addPartDTO.getMakeName()).orElse(null);
        part.setMake(make);
        Model model = this.modelRepo.findByName(addPartDTO.getModelName()).orElse(null);
        part.setModel(model);
        part.setDescription(addPartDTO.getDescription());
        part.setQuantity(addPartDTO.getQuantity());
        part.setPrice(addPartDTO.getPrice());
        part.setSeller(currentUser);

        this.partRepo.save(part);
        this.userRepo.save(currentUser);
    }

    @Override
    public void removePartById(Long id, User currentUser) {
        Part partToDelete = this.partRepo.findById(id).orElse(null);
        if (partToDelete != null) {
            Set<Part> ownedParts = currentUser.getParts();
            ownedParts.remove(partToDelete);
            currentUser.setParts(ownedParts);

            this.userRepo.save(currentUser);
            this.partRepo.delete(partToDelete);
        }
    }

    @Override
    public Part findPartById(Long id) {
        return this.partRepo.findById(id).orElse(null);
    }

    @Override
    public void editPart(Part currentPart, AddPartDTO addPartDTO) {
        currentPart.setName(addPartDTO.getName());
        currentPart.setImageUrl(addPartDTO.getImageUrl());

        Model model = this.modelRepo.findByName(addPartDTO.getModelName()).orElse(null);
        currentPart.setModel(model);

        Category category = this.categoryRepo.findByName(addPartDTO.getCategoryName()).orElse(null);
        currentPart.setCategory(category);

        currentPart.setPrice(addPartDTO.getPrice());
        currentPart.setQuantity(addPartDTO.getQuantity());
        currentPart.setDescription(addPartDTO.getDescription());

        this.partRepo.save(currentPart);
    }
}
