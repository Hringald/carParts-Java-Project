package com.carParts.repository;

import com.carParts.model.entity.Admin;

import com.carParts.model.entity.Category;
import com.carParts.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUser(User user);

    Optional<Admin> findByUsername(String username);
}
