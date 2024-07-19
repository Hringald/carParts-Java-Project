package com.carParts.service;

import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;

import java.util.List;

public interface OfferService {

    List<Offer> findOwnedOffers(User user);
}
