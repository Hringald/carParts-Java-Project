package com.carParts.controller.impl;

import com.carParts.controller.AdminController;
import com.carParts.model.dto.*;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.MakeService;
import com.carParts.service.impl.ModelServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class AdminControllerImpl implements AdminController {

    private final ModelServiceImpl modelService;
    private final UserServiceImpl userService;

    private final MakeService makeService;

    public AdminControllerImpl(UserServiceImpl userService, ModelServiceImpl modelService, MakeService makeService) {
        this.userService = userService;
        this.modelService = modelService;
        this.makeService = makeService;
    }


    @Override
    public String usersParts(Model model) {

        this.userService.usersPartsView(model);

        return "Admin/UsersParts";
    }

    @Override
    public String editUserParts(@PathVariable("id") Long id, Model model) {

        this.userService.editUserPartsView(id, model);

        return "Admin/EditUserParts";
    }

    @Override
    public String editModels(Model model) {

        this.modelService.editModelsView(model);

        return "Admin/EditModels";
    }

    @Override
    public String editModel(@PathVariable("id") Long id, Model model) {

        this.modelService.editModelView(id, model);

        return "Admin/EditModel";
    }

    @Override
    public String editModel(Model model, @Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes) {

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
    public String addModel(Model model) {

        this.makeService.addModelView(model);

        return "Admin/AddModel";
    }

    @Override
    public String addModel(@Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes) {

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

        this.makeService.editMakesView(model);

        return "Admin/EditMakes";
    }

    @Override
    public String editMake(@PathVariable("id") Long id, Model model) {

        this.makeService.editMakeView(id, model);

        return "Admin/EditMake";
    }

    @Override
    public String editMake(Model model, @Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

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

        this.makeService.deleteMakeById(id);
        return "redirect:/admin/editMakes";
    }

    @Override
    public String addMake() {

        return "Admin/AddMake";
    }

    @Override
    public String addMake(@Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

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
