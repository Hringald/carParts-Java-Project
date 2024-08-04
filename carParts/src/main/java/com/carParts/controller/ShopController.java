package com.carParts.controller;

import com.carParts.model.dto.SearchFormDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping(name = "/")
public interface ShopController {

    @GetMapping("shop/models/{makeName}")
    String shopModels(@PathVariable("makeName") String makeName, Model model);

    @GetMapping("shop/categories/{makeName}/{modelName}")
    String shopCategories(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, Model model);

    @GetMapping("shop/shopPage/{makeName}/{modelName}/{categoryName}/page/{pageNo}")
    String findPaginated(@PathVariable(value = "makeName") String makeName, @PathVariable(value = "modelName") String modelName, @PathVariable(value = "categoryName") String categoryName, @PathVariable(value = "pageNo") int pageNo, @RequestParam(value = "searchTerm", required = false) String searchTerm, Model model);


    @GetMapping("shop/shopPage/{makeName}/{modelName}/{categoryName}")
    String shopPage(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName, @RequestParam(value = "searchTerm", required = false) String searchTerm, Model model);

    @PostMapping("shop/shopPage/{makeName}/{modelName}/{categoryName}")
    String searchShopPage(@Valid SearchFormDTO searchFormDTO, BindingResult result, RedirectAttributes redirectAttributes, @PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName, Model model);
}
