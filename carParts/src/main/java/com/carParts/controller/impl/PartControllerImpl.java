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

        this.partService.myPartsView(userDetails, model);

        return "Part/MyParts";
    }

    @Override
    public String chooseMake(Model model) {

        this.makeService.chooseMakeView(model);

        return "Part/ChooseMake";
    }

    @Override
    public String addPart(@PathVariable("makeName") String makeName, Model model) {

        this.partService.addPartView(makeName, model);

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

        this.partService.addPart(addPartDTO, userDetails);

        return "redirect:/";
    }

    @Override
    public String removePartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        this.partService.removePart(id);
        return "redirect:/parts/myParts";
    }

    @Override
    public String editPartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model) {

        this.partService.editPartByIdView(userDetails, id, model);

        return "Part/EditPart";
    }

    @Override
    public String editPartById(@AuthenticationPrincipal UserDetails userDetails, Model model, @Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        Long id = addPartDTO.getId();
        Part currentPart = this.partService.findPartById(id);

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
