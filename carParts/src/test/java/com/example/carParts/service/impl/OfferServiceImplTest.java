package com.example.carParts.service.impl;

import com.carParts.model.entity.Admin;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.User;
import com.carParts.repository.AdminRepo;
import com.carParts.repository.OfferRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.util.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

class OfferServiceImplTest {

    private final String TEST_USERNAME = "test";
    private final String TEST_INVALID_USERNAME = "1234";

    private OfferServiceImpl toTest;
    private UserRepo mockUserRepo;
    private OfferRepo mockOfferRepo;

    private PartRepo mockPartRepo;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockOfferRepo = Mockito.mock();
        mockPartRepo = Mockito.mock();

        toTest = new OfferServiceImpl(mockUserRepo, mockOfferRepo, mockPartRepo);
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

        Assertions.assertNull(expectedOffers);
    }

    @Test
    void testFindId_OfferFound() {

        Offer offer = new Offer();

        when(toTest.findById(offer.getId())).thenReturn(offer);

        Offer expectedOffer = this.toTest.findById(offer.getId());

        Assertions.assertInstanceOf(Offer.class, expectedOffer);
        Assertions.assertEquals(expectedOffer.getId(), offer.getId());
    }

    @Test
    void testFindById_OfferNotFound() {

        Offer expectedOffer = this.toTest.findById(null);

        Assertions.assertNull(expectedOffer);
    }
}
