package com.carParts.repository;

import com.carParts.model.entity.Category;
import com.carParts.model.entity.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryEnum categoryEnum);
}
