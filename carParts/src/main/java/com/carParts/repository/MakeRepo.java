package com.carParts.repository;

import com.carParts.model.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MakeRepo extends JpaRepository<Make, Long> {
    Optional<Make> findByName(String makeName);
}
