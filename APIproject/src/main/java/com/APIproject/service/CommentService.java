package com.APIproject.service;

import com.APIproject.model.Comment;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    Comment getComment(@PathVariable("id") String id);

    Comment  addComment(Comment newComment);
    Comment editComment(String id, Comment newComment);
    void deleteComment(String id);
}
