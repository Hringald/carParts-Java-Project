package com.carParts.controller.impl;

import com.carParts.controller.HomeController;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;

import org.springframework.stereotype.Controller;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;

    private final UserServiceImpl userService;

    public HomeControllerImpl(LoggedUser loggedUser,
                              UserServiceImpl userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index.html";
    }
}
