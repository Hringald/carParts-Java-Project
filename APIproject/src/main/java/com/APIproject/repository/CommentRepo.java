package com.APIproject.repository;

import com.APIproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
