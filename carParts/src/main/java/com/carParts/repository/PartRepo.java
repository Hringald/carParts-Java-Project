package com.carParts.repository;

import com.carParts.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PartRepo extends JpaRepository<Part, Long> {
    List<Part> findBySeller(User user);

    Page<Part> findByMakeAndModelAndCategoryAndNameLike(Make make, Model model, Category category, String searchTerm, Pageable pageable);
}
