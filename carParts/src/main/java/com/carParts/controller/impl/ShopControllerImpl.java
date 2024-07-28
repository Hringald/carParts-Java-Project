package com.carParts.controller.impl;

import com.carParts.controller.ShopController;
import com.carParts.model.dto.SearchFormDTO;
import com.carParts.model.entity.Category;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.service.impl.*;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class ShopControllerImpl implements ShopController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;

    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;
    private final MakeServiceImpl makeService;
    private final ModelServiceImpl modelService;

    private final PartServiceImpl partService;


    public ShopControllerImpl(LoggedUser loggedUser,
                              UserServiceImpl userService, AdminUser adminUser, MakeServiceImpl makeService, ModelServiceImpl modelService, CategoryServiceImpl categoryService, PartServiceImpl partService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminUser = adminUser;
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
        this.partService = partService;
    }

    @Override
    public String shopModels(@PathVariable("makeName") String makeName, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        Make make = this.makeService.findMakeByName(makeName);
        Set<com.carParts.model.entity.Model> modelsByMake = this.modelService.findModelByMake(make);

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelsByMake", modelsByMake);

        return "ShopModels.html";
    }

    @Override
    public String shopCategories(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<Category> allCategories = this.categoryService.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelName", modelName);

        return "ShopCategories.html";
    }

    @Override
    public String shopPage(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName, @RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelName", modelName);
        model.addAttribute("categoryName", categoryName);
        if (searchTerm == null) {
            searchTerm = "%";
        }
        model.addAttribute("searchTerm", searchTerm);

        return findPaginated(makeName, modelName, categoryName, 1, searchTerm, model);
    }

    @Override
    public String searchShopPage(@Valid SearchFormDTO searchFormDTO, BindingResult result, RedirectAttributes redirectAttributes, @PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("makeName", makeName);
        model.addAttribute("modelName", modelName);
        model.addAttribute("categoryName", categoryName);


        String searchTerm = searchFormDTO.getSearchTerm();
        if (searchTerm == null) {
            searchTerm = "%";
        }
        model.addAttribute("searchTerm", searchTerm);

        return findPaginated(makeName, modelName, categoryName, 1, searchTerm, model);
    }
    @Override
    public String findPaginated(@PathVariable(value = "makeName") String makeName, @PathVariable(value = "modelName") String modelName, @PathVariable(value = "categoryName") String categoryName, @PathVariable(value = "pageNo") int pageNo, @RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }



        int pageSize = 6;

        if (searchTerm == null) {
            searchTerm = "%";
        }

        Page<Part> page = this.partService.findPaginated(pageNo, pageSize, makeName, modelName, categoryName, searchTerm);

        List<Part> partList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalParts", page.getTotalElements());
        model.addAttribute("searchTerm", searchTerm);

        model.addAttribute("partList", partList);


        return "ShopPage.html";
    }
    @ModelAttribute
    public SearchFormDTO searchFormDTO() {
        return new SearchFormDTO();
    }
}
