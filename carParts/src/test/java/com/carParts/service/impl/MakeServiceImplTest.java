package com.carParts.service.impl;

import com.carParts.model.dto.AddMakeDTO;
import com.carParts.model.entity.Category;
import com.carParts.model.entity.Make;
import com.carParts.repository.MakeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class MakeServiceImplTest {

    private final String TEST_MAKE_NAME = "test";
    private final String TEST_NEW_MAKE_NAME = "test123";
    private final String TEST_INVALID_MAKE_NAME = "1234";

    private final Long TEST_INVALID_MAKE_ID = null;

    private MakeServiceImpl toTest;

    private MakeRepo mockMakeRepo;

    private PartServiceImpl mockPartService;

    private ModelServiceImpl mockModelService;

    @BeforeEach
    void setUp() {
        mockMakeRepo = Mockito.mock();
        mockPartService = Mockito.mock();
        mockModelService = Mockito.mock();


        toTest = new MakeServiceImpl(mockMakeRepo, mockPartService, mockModelService);
    }

    @Test
    void testFindMakeByName_MakeFound() {

        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        when(mockMakeRepo.findByName(TEST_MAKE_NAME)).thenReturn(Optional.of(make));

        Make expectedMake = this.toTest.findMakeByName(TEST_MAKE_NAME);

        Assertions.assertInstanceOf(Make.class, expectedMake);
        Assertions.assertEquals(expectedMake.getName(), TEST_MAKE_NAME);
    }

    @Test
    void testFindMakeByName_MakeNotFound() {
        Make expectedMake = this.toTest.findMakeByName(TEST_INVALID_MAKE_NAME);

        Assertions.assertNull(expectedMake);
    }

    @Test
    void testFindMakeById_MakeFound() {

        Make make = new Make();

        when(mockMakeRepo.findById(make.getId())).thenReturn(Optional.of(make));

        Make expectedMake = this.toTest.findMakeById(make.getId());

        Assertions.assertInstanceOf(Make.class, expectedMake);
        Assertions.assertEquals(expectedMake.getId(), make.getId());
    }

    @Test
    void testFindMakeById_MakeNotFound() {

        Make expectedMake = this.toTest.findMakeById(TEST_INVALID_MAKE_ID);

        Assertions.assertNull(expectedMake);
    }

    @Test
    void getAllMakesReturnAList_HasMakes() {
        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        List<Make> makes = new ArrayList<>();
        makes.add(make);

        when(mockMakeRepo.findAll()).thenReturn(makes);

        Assertions.assertEquals(makes.size(), 1);
    }

    @Test
    void getAllMakesReturnAList_HasNoMakes() {
        var makes = toTest.getAllMakes();

        Assertions.assertEquals(makes.size(), 0);
    }

    @Test
    void testEditMakeById() {
        AddMakeDTO addMakeDTO = new AddMakeDTO();
        addMakeDTO.setName(TEST_NEW_MAKE_NAME);

        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        when(mockMakeRepo.findById(null)).thenReturn(Optional.of(make));

        toTest.editMakeById(addMakeDTO, make.getId());

        Assertions.assertEquals(make.getName(), TEST_NEW_MAKE_NAME);
    }

    @Test
    void testCreateMake(){
        AddMakeDTO addMakeDTO = new AddMakeDTO();
        addMakeDTO.setName(TEST_NEW_MAKE_NAME);

        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        when(mockMakeRepo.findById(null)).thenReturn(Optional.of(make));

        Assertions.assertDoesNotThrow(() -> toTest.createMake(addMakeDTO));
    }
    @Test
    void testDeleteMakeById(){
        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        when(mockMakeRepo.findById(null)).thenReturn(Optional.of(make));

        Assertions.assertDoesNotThrow(() -> toTest.deleteMakeById(null));
    }

    @Test
    void addModelViewThrowsErrorWHenModelIsEmpty(){

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.addModelView(null),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void editMakesViewThrowsErrorWHenModelIsEmpty(){

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.editMakesView(null),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void editMakeViewThrowsErrorWHenModelIsEmpty(){

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.editMakeView(null, Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }

    @Test
    void chooseMakeViewWorks(){
        Assertions.assertDoesNotThrow(()->this.toTest.chooseMakeView(Mockito.mock()));
    }

    @Test
    void shopModelsViewThrowsWithInvalidMakeName(){
        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.shopModelsView(Mockito.mock(),Mockito.mock()),
                "Expected doThing() to throw, but it didn't"
        );
    }
}
