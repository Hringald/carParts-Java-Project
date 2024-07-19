package com.carParts.service;


import com.carParts.model.entity.Category;
import com.carParts.model.entity.CategoryEnum;
import com.carParts.model.entity.Make;

import java.util.List;
import java.util.Set;

public interface MakeService {

    List<Make> getAllMakes();

    Make findMakeByName(String makeName);

    void initMakes();

}
