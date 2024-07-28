package com.carParts.controller.impl;

import com.carParts.controller.OfferController;
import com.carParts.model.dto.AddOfferDTO;
import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.*;
import com.carParts.repository.UserRepo;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.service.impl.PartServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class OfferControllerImpl implements OfferController {

    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;
    private final OfferServiceImpl offerService;

    private final PartServiceImpl partService;

    public OfferControllerImpl(LoggedUser loggedUser,
                               UserServiceImpl userService, OfferServiceImpl offerService, MakeServiceImpl makeService, PartServiceImpl partService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
        this.makeService = makeService;
        this.partService = partService;
    }

    @Override
    public String myOffers(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        List<Offer> myOffers = this.offerService.findOwnedOffers(currentUser);

        model.addAttribute("myOffers", myOffers);

        return "MyOffers";
    }

    @Override
    public String createOffer(@PathVariable("id") Long id, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        Part partToBuy = this.partService.findPartById(id);

        model.addAttribute("partId", partToBuy.getId());
        model.addAttribute("partUrl", partToBuy.getImageUrl());
        model.addAttribute("partName", partToBuy.getName());
        model.addAttribute("partDescription", partToBuy.getDescription());
        model.addAttribute("partQuantity", partToBuy.getQuantity());
        model.addAttribute("partPrice", partToBuy.getPrice());

        return "CreateOffer";
    }

    @Override
    public String createOffer(Model model, @Valid AddOfferDTO addOfferDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        Long id = addOfferDTO.getId();

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOfferDTO", addOfferDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", result);

            String resultString = "redirect:/offers/viewOffer/";
            resultString += id;
            return resultString;
        }


        Part currentPart = this.partService.findPartById(id);
        User partSeller = currentPart.getSeller();

        this.offerService.addOffer(addOfferDTO, currentPart, partSeller);

        return "redirect:/";
    }

    @Override
    public String viewOffer(@PathVariable("id") Long id, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        Offer currentOffer = this.offerService.findById(id);
        Part currentPart = currentOffer.getPart();
        User partSeller = currentPart.getSeller();

        if (!Objects.equals(this.loggedUser.getId(), partSeller.getId())) {
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

        return "OfferInfo";
    }

    @Override
    public String declineOffer(@PathVariable("id") Long id, Model model) {

        Offer currentOffer = this.offerService.findById(id);
        Part currentPart = currentOffer.getPart();

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
