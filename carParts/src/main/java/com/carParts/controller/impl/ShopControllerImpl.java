package com.carParts.controller.impl;

import com.carParts.controller.HomeController;
import com.carParts.controller.ShopController;
import com.carParts.model.entity.Make;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.ModelServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class ShopControllerImpl implements ShopController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;

    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;
    private final ModelServiceImpl modelService;



    public ShopControllerImpl(LoggedUser loggedUser,
                              UserServiceImpl userService, AdminUser adminUser, MakeServiceImpl makeService, ModelServiceImpl modelService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminUser = adminUser;
        this.makeService = makeService;
        this.modelService = modelService;
    }

    @Override
    public String shopModels(@PathVariable("makeName") String makeName, Model model) {

        Make make = this.makeService.findMakeByName(makeName);
        Set<com.carParts.model.entity.Model> modelsByMake = this.modelService.findModelByMake(make);

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelsByMake", modelsByMake);

        return "ShopModels.html";
    }
}
