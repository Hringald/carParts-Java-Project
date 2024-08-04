package com.carParts.service.impl;

import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.OfferRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private final UserRepo userRepo;

    private final OfferRepo offerRepo;

    private final PartRepo partRepo;

    private final PartServiceImpl partService;

    public OfferServiceImpl(UserRepo userRepo, OfferRepo offerRepo, PartRepo partRepo, PartServiceImpl partService) {
        this.userRepo = userRepo;
        this.offerRepo = offerRepo;
        this.partRepo = partRepo;
        this.partService = partService;
    }

    @Override
    public Offer findById(Long id) {
        return this.offerRepo.findById(id).orElse(null);
    }

    @Override
    public List<Offer> findOwnedOffers(User user) {
        return this.offerRepo.findBySeller(user);
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO, Part part, User seller) {
        Offer newOffer = new Offer();

        newOffer.setName(addOfferDTO.getName());
        newOffer.setAddress(addOfferDTO.getAddress());
        newOffer.setCity(addOfferDTO.getCity());
        newOffer.setEmail(addOfferDTO.getEmail());
        newOffer.setPhone(addOfferDTO.getPhone());
        newOffer.setZipCode(addOfferDTO.getZipCode());

        part.setOffer(newOffer);

        newOffer.setPart(part);
        newOffer.setSeller(seller);

        this.offerRepo.save(newOffer);
        this.partRepo.save(part);
    }

    @Override
    public void declineOffer(Offer currentOffer) {
        User seller = currentOffer.getSeller();
        Set<Offer> sellerOffers = seller.getOffers();
        sellerOffers.remove(currentOffer);
        seller.setOffers(sellerOffers);

        Part currentPart = currentOffer.getPart();
        currentPart.setOffer(null);

        currentOffer.setPart(null);

        this.userRepo.save(seller);
        this.partRepo.save(currentPart);
        this.offerRepo.save(currentOffer);
        this.offerRepo.delete(currentOffer);
    }

    @Override
    public void sellOffer(Offer currentOffer, Part currentPart) {
        if (currentPart.getQuantity() - 1 <= 0) {
            this.partService.removePart(currentPart.getId());
        } else {
            currentPart.setQuantity(currentPart.getQuantity() - 1);
            declineOffer(currentOffer);
        }


    }

}
