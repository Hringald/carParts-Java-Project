package com.carParts.controller.impl;

import com.carParts.controller.CommentController;
import com.carParts.model.dto.CommentDTO;
import com.carParts.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CommentControllerImpl implements CommentController {

    private CommentServiceImpl commentService;

    public CommentControllerImpl(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Override
    public String comments(Model model) throws JsonProcessingException {

        this.commentService.commentsView(model);

        return "Comment/comments";
    }

    @Override
    public String deleteComment(@PathVariable String id, Model model) throws JsonProcessingException {

        this.commentService.deleteComment(id, model);

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

        this.commentService.addComment(commentDTO);

        return "redirect:/comments";
    }

    @Override
    public String editComment(@PathVariable("id") Long id, Model model) {

        this.commentService.editCommentView(id, model);

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

        this.commentService.editComment(commentDTO);

        return "redirect:/comments";
    }

    @ModelAttribute
    public CommentDTO commentDTO() {
        return new CommentDTO();
    }
}
