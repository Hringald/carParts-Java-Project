package com.carParts.controller.impl;

import com.carParts.controller.AccountController;
import com.carParts.controller.AdminController;
import com.carParts.model.dto.EmailChangeDTO;
import com.carParts.model.dto.MakeAdminDTO;
import com.carParts.model.dto.PasswordChangeDTO;
import com.carParts.model.dto.PhoneChangeDTO;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.UserService;
import com.carParts.service.impl.AdminServiceImpl;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class AdminControllerImpl implements AdminController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;
    private final UserServiceImpl userService;
    private final AdminServiceImpl adminService;

    public AdminControllerImpl(LoggedUser loggedUser, UserServiceImpl userService, AdminServiceImpl adminService, AdminUser adminUser) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminService = adminService;
        this.adminUser = adminUser;
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
}
