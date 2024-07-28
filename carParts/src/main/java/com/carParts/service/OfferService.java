package com.carParts.service;

import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;

import java.util.List;

public interface OfferService {

    Offer findById(Long id);
    List<Offer> findOwnedOffers(User user);

    void addOffer(AddOfferDTO addOfferDTO, Part part, User seller);
    void declineOffer(Offer currentOffer);

    void sellOffer(Offer currentOffer, Part currentPart);
}
