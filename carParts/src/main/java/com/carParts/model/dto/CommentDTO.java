package com.carParts.model.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDTO {
    private Long id;
    @NotBlank(message = "{comment_not_empty}")
    private String content;

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
