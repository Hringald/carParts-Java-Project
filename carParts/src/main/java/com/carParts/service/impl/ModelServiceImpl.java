package com.carParts.service.impl;

import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.repository.MakeRepo;
import com.carParts.repository.ModelRepo;
import com.carParts.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepo modelRepo;
    private final MakeRepo makeRepo;



    public ModelServiceImpl(ModelRepo modelRepo, MakeRepo makeRepo) {
        this.modelRepo = modelRepo;
        this.makeRepo = makeRepo;
    }

    @Override
    public Set<Model> findModelByMake(Make make) {
        return this.modelRepo.findByMake(make);
    }

    @Override
    public void initModels() {
        Make make = this.makeRepo.findByName("BMW").orElse(null);
        Set<Model> makeModels = make.getModels();

        Model model = new Model();
        model.setName("3 Series");
        model.setMake(make);
        model.setImageUrl("https://www.topgear.com/sites/default/files/2022/09/1-BMW-3-Series.jpg");

        makeModels.add(model);
        make.setModels(makeModels);

        this.modelRepo.save(model);
        this.makeRepo.save(make);
    }

    @Override
    public void deleteModelById(Long id) {
        Model currentModel = this.modelRepo.findById(id).orElse(null);
        this.modelRepo.delete(currentModel);
    }

    @Override
    public Model findModelById(Long id) {
        return this.modelRepo.findById(id).orElse(null);
    }

    @Override
    public List<Model> getAllModels() {
        return this.modelRepo.findAll();
    }

    @Override
    public void editModelById(AddModelDTO addModelDTO, Long id) {
        Model model = this.modelRepo.findById(id).orElse(null);

        model.setName(addModelDTO.getName());

        Make make = this.makeRepo.findByName(addModelDTO.getMakeName()).orElse(null);

        model.setMake(make);

        model.setImageUrl(addModelDTO.getImageUrl());

        this.modelRepo.save(model);
    }

    @Override
    public void createModel(AddModelDTO addModelDTO) {
        Model model = new Model();

        model.setName(addModelDTO.getName());

        Make make = this.makeRepo.findByName(addModelDTO.getMakeName()).orElse(null);

        model.setMake(make);

        model.setImageUrl(addModelDTO.getImageUrl());

        //Admin admin = adminService.findAdminByUsername(adminUser.getUsername());

        //model.setAdmin(admin);

        this.modelRepo.save(model);
    }

    @Override
    public void deleteAllModelsByMake(Make make) {
        Set<Model> models = this.modelRepo.findByMake(make);

        for (Model model : models) {
            this.modelRepo.delete(model);
        }
    }
}
