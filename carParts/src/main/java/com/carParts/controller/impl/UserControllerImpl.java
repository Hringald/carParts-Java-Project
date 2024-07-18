package com.carParts.controller.impl;

import com.carParts.controller.UserController;
import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserControllerImpl implements UserController {

    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;

    public UserControllerImpl(LoggedUser loggedUser, UserServiceImpl userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @Override
    public String login(Model model) {
        if (this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "login";
    }

    @Override
    public String loginConfirm(LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", result);

            return "redirect:/identity/account/login";
        }

        boolean validCredentials = this.userService.checkCredentials(loginDTO.getEmail(), loginDTO.getPassword());

        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/identity/account/login";
        }

        this.userService.login(loginDTO.getEmail());
        return "redirect:/";
    }

    @Override
    public String register() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "register";
    }

    @Override
    public String registerConfirm(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/identity/account/register";
        }

        this.userService.register(registerDTO);
        return "redirect:/";
    }

    @Override
    public String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.userService.logout();
        return "redirect:/";
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
            redirectAttributes
                    .addFlashAttribute("phoneChangeDTO", phoneChangeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.phoneChangeDTO", result);
            return "redirect:/identity/account/manage";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changePhone(currentUser.getId(), phoneChangeDTO);

        return "redirect:/identity/account/manage";
    }

    @Override
    public String emailChange(Model model){
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
            redirectAttributes
                    .addFlashAttribute("emailChangeDTO", emailChangeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.emailChangeDTO", result);
            return "redirect:/identity/account/manage/email";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changeEmail(currentUser.getId(), emailChangeDTO);

        return "redirect:/identity/account/manage/email";
    }

    @Override
    public String passwordChange(Model model){
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "Password";
    }

    @Override
    public String passwordChange(@Valid PasswordChangeDTO passwordChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmNewPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmNewPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("passwordChangeDTO", passwordChangeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.passwordChangeDTO", result);
            return "redirect:/identity/account/manage/password";
        }

        User currentUser = this.userService.findUserById(this.loggedUser.getId()).orElse(null);
        this.userService.changePassword(currentUser.getId(), passwordChangeDTO);

        return "redirect:/identity/account/manage/password";
    }

    @Override
    public String personalData(Model model){
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "PersonalData";
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute
    public PhoneChangeDTO phoneChangeDTO() {
        return new PhoneChangeDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @ModelAttribute
    public PasswordChangeDTO passwordChangeDTO() {
        return new PasswordChangeDTO();
    }
}
