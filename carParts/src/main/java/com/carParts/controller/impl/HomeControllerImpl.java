package com.carParts.controller.impl;

import com.carParts.controller.HomeController;
import com.carParts.model.entity.Make;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;

    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;


    public HomeControllerImpl(LoggedUser loggedUser,
                              UserServiceImpl userService, AdminUser adminUser, MakeServiceImpl makeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminUser = adminUser;
        this.makeService = makeService;
    }

    @Override
    public String index(Model model) {

        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "index.html";
    }
}
