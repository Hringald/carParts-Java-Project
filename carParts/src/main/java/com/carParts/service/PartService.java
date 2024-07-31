package com.carParts.service;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PartService {

    List<Part> findOwnedParts(User user);

    List<Part> findAllParts();

    void addPart(AddPartDTO addPartDTO, User currentUser);

    void editPart(Part currentPart, AddPartDTO addPartDTO);

    void removePart(Part partToDelete, User currentUser);

    Part findPartById(Long id);

    void initParts();

    Page<Part> findPaginated(int pageNo, int pageSize, String makeName, String modelName, String categoryName, String searchTerm);

    void deleteAllPartsByMake(Make make);
}
