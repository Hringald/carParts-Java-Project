package com.carParts.service.impl;

import com.carParts.model.dto.CommentDTO;
import com.carParts.service.CommentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    public CommentServiceImpl() {
    }

    @Override
    public void commentsView(Model model) {
        String uri = "http://localhost:8081/api/comments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO[]> responseEntity = restTemplate.getForEntity(uri, CommentDTO[].class);
        List<CommentDTO> comments = Arrays.stream(responseEntity.getBody()).toList();

        model.addAttribute("comments", comments);
    }

    @Override
    public void deleteComment(String commentId, Model model) {
        String uri = "http://localhost:8081/api/comments/";
        uri += commentId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        String uri = "http://localhost:8081/api/comments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO> ResponseResult = restTemplate.postForEntity(uri, commentDTO, CommentDTO.class);
    }

    @Override
    public void editCommentView(Long commentId, Model model) {
        String uri = "http://localhost:8081/api/comments/";
        uri += commentId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CommentDTO> responseEntity = restTemplate.getForEntity(uri, CommentDTO.class);
        CommentDTO commentDTO = responseEntity.getBody();

        model.addAttribute("content", commentDTO.getContent());
        model.addAttribute("commentId", commentDTO.getId());
    }

    @Override
    public void editComment(CommentDTO commentDTO) {
        String uri = "http://localhost:8081/api/comments/";
        uri += commentDTO.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CommentDTO> requestEntity = new HttpEntity<>(commentDTO, headers);
        ResponseEntity<CommentDTO> ResponseResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, CommentDTO.class);
    }
}
