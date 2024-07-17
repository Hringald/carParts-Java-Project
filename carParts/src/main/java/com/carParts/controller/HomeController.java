package com.carParts.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/")
public interface HomeController {

    @GetMapping
    String index();
/*
    @GetMapping("/home")
    String home(Model model);

    @GetMapping("/home/remove-painting/{id}")
    String removeById(@PathVariable("id") Long id);

    @GetMapping("/home/add-favourite/{id}")
    String addFavPainting(@PathVariable("id") Long id);

    @GetMapping("/home/vote/{id}")
    String voteById(@PathVariable("id") Long id);

    @GetMapping("/home/remove-favourite/{id}")
    String removeFavourite(@PathVariable("id") Long id);
    */
}
