package com.carParts.service.impl;

import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import com.carParts.model.entity.UserRoleEntity;
import com.carParts.model.enums.UserRoleEnum;
import com.carParts.repository.UserRepo;
import com.carParts.repository.UserRoleRepo;
import com.carParts.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final UserRoleRepo userRoleRepo;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = this.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        return this.userRepo.findByEmail(email).orElse(null);
        // return this.mapUserDTO(user);
    }

    @Override
    public boolean checkCredentials(String email, String password) {
        User user = this.getUserByEmail(email);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }


    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepo.save(this.mapUser(registerDTO));
    }

    private User getUserByEmail(String email) {
        return this.userRepo.findByEmail(email).orElse(null);
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));

        return user;
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }

    public void initAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("1234"));
        admin.setEmail("admin@abv.bg");

        var adminRoleEntity = this.userRoleRepo.findByRole(UserRoleEnum.valueOf("ADMIN")).orElse(null);

        var roles = admin.getRoles();
        roles.add(adminRoleEntity);
        admin.setRoles(roles);


        userRoleRepo.save(adminRoleEntity);
        userRepo.save(admin);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);

    }

    public void initTest() {
        User test = new User();
        test.setUsername("testUser");
        test.setPassword(encoder.encode("1234"));
        test.setEmail("test@abv.bg");

        var userRoleEntity = this.userRoleRepo.findByRole(UserRoleEnum.valueOf("USER")).orElse(null);


        var roles = test.getRoles();
        roles.add(userRoleEntity);
        test.setRoles(roles);


        userRoleRepo.save(userRoleEntity);
        userRepo.save(test);
    }

    @Override
    public void changePhone(Long UserId, PhoneChangeDTO phoneChangeDTO) {
        User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setPhone(phoneChangeDTO.getPhone());
        userRepo.save(currentUser);
    }

    @Override
    public void changeEmail(UserDetails userDetails, Long UserId, EmailChangeDTO emailChangeDTO) {
        User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setEmail(emailChangeDTO.getEmailChange());
        //userDetails.setUsername(emailChangeDTO.getEmailChange());
        userRepo.save(currentUser);
    }

    @Override
    public void changePassword(Long UserId, PasswordChangeDTO passwordChangeDTO) {
        User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));
        userRepo.save(currentUser);
    }

    @Override
    public void deleteUser(Long UserId) {
        User currentUser = this.findUserById(UserId).orElse(null);
        this.userRepo.delete(currentUser);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public void initRoles() {

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.valueOf("USER"));

        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRoleEnum.valueOf("ADMIN"));

        userRoleRepo.save(roleUser);
        userRoleRepo.save(roleAdmin);
    }

    @Override
    public void makeAdmin(UserDetails userDetails, User user) {

        var adminRole = this.userRoleRepo.findByRole(UserRoleEnum.ADMIN);

        var userRoles = user.getRoles();
        userRoles.add(adminRole.orElse(null));

        user.setRoles(userRoles);

        this.userRepo.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }
}
