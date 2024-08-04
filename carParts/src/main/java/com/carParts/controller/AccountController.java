package com.carParts.controller;

import com.carParts.model.dto.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RequestMapping("/identity/account")
public interface AccountController {

    @GetMapping("/login")
    String login(Model model);

    @GetMapping("/login/error")
    String loginError(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/register")
    String register();

    @PostMapping("/register")
    String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage")
    String changePhone(@AuthenticationPrincipal UserDetails userDetails, Model model);

    @PostMapping("/manage")
    String changePhone(@AuthenticationPrincipal UserDetails userDetails, @Valid PhoneChangeDTO phoneChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/email")
    String emailChange(@AuthenticationPrincipal UserDetails userDetails, Model model);

    @PostMapping("/manage/email")
    String changeEmail(@AuthenticationPrincipal UserDetails userDetails, @Valid EmailChangeDTO emailChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/password")
    String passwordChange(Model model);

    @PostMapping("/manage/password")
    String passwordChange(@AuthenticationPrincipal UserDetails userDetails, @Valid PasswordChangeDTO passwordChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/becomeAdmin")
    String becomeAdmin(Model model);

    @PostMapping("/manage/becomeAdmin")
    String becomeAdmin(@AuthenticationPrincipal UserDetails userDetails, @Valid MakeAdminDTO makeAdminDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/personalData")
    String personalData(Model model);

    @GetMapping("/manage/deletePersonalData")
    String deleteUser(@AuthenticationPrincipal UserDetails userDetails, Model model);

}
