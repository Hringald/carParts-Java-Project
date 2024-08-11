package com.carParts.service.impl;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.*;
import com.carParts.repository.*;
import com.carParts.service.PartService;
import org.modelmapper.ModelMapper;
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
        ModelMapper modelMapper = new ModelMapper();

        Part part = modelMapper.map(addPartDTO, Part.class);

        Category category = this.categoryRepo.findByName(addPartDTO.getCategoryName()).orElse(null);
        part.setCategory(category);
        Make make = this.makeRepo.findByName(addPartDTO.getMakeName()).orElse(null);
        part.setMake(make);
        Model model = this.modelRepo.findByName(addPartDTO.getModelName()).orElse(null);
        part.setModel(model);
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
        currentPart.setPrice(addPartDTO.getPrice());
        currentPart.setQuantity(addPartDTO.getQuantity());
        currentPart.setDescription(addPartDTO.getDescription());

        Model model = this.modelRepo.findByName(addPartDTO.getModelName()).orElse(null);
        currentPart.setModel(model);

        Category category = this.categoryRepo.findByName(addPartDTO.getCategoryName()).orElse(null);
        currentPart.setCategory(category);

        this.partRepo.save(currentPart);
    }

    @Override
    public void initParts() {
        Make make = this.makeRepo.findByName("BMW").orElse(null);
        Model model = this.modelRepo.findByName("3 Series").orElse(null);
        Category category = this.categoryRepo.findByName("Main Parts").orElse(null);
        User user = this.userRepo.findByEmail("test@abv.bg").orElse(null);

        Part part1 = new Part();
        part1.setName("Engine");
        part1.setImageUrl("https://www.realoem.com/images/eng.jpg");
        part1.setMake(make);
        part1.setModel(model);
        part1.setCategory(category);
        part1.setPrice(2000);
        part1.setQuantity(2);
        part1.setDescription("BMW Engine");
        part1.setSeller(user);

        Set<Part> userParts1 = user.getParts();
        userParts1.add(part1);
        user.setParts(userParts1);

        this.partRepo.save(part1);

        Part part2 = new Part();
        part2.setName("Crankshaft");
        part2.setImageUrl("https://haynes.com/en-gb/sites/default/files/styles/unaltered_webp/public/What%20is%20it%20-%20Crank_0.jpg?itok=eD8z2ii8&timestamp=1482402192");
        part2.setMake(make);
        part2.setModel(model);
        part2.setCategory(category);
        part2.setPrice(1000);
        part2.setQuantity(2);
        part2.setDescription("BMW Crankshaft");
        part2.setSeller(user);

        Set<Part> userParts2 = user.getParts();
        userParts2.add(part2);
        user.setParts(userParts2);

        this.partRepo.save(part2);


        Part part3 = new Part();
        part3.setName("Piston");
        part3.setImageUrl("https://s.bashmaistora.bg/product/65/butalo-za-benzinov-dvigatel-honda-025-168471.jpg");
        part3.setMake(make);
        part3.setModel(model);
        part3.setCategory(category);
        part3.setPrice(200);
        part3.setQuantity(2);
        part3.setDescription("BMW Piston");
        part3.setSeller(user);

        Set<Part> userParts3 = user.getParts();
        userParts3.add(part3);
        user.setParts(userParts3);

        this.partRepo.save(part3);


        Part part4 = new Part();
        part4.setName("Gear box");
        part4.setImageUrl("https://media.torque.com.sg/public/2019/03/gearbox-guide.jpg");
        part4.setMake(make);
        part4.setModel(model);
        part4.setCategory(category);
        part4.setPrice(3000);
        part4.setQuantity(1);
        part4.setDescription("BMW Gear Box");
        part4.setSeller(user);

        Set<Part> userParts4 = user.getParts();
        userParts4.add(part4);
        user.setParts(userParts4);

        this.partRepo.save(part4);

        Part part5 = new Part();
        part5.setName("Suspension");
        part5.setImageUrl("https://mytyresite-images.s3.ap-southeast-2.amazonaws.com/news/suspension-components-2023-03-27.jpg");
        part5.setMake(make);
        part5.setModel(model);
        part5.setCategory(category);
        part5.setPrice(200);
        part5.setQuantity(2);
        part5.setDescription("BMW Suspension");
        part5.setSeller(user);

        Set<Part> userParts5 = user.getParts();
        userParts5.add(part5);
        user.setParts(userParts5);

        this.partRepo.save(part5);

        Part part6 = new Part();
        part6.setName("Wheels");
        part6.setImageUrl("https://hips.hearstapps.com/hmg-prod/images/pmx0908119a-1591389137.jpg?crop=0.501xw:1.00xh;0.212xw,0&resize=640:*");
        part6.setMake(make);
        part6.setModel(model);
        part6.setCategory(category);
        part6.setPrice(200);
        part6.setQuantity(2);
        part6.setDescription("BMW Wheels");
        part6.setSeller(user);

        Set<Part> userParts6 = user.getParts();
        userParts6.add(part6);
        user.setParts(userParts6);

        this.partRepo.save(part6);

        Part part7 = new Part();
        part7.setName("Battery");
        part7.setImageUrl("https://i5.walmartimages.com/asr/bac4b4f9-5d19-4e2c-b5f8-7533d420d63a.aaeab574a492d71d7f29f6621334eace.jpeg");
        part7.setMake(make);
        part7.setModel(model);
        part7.setCategory(category);
        part7.setPrice(200);
        part7.setQuantity(2);
        part7.setDescription("BMW Battery");
        part7.setSeller(user);

        Set<Part> userParts7 = user.getParts();
        userParts7.add(part7);
        user.setParts(userParts7);

        this.partRepo.save(part7);
        this.userRepo.save(user);
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
