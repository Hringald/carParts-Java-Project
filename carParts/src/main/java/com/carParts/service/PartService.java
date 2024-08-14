package com.carParts.service;

import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.List;

public interface PartService {

    List<Part> findOwnedParts(User user);

    List<Part> findAllParts();

    void addPart(AddPartDTO addPartDTO, UserDetails userDetails);

    void editPart(Part currentPart, AddPartDTO addPartDTO);

    void removePart(Long id);

    Part findPartById(Long id);

    void initParts();

    Page<Part> findPaginated(int pageNo, int pageSize, String makeName, String modelName, String categoryName, String searchTerm);

    void deleteAllPartsByMake(Make make);

    void myPartsView(UserDetails userDetails, Model model);

    void addPartView(String makeName, Model model);

    void editPartByIdView(UserDetails userDetails, long partId, Model model);
}
