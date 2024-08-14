package com.carParts.service.impl;

import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

class ModelServiceImplTest {
    private ModelServiceImpl toTest;

    private MakeRepo mockMakeRepo;

    private ModelRepo mockModelRepo;

    private PartRepo mockPartRepo;

    @BeforeEach
    void setUp() {
        mockMakeRepo = Mockito.mock();
        mockModelRepo = Mockito.mock();
        mockPartRepo = Mockito.mock();

        toTest = new ModelServiceImpl(mockModelRepo, mockMakeRepo, mockPartRepo);
    }

    @Test
    void testFindModelByMake_ModelFound() {

        Model model = new Model();

        Make make = new Make();
        model.setMake(make);

        Set<Model> models = new HashSet<>();
        models.add(model);

        when(mockModelRepo.findByMake(model.getMake())).thenReturn(models);

        Set<Model> expectedModels = this.toTest.findModelByMake(model.getMake());

        Assertions.assertEquals(expectedModels.stream().count(), 1);
    }

    @Test
    void testFindModelByMake_ModelNotFound() {
        Model model = new Model();
        Set<Model> expectedModels = this.toTest.findModelByMake(model.getMake());

        Assertions.assertEquals(expectedModels, new HashSet<>());
    }

    @Test
    void testFindId_ModelFound() {

        Model model = new Model();

        when(mockModelRepo.findById(model.getId())).thenReturn(Optional.of(model));

        Model expectedModel = this.toTest.findModelById(model.getId());

        Assertions.assertInstanceOf(Model.class, expectedModel);
        Assertions.assertEquals(expectedModel.getId(), model.getId());
    }

    @Test
    void testFindById_ModelNotFound() {

        Model expectedModel = this.toTest.findModelById(null);

        Assertions.assertNull(expectedModel);
    }

    @Test
    void testGetAllModels_Parts_notFound() {

        List<Model> allModels = toTest.getAllModels();
        Assertions.assertEquals(allModels, new ArrayList<>());
    }

    @Test
    void testGetAllModels_Parts_Found() {

        Model model = new Model();
        List<Model> models = new ArrayList<>();
        models.add(model);

        when(mockModelRepo.findAll()).thenReturn(models);

        List<Model> allModels = toTest.getAllModels();
        Assertions.assertEquals(allModels.stream().count(), 1);
    }
}
