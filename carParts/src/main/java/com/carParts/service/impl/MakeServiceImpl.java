package com.carParts.service.impl;

import com.carParts.model.entity.Make;
import com.carParts.model.entity.User;
import com.carParts.repository.MakeRepo;
import com.carParts.service.MakeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepo makeRepo;

    public MakeServiceImpl(MakeRepo makeRepo) {
        this.makeRepo = makeRepo;
    }

    @Override
    public List<Make> getAllMakes() {
        return this.makeRepo.findAll();
    }

    @Override
    public Make findMakeByName(String makeName){return this.makeRepo.findByName(makeName).orElse(null);}
    @Override
    public void initMakes(){
        Make make = new Make();

        make.setName("BMW");
        make.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/800px-BMW.svg.png");

        this.makeRepo.save(make);
    }
}
