package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.entity.Make;
import com.carParts.repository.MakeRepo;
import com.carParts.repository.ModelRepo;
import com.carParts.service.HomeService;
import com.carParts.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private final MakeServiceImpl makeService;

    private final ModelMapper modelMapper;

    public HomeServiceImpl(MakeServiceImpl makeService) {
        this.makeService = makeService;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public void indexView(Model model) {
        List<Make> allMakes = this.makeService.getAllMakes();

        List<AddMakeDTO> allMakesDTOs = allMakes
                .stream()
                .map(make -> modelMapper.map(make, AddMakeDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("allMakes", allMakesDTOs);
    }
}
