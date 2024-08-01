package com.carParts.API;

import com.carParts.model.entity.Part;
import com.carParts.service.impl.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/allParts")
public class AllPartsAPI {
    private final PartServiceImpl partService;

    @Autowired

    public AllPartsAPI(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partService.findAllParts();
    }
}
