package com.carParts.service.impl;

import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

class PartServiceImplTest {
    private PartServiceImpl toTest;
    private UserRepo mockUserRepo;

    private PartRepo mockPartRepo;

    private CategoryRepo mockCategoryRepo;

    private MakeRepo mockMakeRepo;

    private ModelRepo mockModelRepo;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockPartRepo = Mockito.mock();
        mockCategoryRepo = Mockito.mock();
        mockMakeRepo = Mockito.mock();
        mockModelRepo = Mockito.mock();

        toTest = new PartServiceImpl(mockUserRepo, mockPartRepo, mockCategoryRepo, mockMakeRepo, mockModelRepo);
    }

    @Test
    void testFindAllParts_Parts_notFound() {

        List<Part> allParts = toTest.findAllParts();
        Assertions.assertEquals(allParts, new ArrayList<>());
    }

    @Test
    void testFindAllParts_Parts_Found() {

        Part part = new Part();
        List<Part> parts = new ArrayList<>();
        parts.add(part);

        when(mockPartRepo.findAll()).thenReturn(parts);

        List<Part> allParts = toTest.findAllParts();
        Assertions.assertEquals(allParts.stream().count(), 1);
    }

    @Test
    void testFindOwnedParts_Parts_notFound() {

        User user = new User();

        Set<Part> parts = new HashSet<>();

        user.setParts(parts);

        List<Part> ownedParts = toTest.findOwnedParts(user);
        Assertions.assertEquals(ownedParts, new ArrayList<>());
    }

    @Test
    void testFindOwnedParts_Parts_Found() {

        User user = new User();

        Part part = new Part();
        Set<Part> parts = new HashSet<>();
        parts.add(part);

        user.setParts(parts);

        when(mockPartRepo.findBySeller(user)).thenReturn(user.getParts().stream().toList());

        List<Part> ownedParts = toTest.findOwnedParts(user);
        Assertions.assertEquals(ownedParts.stream().count(), 1);
    }

    @Test
    void testFindId_PartFound() {

        Part part = new Part();

        when(mockPartRepo.findById(part.getId())).thenReturn(Optional.of(part));

        Part expectedPart = this.toTest.findPartById(part.getId());

        Assertions.assertInstanceOf(Part.class, expectedPart);
        Assertions.assertEquals(expectedPart.getId(), part.getId());
    }

    @Test
    void testFindById_PartNotFound() {

        Part expectedPart = this.toTest.findPartById(null);

        Assertions.assertNull(expectedPart);
    }
}
