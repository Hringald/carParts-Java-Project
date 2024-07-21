package com.carParts.service;

import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;

import java.util.List;
import java.util.Set;

public interface ModelService {

    Set<Model> findModelByMake(Make make);
    void initModels();
}
