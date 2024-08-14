package com.APIproject.API;

import com.APIproject.model.Comment;
import com.APIproject.service.impl.CommentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;


@RestController
@RequestMapping("api/comments")
public class API {

    private static CommentServiceImpl commentService;

    @Autowired
    public API(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments() {
        return commentService.getAllComments();
    }

    @GetMapping(path = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getComment(@PathVariable("id") String id) throws ServerException {
        Comment comment = commentService.getComment(id);

        if (comment == null) {
            throw new ServerException("Error in getting the Comment resource. Try Again.");
        }

        return comment;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Comment> createComment(@RequestBody Comment newComment, HttpServletRequest request) throws ServerException {

        Comment comment = commentService.addComment(newComment);
        if (comment != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(comment.getId())
                    .toUri();
            return ResponseEntity.created(location).body(comment);
        } else {
            throw new ServerException("Error in creating the Comment resource. Try Again.");
        }
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Comment> updateComment(@PathVariable("id") String id, @RequestBody Comment newComment, HttpServletRequest request) throws ServerException {

        if (id == null) {
            throw new ServerException("Error in updating the Comment resource. Try Again.");
        }

        Comment comment = commentService.editComment(id, newComment);

        if (comment != null & newComment.getContent() != null) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(comment.getId())
                    .toUri();
            return ResponseEntity.created(location).body(comment);
        } else {
            throw new ServerException("Error in updating the Comment resource. Try Again.");
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComment(@PathVariable("id") String id, HttpServletRequest request) throws ServerException {

        if (id == null) {
            throw new ServerException("Error in deleting the Comment resource. Try Again.");
        }

        commentService.deleteComment(id);
    }
}