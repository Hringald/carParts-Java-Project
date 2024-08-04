package com.carParts.controller.impl;

import com.carParts.controller.OfferController;
import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.entity.*;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.service.impl.PartServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class OfferControllerImpl implements OfferController {

    private final UserServiceImpl userService;
    private final OfferServiceImpl offerService;

    private final PartServiceImpl partService;

    public OfferControllerImpl(UserServiceImpl userService, OfferServiceImpl offerService, PartServiceImpl partService) {
        this.userService = userService;
        this.offerService = offerService;
        this.partService = partService;
    }

    @Override
    public String myOffers(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        User currentUser = this.userService.findUserByEmail(userDetails.getUsername());
        List<Offer> myOffers = this.offerService.findOwnedOffers(currentUser);

        model.addAttribute("myOffers", myOffers);

        return "Offer/MyOffers";
    }

    @Override
    public String createOffer(@PathVariable("id") Long id, Model model) {

        Part partToBuy = this.partService.findPartById(id);

        model.addAttribute("partId", partToBuy.getId());
        model.addAttribute("partUrl", partToBuy.getImageUrl());
        model.addAttribute("partName", partToBuy.getName());
        model.addAttribute("partDescription", partToBuy.getDescription());
        model.addAttribute("partQuantity", partToBuy.getQuantity());
        model.addAttribute("partPrice", partToBuy.getPrice());

        return "Offer/CreateOffer";
    }

    @Override
    public String createOffer(Model model, @Valid AddOfferDTO addOfferDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        Long id = addOfferDTO.getId();

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOfferDTO", addOfferDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", result);

            String resultString = "redirect:/offers/createOffer/";
            resultString += id;
            return resultString;
        }


        Part currentPart = this.partService.findPartById(id);
        User partSeller = currentPart.getSeller();

        this.offerService.addOffer(addOfferDTO, currentPart, partSeller);

        return "redirect:/";
    }

    @Override
    public String viewOffer(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model) {

        Offer currentOffer = this.offerService.findById(id);
        Part currentPart = currentOffer.getPart();
        User partSeller = currentPart.getSeller();

        User currentUser = this.userService.findUserByEmail(userDetails.getUsername());

        if (!Objects.equals(currentUser.getId(), partSeller.getId())) {
            return "redirect:/";
        }

        model.addAttribute("partUrl", currentPart.getImageUrl());
        model.addAttribute("partName", currentPart.getName());
        model.addAttribute("partDescription", currentPart.getDescription());
        model.addAttribute("partQuantity", currentPart.getQuantity());
        model.addAttribute("partPrice", currentPart.getPrice());

        model.addAttribute("offerId", currentOffer.getId());
        model.addAttribute("offerName", currentOffer.getName());
        model.addAttribute("offerEmail", currentOffer.getEmail());
        model.addAttribute("offerPhone", currentOffer.getPhone());
        model.addAttribute("offerAddress", currentOffer.getAddress());
        model.addAttribute("offerCity", currentOffer.getCity());
        model.addAttribute("offerZip", currentOffer.getZipCode());

        return "Offer/OfferInfo";
    }

    @Override
    public String declineOffer(@PathVariable("id") Long id, Model model) {

        Offer currentOffer = this.offerService.findById(id);

        this.offerService.declineOffer(currentOffer);

        return "redirect:/";
    }

    @Override
    public String sellOffer(@PathVariable("id") Long id, Model model) {

        Offer currentOffer = this.offerService.findById(id);
        Part currentPart = currentOffer.getPart();

        this.offerService.sellOffer(currentOffer, currentPart);

        return "redirect:/";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }
}
