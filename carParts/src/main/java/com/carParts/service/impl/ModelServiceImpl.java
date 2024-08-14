package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.Offer;
import com.carParts.repository.MakeRepo;
import com.carParts.repository.ModelRepo;
import com.carParts.repository.PartRepo;
import com.carParts.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepo modelRepo;
    private final MakeRepo makeRepo;
    private final PartRepo partRepo;
    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepo modelRepo, MakeRepo makeRepo, PartRepo partRepo) {
        this.modelRepo = modelRepo;
        this.makeRepo = makeRepo;
        this.modelMapper = new ModelMapper();
        this.partRepo = partRepo;
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

        for (var part : currentModel.getParts()
        ) {
            part.setModel(null);
            part.setSeller(null);
            part.setOffer(null);

            Make make = part.getMake();
            var parts = make.getParts();
            parts.remove(part);
            make.setParts(parts);

            part.setMake(null);

            partRepo.save(part);
            partRepo.delete(part);
        }
        currentModel.setParts(new HashSet<>());

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
        ModelMapper modelMapper = new ModelMapper();

        Model model = modelMapper.map(addModelDTO, Model.class);

        Make make = this.makeRepo.findByName(addModelDTO.getMakeName()).orElse(null);
        model.setMake(make);

        model.setImageUrl(addModelDTO.getImageUrl());

        this.modelRepo.save(model);
    }

    @Override
    public void deleteAllModelsByMake(Make make) {
        Set<Model> models = this.modelRepo.findByMake(make);

        for (Model model : models) {
            this.modelRepo.delete(model);
        }
    }

    @Override
    public void editModelsView(org.springframework.ui.Model model) {
        List<com.carParts.model.entity.Model> models = getAllModels();

        List<AddModelDTO> modelsDTOs = models
                .stream()
                .map(carModel -> modelMapper.map(carModel, AddModelDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("models", modelsDTOs);
    }

    @Override
    public void editModelView(Long modelId, org.springframework.ui.Model model) {

        List<Make> allMakes = this.makeRepo.findAll();

        List<AddMakeDTO> allMakesDTOs = allMakes
                .stream()
                .map(make -> modelMapper.map(make, AddMakeDTO.class))
                .collect(Collectors.toList());

        com.carParts.model.entity.Model carModel = findModelById(modelId);

        AddModelDTO modelDTO = modelMapper.map(carModel, AddModelDTO.class);

        model.addAttribute("makeName", modelDTO.getMakeName());
        model.addAttribute("modelId", modelDTO.getId());
        model.addAttribute("modelName", modelDTO.getName());
        model.addAttribute("modelUrl", modelDTO.getImageUrl());
        model.addAttribute("allMakes", allMakesDTOs);
    }
}
