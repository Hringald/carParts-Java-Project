package com.carParts.controller.impl;

import com.carParts.controller.PartController;
import com.carParts.model.dto.AddPartDTO;
import com.carParts.model.entity.*;
import com.carParts.service.impl.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class PartControllerImpl implements PartController {

    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;
    private final ModelServiceImpl modelService;
    private final PartServiceImpl partService;
    private final CategoryServiceImpl categoryService;

    public PartControllerImpl(UserServiceImpl userService, PartServiceImpl partService, MakeServiceImpl makeService, CategoryServiceImpl categoryService, ModelServiceImpl modelService) {
        this.userService = userService;
        this.partService = partService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
    }

    @Override
    public String myParts(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        User currentUser = this.userService.findUserByEmail(userDetails.getUsername());
        List<Part> myParts = this.partService.findOwnedParts(currentUser);

        model.addAttribute("myParts", myParts);

        return "Part/MyParts";
    }

    @Override
    public String chooseMake(Model model) {

        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "Part/ChooseMake";
    }

    @Override
    public String addPart(@PathVariable("makeName") String makeName, Model model) {
        model.addAttribute("makeName", makeName);

        List<Category> allCategories = this.categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        Make make = this.makeService.findMakeByName(makeName);

        Set<com.carParts.model.entity.Model> makeModels = this.modelService.findModelByMake(make);
        model.addAttribute("makeModels", makeModels);

        return "Part/AddPart";
    }

    @Override
    public String addPart(@AuthenticationPrincipal UserDetails userDetails, @Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        String makeName = addPartDTO.getMakeName();

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPartDTO", addPartDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPartDTO", result);

            String resultString = "redirect:/parts/addPart/";
            resultString += makeName;
            return resultString;
        }

        User currentUser = this.userService.findUserByEmail(userDetails.getUsername());
        this.partService.addPart(addPartDTO, currentUser);

        return "redirect:/";
    }

    @Override
    public String removePartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        this.partService.removePart(id);
        return "redirect:/parts/myParts";
    }

    @Override
    public String editPartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model) {

        Part partToEdit = this.partService.findPartById(id);

        Make partMake = partToEdit.getMake();
        String makeName = partMake.getName();
        model.addAttribute("makeName", makeName);

        String modelName = partToEdit.getModel().getName();
        model.addAttribute("modelName", modelName);

        String categoryName = partToEdit.getCategory().getName();
        model.addAttribute("categoryName", categoryName);

        List<Category> allCategories = this.categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        Set<com.carParts.model.entity.Model> makeModels = this.modelService.findModelByMake(partMake);
        model.addAttribute("makeModels", makeModels);

        model.addAttribute("name", partToEdit.getName());
        model.addAttribute("imageUrl", partToEdit.getImageUrl());
        model.addAttribute("price", partToEdit.getPrice());
        model.addAttribute("quantity", partToEdit.getQuantity());
        model.addAttribute("description", partToEdit.getDescription());
        model.addAttribute("partId", partToEdit.getId());

        return "Part/EditPart";
    }

    @Override
    public String editPartById(@AuthenticationPrincipal UserDetails userDetails, Model model, @Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        Long id = addPartDTO.getId();
        Part currentPart = this.partService.findPartById(id);

        if (addPartDTO.getPrice() < Double.parseDouble(DataConstants.Part.DecimalMinValue)) {
            result.addError(
                    new FieldError(
                            "lowePrice",
                            "price",
                            "Price must be bigger than 0.01"));
        }

        if (addPartDTO.getPrice() > Double.parseDouble(DataConstants.Part.DecimalMaxValue)) {
            result.addError(
                    new FieldError(
                            "highPrice",
                            "price",
                            "Price is too high!"));
        }

        if (addPartDTO.getQuantity() < DataConstants.Part.QuantityMinValue) {
            result.addError(
                    new FieldError(
                            "lowQuantity",
                            "quantity",
                            "Quantity must be bigger than 0"));
        }

        if (addPartDTO.getQuantity() > DataConstants.Part.QuantityMaxValue) {
            result.addError(
                    new FieldError(
                            "highQuantity",
                            "quantity",
                            "Quantity must be less than 100"));
        }


        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPartDTO", addPartDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPartDTO", result);

            String resultString = "redirect:/parts/editPart/";
            resultString += id;
            return resultString;
        }


        this.partService.editPart(currentPart, addPartDTO);

        return "redirect:/parts/myParts";
    }

    @ModelAttribute
    public AddPartDTO addPartDTO() {
        return new AddPartDTO();
    }
}
