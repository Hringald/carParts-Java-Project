package com.carParts.service.impl;

import com.carParts.model.entity.Make;
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
}
