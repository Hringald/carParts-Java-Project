package com.carParts.service;

import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.List;

public interface OfferService {

    Offer findById(Long id);

    List<Offer> findOwnedOffers(User user);

    void addOffer(AddOfferDTO addOfferDTO, Long PartId);

    void declineOffer(Long offerId);

    void sellOffer(Long offerId);

    void myOffersView(UserDetails userDetails, Model model);

    void createOfferView(Long offerId, Model model);

    boolean isUserSeller(UserDetails userDetails, Long partId);

    void viewOfferView(UserDetails userDetails, Model model, Long partId);
}
