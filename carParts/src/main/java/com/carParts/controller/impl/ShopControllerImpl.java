package com.carParts.controller.impl;

import com.carParts.controller.ShopController;
import com.carParts.model.dto.SearchFormDTO;
import com.carParts.model.entity.Category;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Part;
import com.carParts.service.impl.*;
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

    private final CategoryServiceImpl categoryService;
    private final MakeServiceImpl makeService;
    private final ModelServiceImpl modelService;

    private final PartServiceImpl partService;


    public ShopControllerImpl(MakeServiceImpl makeService, ModelServiceImpl modelService, CategoryServiceImpl categoryService, PartServiceImpl partService) {
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
        this.partService = partService;
    }

    @Override
    public String shopModels(@PathVariable("makeName") String makeName, Model model) {

        this.makeService.shopModelsView(makeName, model);

        return "Shop/ShopModels";
    }

    @Override
    public String shopCategories(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, Model model) {

        this.categoryService.shopCategoriesView(makeName, modelName, model);

        return "Shop/ShopCategories";
    }

    @Override
    public String shopPage(@PathVariable("makeName") String makeName, @PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName, @RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {

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

        int pageSize = 6;

        if (searchTerm == null) {
            searchTerm = "%";
        }

        if (searchTerm != null) {
            searchTerm = "%" + searchTerm + "%";
        }

        Page<Part> page = this.partService.findPaginated(pageNo, pageSize, makeName, modelName, categoryName, searchTerm);

        List<Part> partList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalParts", page.getTotalElements());
        model.addAttribute("searchTerm", searchTerm);

        model.addAttribute("partList", partList);


        return "Shop/ShopPage.html";
    }

    @ModelAttribute
    public SearchFormDTO searchFormDTO() {
        return new SearchFormDTO();
    }
}
