package com.carParts.service;

import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;

import java.util.List;

public interface PartService {

    List<Part> findOwnedParts(User user);
}
