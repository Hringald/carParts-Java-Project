package com.carParts.service.impl;

import com.carParts.model.CarPartsUserDetails;
import com.carParts.model.entity.User;
import com.carParts.model.enums.UserRoleEnum;
import com.carParts.repository.UserRepo;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class CarPartsUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CarPartsUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepo
                .findByEmail(email)
                .map(CarPartsUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username with email " + email + " not found"));
    }

    private static UserDetails map(User user) {
        return new CarPartsUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(r -> r.getRole()).map(CarPartsUserDetailsService::map).toList()
        );
    }

    private static GrantedAuthority map(UserRoleEnum roleEnum) {
        return new SimpleGrantedAuthority("ROLE_" + roleEnum);
    }
}
