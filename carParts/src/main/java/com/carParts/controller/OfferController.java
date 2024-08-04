package com.carParts.controller;

import com.carParts.model.dto.AddOfferDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping(name = "/")
public interface OfferController {

    @GetMapping("offers/myOffers")
    String myOffers(@AuthenticationPrincipal UserDetails userDetails, Model model);

    @GetMapping("offers/createOffer/{id}")
    String createOffer(@PathVariable("id") Long id, Model model);

    @PostMapping("offers/create")
    String createOffer(Model model, @Valid AddOfferDTO addOfferDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("offers/info/{id}")
    String viewOffer(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model);

    @GetMapping("offers/decline/{id}")
    String declineOffer(@PathVariable("id") Long id, Model model);

    @GetMapping("offers/sell/{id}")
    String sellOffer(@PathVariable("id") Long id, Model model);
}
