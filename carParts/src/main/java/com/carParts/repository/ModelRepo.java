package com.carParts.repository;

import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ModelRepo extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String modelName);

    Set<Model> findByMake(Make make);
}
