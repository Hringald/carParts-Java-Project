package com.carParts.service;


import com.carParts.model.dto.CommentDTO;
import com.carParts.model.entity.Category;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

public interface CommentService {

    void commentsView(Model model);

    void deleteComment(String commentId, Model model);

    void addComment(CommentDTO commentDTO);

    void editCommentView(Long commentId, Model model);

    void editComment(CommentDTO commentDTO);

}
