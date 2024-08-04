package com.carParts.repository;

import com.carParts.model.entity.User;
import com.carParts.model.entity.UserRoleEntity;
import com.carParts.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRoleEnum roleEnum);

}
