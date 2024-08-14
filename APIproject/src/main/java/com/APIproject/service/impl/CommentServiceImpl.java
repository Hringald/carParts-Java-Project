package com.APIproject.service.impl;

import com.APIproject.model.Comment;
import com.APIproject.repository.CommentRepo;
import com.APIproject.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    @Override
    public Comment getComment(String id) {
        return commentRepo.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public Comment addComment(Comment newComment) {
        return commentRepo.save(newComment);
    }

    @Override
    public Comment editComment(String id, Comment newComment) {
        Comment comment = commentRepo.findById(Long.valueOf(id)).orElse(null);
        comment.setContent(newComment.getContent());

        commentRepo.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(String id) {
        Comment comment = commentRepo.findById(Long.valueOf(id)).orElse(null);
        commentRepo.delete(comment);
    }
}
