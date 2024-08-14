package com.carParts.controller;

import com.carParts.model.dto.CommentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping(name = "/")
public interface CommentController {


    @GetMapping("/comments")
    String comments(Model model) throws JsonProcessingException;

    @GetMapping("/comments/delete/{id}")
    String deleteComment(@PathVariable String id, Model model) throws JsonProcessingException;

    @PostMapping("/comments/create")
    String addComment(@Valid CommentDTO commentDTO, BindingResult result, RedirectAttributes redirectAttributes) throws JsonProcessingException;
    @GetMapping("/comments/edit/{id}")
    String editComment(@PathVariable("id") Long id, Model model);
    @PostMapping("/comments/edit")
    String editComment(@Valid CommentDTO commentDTO, BindingResult result, RedirectAttributes redirectAttributes) throws JsonProcessingException;
}
