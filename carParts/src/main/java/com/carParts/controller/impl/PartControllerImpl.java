package com.carParts.controller.impl;

import com.carParts.controller.PartController;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.PartServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PartControllerImpl implements PartController {

    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;
    private final PartServiceImpl partService;

    public PartControllerImpl(LoggedUser loggedUser,
                              UserServiceImpl userService, PartServiceImpl partService, MakeServiceImpl makeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.partService = partService;
        this.makeService = makeService;
    }

    @Override
    public String myParts(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        List<Part> myParts = this.partService.findOwnedParts(currentUser);

        model.addAttribute("myParts", myParts);

        return "MyParts";
    }

    @Override
    public String chooseMake(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "ChooseMake";
    }

    @Override
    public String addPart(@PathVariable("makeName") String makeName){

        return "AddPart";
    }

}
