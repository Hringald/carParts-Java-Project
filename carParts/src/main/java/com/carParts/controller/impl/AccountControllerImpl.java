package com.carParts.controller.impl;

import com.carParts.controller.AccountController;
import com.carParts.model.dto.EmailChangeDTO;
import com.carParts.model.dto.MakeAdminDTO;
import com.carParts.model.dto.PasswordChangeDTO;
import com.carParts.model.dto.PhoneChangeDTO;
import com.carParts.model.entity.User;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AccountControllerImpl implements AccountController {

    private final LoggedUser loggedUser;
    private final AdminUser adminUser;
    private final UserServiceImpl userService;
    private final AdminServiceImpl adminService;

    public AccountControllerImpl(LoggedUser loggedUser, UserServiceImpl userService, AdminServiceImpl adminService, AdminUser adminUser) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.adminService = adminService;
        this.adminUser = adminUser;
    }


    @Override
    public String changePhone(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        String CurrentUserEmail = currentUser.getEmail();
        String CurrentUserPhone = currentUser.getPhone();

        model.addAttribute("userEmail", CurrentUserEmail);
        model.addAttribute("userPhone", CurrentUserPhone);


        return "manage";
    }

    @Override
    public String changePhone(@Valid PhoneChangeDTO phoneChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("phoneChangeDTO", phoneChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.phoneChangeDTO", result);
            return "redirect:/identity/account/manage";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changePhone(currentUser.getId(), phoneChangeDTO);

        return "redirect:/identity/account/manage";
    }

    @Override
    public String emailChange(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        String CurrentUserEmail = currentUser.getEmail();

        model.addAttribute("userEmail", CurrentUserEmail);

        return "email";
    }

    @Override
    public String changeEmail(@Valid EmailChangeDTO emailChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("emailChangeDTO", emailChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.emailChangeDTO", result);
            return "redirect:/identity/account/manage/email";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changeEmail(currentUser.getId(), emailChangeDTO);

        return "redirect:/identity/account/manage/email";
    }

    @Override
    public String passwordChange(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "Password";
    }

    @Override
    public String passwordChange(@Valid PasswordChangeDTO passwordChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmNewPassword())) {
            result.addError(new FieldError("differentConfirmPassword", "confirmNewPassword", "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("passwordChangeDTO", passwordChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.passwordChangeDTO", result);
            return "redirect:/identity/account/manage/password";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changePassword(currentUser.getId(), passwordChangeDTO);

        return "redirect:/identity/account/manage/password";
    }

    @Override
    public String personalData(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "PersonalData";
    }

    @Override
    public String deleteUser(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.logout();
        this.userService.deleteUser(currentUser.getId());

        return "redirect:/";
    }

    @Override
    public String becomeAdmin(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "BecomeAdmin";
    }

    @Override
    public String becomeAdmin(@Valid MakeAdminDTO makeAdminDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("makeAdminDTO", makeAdminDTO).addFlashAttribute("org.springframework.validation.BindingResult.makeAdminDTO", result);
            return "redirect:/identity/account/manage/becomeAdmin";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        currentUser.setUsername(makeAdminDTO.getUsername());
        this.adminService.makeAdmin(currentUser);

        return "redirect:/";
    }

    @ModelAttribute
    public PhoneChangeDTO phoneChangeDTO() {
        return new PhoneChangeDTO();
    }

    @ModelAttribute
    public PasswordChangeDTO passwordChangeDTO() {
        return new PasswordChangeDTO();
    }

    @ModelAttribute
    public MakeAdminDTO makeAdminDTO() {
        return new MakeAdminDTO();
    }
}
