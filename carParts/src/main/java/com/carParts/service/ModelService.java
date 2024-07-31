package com.carParts.service;

import com.carParts.model.dto.AddModelDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;

import java.util.List;
import java.util.Set;

public interface ModelService {

    Set<Model> findModelByMake(Make make);

    void initModels();

    void deleteModelById(Long id);

    Model findModelById(Long id);

    List<Model> getAllModels();

    void editModelById(AddModelDTO addModelDTO, Long id);

    void createModel(AddModelDTO addModelDTO);

    void deleteAllModelsByMake(Make make);
}
