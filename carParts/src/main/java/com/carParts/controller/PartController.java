package com.carParts.controller;

import com.carParts.model.dto.AddPartDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping(name = "/")
public interface PartController {

    @GetMapping("parts/myParts")
    String myParts(@AuthenticationPrincipal UserDetails userDetails, Model model);

    @GetMapping("parts/chooseMake")
    String chooseMake(Model model);

    @GetMapping("parts/addPart/{makeName}")
    String addPart(@PathVariable("makeName") String makeName, Model model);

    @PostMapping("parts/addPart")
    String addPart(@AuthenticationPrincipal UserDetails userDetails, @Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("parts/deletePart/{id}")
    String removePartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id);

    @GetMapping("parts/editPart/{id}")
    String editPartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id, Model model);

    @PostMapping("parts/editPart")
    String editPartById(@AuthenticationPrincipal UserDetails userDetails, Model model, @Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
