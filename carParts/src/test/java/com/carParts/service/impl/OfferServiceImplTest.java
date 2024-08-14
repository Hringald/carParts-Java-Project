package com.carParts.service.impl;

import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.OfferRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.parameters.P;

import java.util.*;

import static org.mockito.Mockito.when;

class OfferServiceImplTest {
    private final String TEST_NAME = "test";
    private OfferServiceImpl toTest;
    private UserRepo mockUserRepo;
    private OfferRepo mockOfferRepo;

    private PartRepo mockPartRepo;

    private PartServiceImpl mockPartService;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockOfferRepo = Mockito.mock();
        mockPartRepo = Mockito.mock();
        mockPartService = Mockito.mock();

        toTest = new OfferServiceImpl(mockUserRepo, mockOfferRepo, mockPartRepo, mockPartService);
    }

    @Test
    void testFindOwnedOffers_OffersFound() {

        User user = new User();

        Offer offer = new Offer();

        Set<Offer> offers = new HashSet<>();
        offers.add(offer);

        List<Offer> offersList = new ArrayList<>();
        offersList.add(offer);

        user.setOffers(offers);

        when(this.toTest.findOwnedOffers(user)).thenReturn(offersList);

        List<Offer> expectedOffers = this.toTest.findOwnedOffers(user);

        Assertions.assertEquals(new HashSet<Offer>(expectedOffers), user.getOffers());
    }

    @Test
    void testFindOwnedOffers_OffersNotFound() {
        List<Offer> expectedOffers = this.toTest.findOwnedOffers(null);

        Assertions.assertEquals(expectedOffers, new ArrayList<>());
    }

    @Test
    void testFindId_OfferFound() {

        Offer offer = new Offer();

        when(mockOfferRepo.findById(offer.getId())).thenReturn(Optional.of(offer));

        Offer expectedOffer = this.toTest.findById(offer.getId());

        Assertions.assertInstanceOf(Offer.class, expectedOffer);
        Assertions.assertEquals(expectedOffer.getId(), offer.getId());
    }

    @Test
    void testFindById_OfferNotFound() {

        Offer expectedOffer = this.toTest.findById(null);

        Assertions.assertNull(expectedOffer);
    }

    @Test
    void addOfferAddsAnOffer() {

        AddOfferDTO addOfferDTO = new AddOfferDTO();

        User user = new User();

        Part part = new Part();
        part.setSeller(user);

        when(this.mockPartService.findPartById(null)).thenReturn(part);

        Assertions.assertDoesNotThrow(() -> toTest.addOffer(addOfferDTO, null));
    }

    @Test
    void declineOfferWorks() {

        Offer offer = new Offer();
        Part part = new Part();
        offer.setPart(part);

        User user = new User();

        offer.setSeller(user);

        Set<Offer> offers = new HashSet<>();
        offers.add(offer);

        user.setOffers(offers);

        when(this.mockOfferRepo.findById(null)).thenReturn(Optional.of(offer));

        Assertions.assertDoesNotThrow(() -> toTest.declineOffer(null));
    }

    @Test
    void sellOfferWorks() {

        Offer offer = new Offer();
        Part part = new Part();
        offer.setPart(part);

        User user = new User();

        offer.setSeller(user);

        Set<Offer> offers = new HashSet<>();
        offers.add(offer);

        user.setOffers(offers);

        when(this.mockOfferRepo.findById(null)).thenReturn(Optional.of(offer));

        Assertions.assertDoesNotThrow(() -> toTest.sellOffer(null));
    }

    @Test
    void myOffersViewThrowsErrorWhenViewIsEmpty() {

        User user = new User();

        Offer offer = new Offer();
        offer.setName(TEST_NAME);
        offer.setEmail(TEST_NAME);
        offer.setZipCode(TEST_NAME);
        offer.setCity(TEST_NAME);

        List<Offer> offers = new ArrayList<>();
        offers.add(offer);

        when(this.mockUserRepo.findByEmail(null)).thenReturn(Optional.of(user));
        when(this.mockOfferRepo.findBySeller(user)).thenReturn(offers);

        Assertions.assertThrows(
                Exception.class,
                () -> this.toTest.myOffersView(Mockito.mock(), null),
                "Expected doThing() to throw, but it didn't"
        );
    }
}
