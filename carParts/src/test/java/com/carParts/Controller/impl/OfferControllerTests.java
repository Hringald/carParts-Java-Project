
package com.carParts.Controller.impl;

import com.carParts.controller.impl.OfferControllerImpl;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.service.impl.PartServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

public class OfferControllerTests {
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PHONE = "1234745784";
    private final String TEST_CITY = "London";
    private final String TEST_ZIP = "1245";
    private final String TEST_ADDRESS = "test address 1st 20 street";

    private final String TEST_NAME = "testName";

    private final String TEST_PART_DESCRIPTION = "testPartDescriptionTestPartDescription";

    private final int TEST_PART_QUANTITY = 2;
    private final Double TEST_PART_PRICE = 2.50;

    private final String TEST_IMAGE_URL = "testImageUrl";
    private OfferControllerImpl toTest;
    private OfferServiceImpl mockOfferService;
    private UserServiceImpl mockUserService;

    private PartServiceImpl mockPartService;
    private UserDetails mockUserDetails;

    @BeforeEach
    void setUp() {
        mockUserService = Mockito.mock();
        mockOfferService = Mockito.mock();
        mockPartService = Mockito.mock();
        mockUserDetails = Mockito.mock();

        toTest = new OfferControllerImpl(mockUserService, mockOfferService, mockPartService);
    }

    @Test
    void myOffersReturnsCorrectViewWithModel() {

        String result = toTest.myOffers(mockUserDetails, Mockito.mock());

        Assertions.assertEquals(result, "Offer/MyOffers");
    }

    @Test
    void createOfferReturnsCorrectViewWithModel() {

        Part part = new Part();
        part.setImageUrl(TEST_IMAGE_URL);
        part.setName(TEST_NAME);
        part.setDescription(TEST_PART_DESCRIPTION);
        part.setQuantity(TEST_PART_QUANTITY);
        part.setPrice(TEST_PART_PRICE);

        when(mockPartService.findPartById(null)).thenReturn(part);

        String result = toTest.createOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "Offer/CreateOffer");
    }

    @Test
    void viewOfferReturnsCorrectViewWithModel() {
        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPhone(TEST_PHONE);

        Part part = new Part();
        part.setImageUrl(TEST_IMAGE_URL);
        part.setName(TEST_NAME);
        part.setDescription(TEST_PART_DESCRIPTION);
        part.setQuantity(TEST_PART_QUANTITY);
        part.setPrice(TEST_PART_PRICE);
        part.setSeller(user);

        Offer offer = new Offer();
        offer.setName(TEST_NAME);
        offer.setEmail(TEST_EMAIL);
        offer.setPhone(TEST_PHONE);
        offer.setAddress(TEST_ADDRESS);
        offer.setCity(TEST_CITY);
        offer.setZipCode(TEST_ZIP);
        offer.setPart(part);


        when(mockUserService.findUserByEmail(null)).thenReturn(user);
        when(mockPartService.findPartById(null)).thenReturn(part);
        when(mockOfferService.findById(null)).thenReturn(offer);

        String result = toTest.viewOffer(mockUserDetails, null, Mockito.mock());

        Assertions.assertEquals(result, "Offer/OfferInfo");
    }

    @Test
    void declineOfferReturnsCorrectViewWithModel() {

        Part part = new Part();
        part.setImageUrl(TEST_IMAGE_URL);
        part.setName(TEST_NAME);
        part.setDescription(TEST_PART_DESCRIPTION);
        part.setQuantity(TEST_PART_QUANTITY);
        part.setPrice(TEST_PART_PRICE);

        Offer offer = new Offer();
        offer.setName(TEST_NAME);
        offer.setEmail(TEST_EMAIL);
        offer.setPhone(TEST_PHONE);
        offer.setAddress(TEST_ADDRESS);
        offer.setCity(TEST_CITY);
        offer.setZipCode(TEST_ZIP);

        when(mockPartService.findPartById(null)).thenReturn(part);
        when(mockOfferService.findById(null)).thenReturn(offer);

        String result = toTest.declineOffer(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }
}
