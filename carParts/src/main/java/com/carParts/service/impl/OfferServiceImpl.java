package com.carParts.service.impl;

import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.OfferRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.OfferService;
import com.carParts.service.PartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final UserRepo userRepo;

    private final OfferRepo offerRepo;

    public OfferServiceImpl(UserRepo userRepo, OfferRepo offerRepo) {
        this.userRepo = userRepo;
        this.offerRepo = offerRepo;
    }
    @Override
    public List<Offer> findOwnedOffers(User user){
     return this.offerRepo.findBySeller(user);
    }
}
