package com.carParts.controller.impl;

import com.carParts.controller.CommentController;
import com.carParts.controller.HomeController;
import com.carParts.model.dto.CommentDTO;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class CommentControllerImpl implements CommentController {

    public CommentControllerImpl(UserServiceImpl userService, MakeServiceImpl makeService) {
    }

    @Override
    public String comments(Model model) throws JsonProcessingException {
        String uri = "http://localhost:8081/api/comments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO[]> responseEntity = restTemplate.getForEntity(uri, CommentDTO[].class);
        List<CommentDTO> comments = Arrays.stream(responseEntity.getBody()).toList();

        model.addAttribute("comments", comments);

        return "Comment/comments";
    }

    @Override
    public String deleteComment(@PathVariable String id, Model model) throws JsonProcessingException {
        String uri = "http://localhost:8081/api/comments/";
        uri += id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);

        return "redirect:/comments";
    }

    @Override
    public String addComment(@Valid CommentDTO commentDTO, BindingResult result, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("commentDTO", commentDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentDTO", result);

            return "redirect:/comments";
        }

        String uri = "http://localhost:8081/api/comments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO> ResponseResult = restTemplate.postForEntity(uri, commentDTO, CommentDTO.class);

        return "redirect:/comments";
    }

    @Override
    public String editComment(@PathVariable("id") Long id, Model model) {
        String uri = "http://localhost:8081/api/comments/";
        uri += id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO> responseEntity = restTemplate.getForEntity(uri, CommentDTO.class);
        CommentDTO commentDTO = responseEntity.getBody();

        model.addAttribute("content", commentDTO.getContent());
        model.addAttribute("commentId", commentDTO.getId());

        return "Comment/editcomment";
    }

    @Override
    public String editComment(@Valid CommentDTO commentDTO, BindingResult result, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("commentDTO", commentDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentDTO", result);

            String returnString = "redirect:/comments/edit/";
            returnString += commentDTO.getId();

            return returnString;
        }

        String uri = "http://localhost:8081/api/comments/";
        uri += commentDTO.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CommentDTO> requestEntity = new HttpEntity<CommentDTO>(commentDTO, headers);
        ResponseEntity<CommentDTO> ResponseResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, CommentDTO.class);

        return "redirect:/comments";
    }

    @ModelAttribute
    public CommentDTO commentDTO() {
        return new CommentDTO();
    }
}
