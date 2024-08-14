package com.carParts.controller.impl;

import com.carParts.controller.HomeController;
import com.carParts.model.dto.CommentDTO;
import com.carParts.model.dto.RegisterDTO;
import com.carParts.model.entity.Make;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.apache.bcel.classfile.Utility;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {


    private final UserServiceImpl userService;
    private final MakeServiceImpl makeService;


    public HomeControllerImpl(UserServiceImpl userService, MakeServiceImpl makeService) {
        this.userService = userService;
        this.makeService = makeService;
    }

    @Override
    public String index(Model model) {

        List<Make> allMakes = this.makeService.getAllMakes();
        model.addAttribute("allMakes", allMakes);

        return "Index.html";
    }
}
