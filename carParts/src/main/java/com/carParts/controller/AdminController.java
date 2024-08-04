package com.carParts.controller;

import com.carParts.model.dto.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RequestMapping("/admin")
public interface AdminController {

    @GetMapping("/usersParts")
    String usersParts(Model model);

    @GetMapping("/editUserParts/{id}")
    String editUserParts(@PathVariable("id") Long id, Model model);

    @GetMapping("/editModels")
    String editModels(Model model);

    @GetMapping("/editModel/{id}")
    String editModel(@PathVariable("id") Long id, Model model);

    @GetMapping("/editModel")
    String editModel(Model model, @Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/deleteModel/{id}")
    String deleteModel(@PathVariable("id") Long id, Model model);


    @GetMapping("/addModel")
    String addModel(Model model);

    @PostMapping("/addModel")
    String addModel(@Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/editMakes")
    String editMakes(Model model);

    @GetMapping("/editMake/{id}")
    String editMake(@PathVariable("id") Long id, Model model);

    @GetMapping("/editMake")
    String editMake(Model model, @Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/deleteMake/{id}")
    String deleteMake(@PathVariable("id") Long id, Model model);

    @GetMapping("/addMake")
    public String addMake();

    @PostMapping("/addMake")
    public String addMake(@Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
