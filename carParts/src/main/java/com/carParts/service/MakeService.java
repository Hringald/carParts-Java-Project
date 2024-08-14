package com.carParts.service;


import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.entity.Make;
import org.springframework.ui.Model;

import java.util.List;

public interface MakeService {

    List<Make> getAllMakes();

    Make findMakeByName(String makeName);

    Make findMakeById(Long id);

    void editMakeById(AddMakeDTO addMakeDTO, Long id);

    void createMake(AddMakeDTO addMakeDTO);

    void deleteMakeById(Long id);

    void initMakes();

    void addModelView(Model model);

    void editMakesView(Model model);

    void editMakeView(Long makeId, Model model);

    void chooseMakeView(Model model);

    void shopModelsView(String makeName, Model model);

}
