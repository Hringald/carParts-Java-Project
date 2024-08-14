package com.carParts.controller.impl;

import com.carParts.controller.AccountController;
import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import com.carParts.service.impl.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AccountControllerImpl implements AccountController {

    private final UserServiceImpl userService;

    public AccountControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String login(Model model) {

        return "Account/Login";
    }

    @Override
    public String register() {

        return "Account/Register";
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
    public String loginError(LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
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

        return "redirect:/";
    }

    @Override
    public String changePhone(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        this.userService.changePhoneView(userDetails, model);

        return "Account/Manage";
    }

    @Override
    public String changePhone(@AuthenticationPrincipal UserDetails userDetails, @Valid PhoneChangeDTO phoneChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("phoneChangeDTO", phoneChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.phoneChangeDTO", result);
            return "redirect:/identity/account/manage";
        }

        this.userService.changePhone(userDetails, phoneChangeDTO);

        return "redirect:/identity/account/manage";
    }

    @Override
    public String emailChange(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        this.userService.emailChangeView(userDetails, model);

        return "Account/Email";
    }

    @Override
    public String changeEmail(@AuthenticationPrincipal UserDetails userDetails, @Valid EmailChangeDTO emailChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("emailChangeDTO", emailChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.emailChangeDTO", result);
            return "redirect:/identity/account/manage/email";
        }

        this.userService.changeEmail(userDetails, emailChangeDTO);

        return "redirect:/identity/account/logout";
    }

    @Override
    public String passwordChange(Model model) {

        return "Account/Password";
    }

    @Override
    public String passwordChange(@AuthenticationPrincipal UserDetails userDetails, @Valid PasswordChangeDTO passwordChangeDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmNewPassword())) {
            result.addError(new FieldError("differentConfirmPassword", "confirmNewPassword", "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("passwordChangeDTO", passwordChangeDTO).addFlashAttribute("org.springframework.validation.BindingResult.passwordChangeDTO", result);
            return "redirect:/identity/account/manage/password";
        }

        this.userService.changePassword(userDetails, passwordChangeDTO);

        return "redirect:/identity/account/logout";
    }

    @Override
    public String becomeAdmin(Model model) {

        return "Account/BecomeAdmin";
    }

    @Override
    public String becomeAdmin(@AuthenticationPrincipal UserDetails userDetails, @Valid MakeAdminDTO makeAdminDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("makeAdminDTO", makeAdminDTO).addFlashAttribute("org.springframework.validation.BindingResult.makeAdminDTO", result);
            return "redirect:/identity/account/manage/becomeAdmin";
        }

        this.userService.makeAdmin(userDetails, makeAdminDTO);

        return "redirect:/identity/account/logout";
    }

    @Override
    public String personalData(Model model) {

        return "Account/PersonalData";
    }

    @Override
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        this.userService.deleteUser(userDetails);

        return "redirect:/identity/account/logout";
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

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }
}
