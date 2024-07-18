package com.carParts.controller;

import com.carParts.model.dto.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RequestMapping("/identity/account")
public interface UserController {

    @GetMapping("/login")
    String login(Model model);

    @PostMapping("/login")
    String loginConfirm(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/register")
    String register();

    @PostMapping("/register")
    String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/logout")
    String logout();

    @GetMapping("/manage")
    String changePhone(Model model);

    @PostMapping("/manage")
    String changePhone(@Valid PhoneChangeDTO phoneChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/email")
    String emailChange(Model model);

    @PostMapping("/manage/email")
    String changeEmail(@Valid EmailChangeDTO emailChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/manage/password")
    String passwordChange(Model model);

    @GetMapping("/manage/personalData")
    String personalData(Model model);

    @PostMapping("/manage/password")
    String passwordChange(@Valid PasswordChangeDTO passwordChangeDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
