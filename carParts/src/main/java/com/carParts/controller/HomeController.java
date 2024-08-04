package com.carParts.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/")
public interface HomeController {

    @GetMapping
    String index(Model model);
}
