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


@RequestMapping("/")
public interface AdminController {

    @GetMapping("/admin/usersParts")
    String usersParts(Model model);

    @GetMapping("/admin/editUserParts/{id}")
    String editUserParts(@PathVariable("id") Long id, Model model);

    @GetMapping("/admin/editModels")
    String editModels(Model model);

    @GetMapping("/admin/editModel/{id}")
    String editModel(@PathVariable("id") Long id, Model model);

    @GetMapping("/admin/editModel")
    String editModel(Model model, @Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/admin/deleteModel/{id}")
    String deleteModel(@PathVariable("id") Long id, Model model);


    @GetMapping("/admin/addModel")
    String addModel(Model model);

    @PostMapping("/admin/addModel")
    String addModel(@Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/admin/editMakes")
    String editMakes(Model model);

    @GetMapping("/admin/editMake/{id}")
    String editMake(@PathVariable("id") Long id, Model model);

    @GetMapping("/admin/editMake")
    String editMake(Model model, @Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/admin/deleteMake/{id}")
    String deleteMake(@PathVariable("id") Long id, Model model);

    @GetMapping("/admin/addMake")
    public String addMake();

    @PostMapping("/admin/addMake")
    public String addMake(@Valid AddMakeDTO addMakeDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
