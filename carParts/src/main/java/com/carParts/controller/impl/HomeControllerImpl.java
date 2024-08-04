package com.carParts.controller.impl;

import com.carParts.controller.HomeController;
import com.carParts.model.entity.Make;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {


    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;


    public HomeControllerImpl(UserServiceImpl userService, MakeServiceImpl makeService) {
        this.userService = userService;
        this.makeService = makeService;
    }

    @Override
    public String index(Model model) {

        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "Index.html";
    }
}
