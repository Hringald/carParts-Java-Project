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

        this.offerService.myOffersView(userDetails, model);

        return "Offer/MyOffers";
    }

    @Override
    public String createOffer(@PathVariable("id") Long id, Model model) {

        this.offerService.createOfferView(id, model);

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

        this.offerService.addOffer(addOfferDTO, id);

        return "redirect:/";
    }

    @Override
    public String viewOffer(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model) {

        if (this.offerService.isUserSeller(userDetails, id)) {
            return "redirect:/";
        }

        this.offerService.viewOfferView(userDetails, model, id);

        return "Offer/OfferInfo";
    }

    @Override
    public String declineOffer(@PathVariable("id") Long id, Model model) {

        this.offerService.declineOffer(id);

        return "redirect:/";
    }

    @Override
    public String sellOffer(@PathVariable("id") Long id, Model model) {

        this.offerService.sellOffer(id);

        return "redirect:/";
    }

    @ModelAttribute
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }
}
