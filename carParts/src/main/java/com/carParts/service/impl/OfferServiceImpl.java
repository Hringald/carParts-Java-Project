package com.carParts.service.impl;

import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.dto.OfferViewDTO;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.OfferRepo;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final UserRepo userRepo;

    private final OfferRepo offerRepo;

    private final PartRepo partRepo;

    private final PartServiceImpl partService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(UserRepo userRepo, OfferRepo offerRepo, PartRepo partRepo, PartServiceImpl partService) {
        this.userRepo = userRepo;
        this.offerRepo = offerRepo;
        this.partRepo = partRepo;
        this.partService = partService;
        this.modelMapper = new ModelMapper();
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
    public void addOffer(AddOfferDTO addOfferDTO, Long partId) {

        Part part = this.partService.findPartById(partId);
        User seller = part.getSeller();

        ModelMapper modelMapper = new ModelMapper();

        Offer newOffer = modelMapper.map(addOfferDTO, Offer.class);

        part.setOffer(newOffer);

        newOffer.setPart(part);
        newOffer.setSeller(seller);

        this.offerRepo.save(newOffer);
        this.partRepo.save(part);
    }

    @Override
    public void declineOffer(Long offerId) {
        Offer currentOffer = findById(offerId);

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
    public void sellOffer(Long offerId) {
        Offer currentOffer = findById(offerId);
        Part currentPart = currentOffer.getPart();

        if (currentPart.getQuantity() - 1 <= 0) {
            this.partService.removePart(currentPart.getId());
            declineOffer(currentOffer.getId());
        } else {
            currentPart.setQuantity(currentPart.getQuantity() - 1);
            this.partRepo.save(currentPart);
            declineOffer(currentOffer.getId());
        }
    }

    @Override
    public void myOffersView(UserDetails userDetails, Model model) {
        User currentUser = this.userRepo.findByEmail(userDetails.getUsername()).orElse(null);
        List<Offer> myOffers = findOwnedOffers(currentUser);

        List<OfferViewDTO> myOffersDTOs = myOffers
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("myOffers", myOffersDTOs);
    }

    @Override
    public void createOfferView(Long offerId, Model model) {
        Part partToBuy = this.partService.findPartById(offerId);

        AddPartDTO partDTO = modelMapper.map(partToBuy, AddPartDTO.class);

        model.addAttribute("partId", partDTO.getId());
        model.addAttribute("partUrl", partDTO.getImageUrl());
        model.addAttribute("partName", partDTO.getName());
        model.addAttribute("partDescription", partDTO.getDescription());
        model.addAttribute("partQuantity", partDTO.getQuantity());
        model.addAttribute("partPrice", partDTO.getPrice());
    }

    @Override
    public boolean isUserSeller(UserDetails userDetails, Long partId) {
        Offer currentOffer = findById(partId);
        Part currentPart = currentOffer.getPart();
        User partSeller = currentPart.getSeller();

        User currentUser = this.userRepo.findByEmail(userDetails.getUsername()).orElse(null);

        return !Objects.equals(currentUser.getId(), partSeller.getId());
    }

    @Override
    public void viewOfferView(UserDetails userDetails, Model model, Long partId) {
        Offer currentOffer = findById(partId);
        Part currentPart = currentOffer.getPart();

        AddPartDTO partDTO = modelMapper.map(currentPart, AddPartDTO.class);
        AddOfferDTO offerDTO = modelMapper.map(currentOffer, AddOfferDTO.class);

        model.addAttribute("partUrl", partDTO.getImageUrl());
        model.addAttribute("partName", partDTO.getName());
        model.addAttribute("partDescription", partDTO.getDescription());
        model.addAttribute("partQuantity", partDTO.getQuantity());
        model.addAttribute("partPrice", partDTO.getPrice());

        model.addAttribute("offerId", offerDTO.getId());
        model.addAttribute("offerName", offerDTO.getName());
        model.addAttribute("offerEmail", offerDTO.getEmail());
        model.addAttribute("offerPhone", offerDTO.getPhone());
        model.addAttribute("offerAddress", offerDTO.getAddress());
        model.addAttribute("offerCity", offerDTO.getCity());
        model.addAttribute("offerZip", offerDTO.getZipCode());
    }

}
