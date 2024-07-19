package com.carParts.repository;

import com.carParts.model.entity.Admin;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PartRepo extends JpaRepository<Part, Long> {
    List<Part> findBySeller(User user);
}
