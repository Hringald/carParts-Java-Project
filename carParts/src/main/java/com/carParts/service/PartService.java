package com.carParts.service;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;

import java.util.List;

public interface PartService {

    List<Part> findOwnedParts(User user);

    void addPart(AddPartDTO addPartDTO, User currentUser);
    void editPart(Part currentPart, AddPartDTO addPartDTO);

    void removePartById(Long id, User currentUser);

    Part findPartById(Long id);
}
