package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.OfferControllerImpl;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.service.impl.PartServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class OfferControllerTests {

    private OfferControllerImpl toTest;
    private LoggedUser mockLoggedUser;
    private OfferServiceImpl mockOfferService;
    private UserServiceImpl mockUserService;

    private PartServiceImpl mockPartService;

    @BeforeEach
    void setUp() {
        mockLoggedUser = Mockito.mock();
        mockUserService = Mockito.mock();
        mockOfferService = Mockito.mock();
        mockPartService = Mockito.mock();

        toTest = new OfferControllerImpl(mockLoggedUser, mockUserService, mockOfferService, mockPartService);
    }

    @Test
    void myOffersReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.myOffers(Mockito.mock());

        Assertions.assertEquals(result, "MyOffers");
    }

    @Test
    void myOffersReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.myOffers(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void createOfferReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();
        part.setImageUrl("test");
        part.setName("test");
        part.setDescription("test");
        part.setQuantity(1);
        part.setPrice(1.0);

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockPartService.findPartById(null)).thenReturn(part);

        String result = toTest.createOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "CreateOffer");
    }

    @Test
    void createOfferReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.createOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void viewOfferReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();
        part.setImageUrl("test");
        part.setName("test");
        part.setDescription("test");
        part.setQuantity(1);
        part.setPrice(1.0);

        Offer offer = new Offer();
        offer.setName("test");
        offer.setEmail("test@abv.bg");
        offer.setPhone("1234123123");
        offer.setAddress("testtesttest");
        offer.setCity("testCity");
        offer.setZipCode("1122");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockPartService.findPartById(null)).thenReturn(part);
        when(mockOfferService.findById(null)).thenReturn(offer);

        String result = toTest.viewOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "OfferInfo");
    }

    @Test
    void viewOfferReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.viewOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void declineOfferReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();
        part.setImageUrl("test");
        part.setName("test");
        part.setDescription("test");
        part.setQuantity(1);
        part.setPrice(1.0);

        Offer offer = new Offer();
        offer.setName("test");
        offer.setEmail("test@abv.bg");
        offer.setPhone("1234123123");
        offer.setAddress("testtesttest");
        offer.setCity("testCity");
        offer.setZipCode("1122");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockPartService.findPartById(null)).thenReturn(part);
        when(mockOfferService.findById(null)).thenReturn(offer);

        String result = toTest.declineOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void sellOfferReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();
        part.setImageUrl("test");
        part.setName("test");
        part.setDescription("test");
        part.setQuantity(1);
        part.setPrice(1.0);

        Offer offer = new Offer();
        offer.setName("test");
        offer.setEmail("test@abv.bg");
        offer.setPhone("1234123123");
        offer.setAddress("testtesttest");
        offer.setCity("testCity");
        offer.setZipCode("1122");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockPartService.findPartById(null)).thenReturn(part);
        when(mockOfferService.findById(null)).thenReturn(offer);

        String result = toTest.sellOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

}
