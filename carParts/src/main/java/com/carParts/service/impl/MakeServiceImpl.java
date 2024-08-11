package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.repository.MakeRepo;
import com.carParts.service.MakeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepo makeRepo;

    private final PartServiceImpl partService;

    private final ModelServiceImpl modelService;


    public MakeServiceImpl(MakeRepo makeRepo, PartServiceImpl partService, ModelServiceImpl modelService) {

        this.makeRepo = makeRepo;
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
        ModelMapper modelMapper = new ModelMapper();

        Make make = modelMapper.map(addMakeDTO, Make.class);

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
