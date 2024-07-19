package com.carParts.controller.impl;

import com.carParts.controller.OfferController;
import com.carParts.model.entity.Offer;
import com.carParts.model.entity.User;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.OfferServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class OfferControllerImpl implements OfferController {

    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;
    private final OfferServiceImpl offerService;

    public OfferControllerImpl(LoggedUser loggedUser,
                               UserServiceImpl userService, OfferServiceImpl offerService, MakeServiceImpl makeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
        this.makeService = makeService;
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
}
