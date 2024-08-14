package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.Make;
import com.carParts.repository.MakeRepo;
import com.carParts.service.MakeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepo makeRepo;

    private final PartServiceImpl partService;

    private final ModelServiceImpl modelService;
    private final ModelMapper modelMapper;

    public MakeServiceImpl(MakeRepo makeRepo, PartServiceImpl partService, ModelServiceImpl modelService) {

        this.makeRepo = makeRepo;
        this.partService = partService;
        this.modelService = modelService;
        this.modelMapper = new ModelMapper();
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

    @Override
    public void addModelView(Model model) {
        List<Make> allMakes = getAllMakes();

        List<AddMakeDTO> allMakesDTOs = allMakes
                .stream()
                .map(make -> modelMapper.map(make, AddMakeDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("allMakes", allMakesDTOs);
    }

    @Override
    public void editMakesView(Model model) {
        List<Make> allMakes = getAllMakes();

        List<AddMakeDTO> allMakesDTOs = allMakes
                .stream()
                .map(make -> modelMapper.map(make, AddMakeDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("makes", allMakesDTOs);

    }

    @Override
    public void editMakeView(Long makeId, Model model) {
        Make make = findMakeById(makeId);

        AddMakeDTO makeDTO = modelMapper.map(make, AddMakeDTO.class);

        model.addAttribute("makeName", makeDTO.getName());
        model.addAttribute("makeId", makeDTO.getId());
        model.addAttribute("makeName", makeDTO.getName());
        model.addAttribute("makeUrl", makeDTO.getImageUrl());
    }

    @Override
    public void chooseMakeView(Model model) {
        List<Make> allMakes = getAllMakes();

        List<AddMakeDTO> allMakesDTOs = allMakes
                .stream()
                .map(make -> modelMapper.map(make, AddMakeDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("allMakes", allMakesDTOs);
    }

    @Override
    public void shopModelsView(String makeName, Model model) {
        Make make = findMakeByName(makeName);
        Set<com.carParts.model.entity.Model> modelsByMake = this.modelService.findModelByMake(make);

        List<AddModelDTO> modelsByMakeDTOs = modelsByMake
                .stream()
                .map(makeObject -> modelMapper.map(makeObject, AddModelDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelsByMake", modelsByMakeDTOs);
    }
}
