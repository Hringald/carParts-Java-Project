package com.carParts.service.impl;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.*;
import com.carParts.repository.*;
import com.carParts.service.PartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Part> findAllParts() {
        return this.partRepo.findAll();
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
    public void removePart(Long id) {

        Part partToDelete = this.partRepo.findById(id).orElse(null);
        if (partToDelete != null) {
            User partSeller = partToDelete.getSeller();
            Set<Part> sellerParts = partSeller.getParts();
            sellerParts.remove(partToDelete);

            Category partCategory = partToDelete.getCategory();
            Set<Part> categoryParts = partCategory.getParts();
            categoryParts.remove(partToDelete);

            Make partMake = partToDelete.getMake();
            Set<Part> makeParts = partMake.getParts();
            makeParts.remove(partToDelete);

            Model partModel = partToDelete.getModel();
            Set<Part> modelParts = partModel.getParts();
            modelParts.remove(partToDelete);

            partToDelete.setSeller(null);
            partToDelete.setCategory(null);
            partToDelete.setMake(null);
            partToDelete.setModel(null);

            this.userRepo.save(partSeller);
            this.categoryRepo.save(partCategory);
            this.makeRepo.save(partMake);
            this.modelRepo.save(partModel);

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

    @Override
    public void initParts() {
        Make make = this.makeRepo.findByName("BMW").orElse(null);
        Model model = this.modelRepo.findByName("3 Series").orElse(null);
        Category category = this.categoryRepo.findByName("Main Parts").orElse(null);
        User user = this.userRepo.findByEmail("test@abv.bg").orElse(null);

        for (int i = 0; i < 7; i++) {

            Part part = new Part();
            part.setName("Engine");
            part.setImageUrl("https://www.realoem.com/images/eng.jpg");
            part.setMake(make);
            part.setModel(model);
            part.setCategory(category);
            part.setPrice(200);
            part.setQuantity(2);
            part.setDescription("1234567890123412312321321");
            part.setSeller(user);

            Set<Part> userParts = user.getParts();
            userParts.add(part);
            user.setParts(userParts);

            this.partRepo.save(part);
            this.userRepo.save(user);
        }

        for (int i = 0; i < 7; i++) {

            Part part = new Part();
            part.setName("test");
            part.setImageUrl("https://www.realoem.com/images/eng.jpg");
            part.setMake(make);
            part.setModel(model);
            part.setCategory(category);
            part.setPrice(200);
            part.setQuantity(2);
            part.setDescription("1234567890123412312321321");
            part.setSeller(user);

            Set<Part> userParts = user.getParts();
            userParts.add(part);
            user.setParts(userParts);

            this.partRepo.save(part);
            this.userRepo.save(user);
        }
    }

    @Override
    public Page<Part> findPaginated(int pageNo, int pageSize, String makeName, String modelName, String categoryName, String searchTerm) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        Make make = this.makeRepo.findByName(makeName).orElse(null);
        Model model = this.modelRepo.findByName(modelName).orElse(null);
        Category category = this.categoryRepo.findByName(categoryName).orElse(null);
        if (searchTerm == null || searchTerm.isEmpty()) {
            searchTerm = "%";
        }
        return this.partRepo.findByMakeAndModelAndCategoryAndNameLike(make, model, category, searchTerm, pageable);
    }

    @Override
    public void deleteAllPartsByMake(Make make) {
        List<Part> parts = this.partRepo.findByMake(make);

        for (Part part : parts) {
            this.partRepo.delete(part);
        }
    }
}
