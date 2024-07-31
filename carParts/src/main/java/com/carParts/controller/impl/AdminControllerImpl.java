package com.carParts.controller.impl;

import com.carParts.controller.AccountController;
import com.carParts.controller.AdminController;
import com.carParts.model.dto.*;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.MakeService;
import com.carParts.service.UserService;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.ModelServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class AdminControllerImpl implements AdminController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;
    private final ModelServiceImpl modelService;
    private final UserServiceImpl userService;

    private final MakeService makeService;
    private final AdminServiceImpl adminService;

    public AdminControllerImpl(LoggedUser loggedUser, UserServiceImpl userService, AdminServiceImpl adminService, AdminUser adminUser, ModelServiceImpl modelService, MakeService makeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminService = adminService;
        this.adminUser = adminUser;
        this.modelService = modelService;
        this.makeService = makeService;
    }


    @Override
    public String usersParts(Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }

        List<User> users = this.userService.findAllUsers();

        model.addAttribute("users", users);

        return "Admin/UsersParts";
    }

    @Override
    public String editUserParts(@PathVariable("id") Long id, Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }

        User user = this.userService.findUserById(id).orElse(null);
        Set<Part> userParts = user.getParts();

        model.addAttribute("user", user);
        model.addAttribute("userParts", userParts);

        return "Admin/EditUserParts";
    }

    @Override
    public String editModels(Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        List<com.carParts.model.entity.Model> models = this.modelService.getAllModels();

        model.addAttribute("models", models);

        return "Admin/EditModels";
    }

    @Override
    public String editModel(@PathVariable("id") Long id, Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        List<Make> allMakes = this.makeService.getAllMakes();
        com.carParts.model.entity.Model carModel = this.modelService.findModelById(id);

        model.addAttribute("makeName", carModel.getMake().getName());
        model.addAttribute("modelId", id);
        model.addAttribute("modelName", carModel.getName());
        model.addAttribute("modelUrl", carModel.getImageUrl());
        model.addAttribute("allMakes", allMakes);


        return "Admin/EditModel";
    }

    @Override
    public String editModel(Model model, @Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addModelDTO", addModelDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addModelDTO", result);

            String resultString = "redirect:/admin/editModel/";
            resultString += addModelDTO.getId();
            return resultString;
        }

        this.modelService.editModelById(addModelDTO, addModelDTO.getId());

        return "redirect:/admin/editModels";
    }

    @Override
    public String deleteModel(@PathVariable("id") Long id, Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        this.modelService.deleteModelById(id);

        return "redirect:/admin/editModels";
    }

    @Override
    public String addModel(Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "Admin/AddModel";
    }

    @Override
    public String addModel(@Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addModelDTO", addModelDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addModelDTO", result);

            return "redirect:/admin/addModel";
        }

        this.modelService.createModel(addModelDTO);

        return "redirect:/admin/editModels";
    }

    @Override
    public String editMakes(Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        List<Make> makes = this.makeService.getAllMakes();

        model.addAttribute("makes", makes);

        return "Admin/EditMakes";
    }

    @Override
    public String editMake(@PathVariable("id") Long id, Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        Make make = this.makeService.findMakeById(id);

        model.addAttribute("makeName", make.getName());
        model.addAttribute("makeId", id);
        model.addAttribute("makeName", make.getName());
        model.addAttribute("makeUrl", make.getImageUrl());

        return "Admin/EditMake";
    }

    @GetMapping("/admin/editMake")
    public String editMake(Model model, @Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addMakeDTO", addMakeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addMakeDTO", result);

            String resultString = "redirect:/admin/editMake/";
            resultString += addMakeDTO.getId();
            return resultString;
        }

        this.makeService.editMakeById(addMakeDTO, addMakeDTO.getId());

        return "redirect:/admin/editMakes";
    }

    @GetMapping("/admin/deleteMake/{id}")
    public String deleteMake(@PathVariable("id") Long id, Model model) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        this.makeService.deleteMakeById(id);
        return "redirect:/admin/editMakes";
    }

    @Override
    public String addMake() {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        return "Admin/AddMake";
    }

    @Override
    public String addMake(@Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!this.adminUser.isAdmin()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addMakeDTO", addMakeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addMakeDTO", result);

            return "redirect:/admin/addMake";
        }

        this.makeService.createMake(addMakeDTO);

        return "redirect:/admin/editMakes";
    }

    @ModelAttribute
    public AddMakeDTO addMakeDTO() {
        return new AddMakeDTO();
    }

    @ModelAttribute
    public AddModelDTO addModelDTO() {
        return new AddModelDTO();
    }
}
