package com.carParts.service;


import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    boolean checkCredentials(String username, String password);


    void register(RegisterDTO registerDTO);

    void initAdmin();

    Optional<User> findUserById(Long id);

    void initTest();

    void changePhone(Long UserId, PhoneChangeDTO phoneChangeDTO);

    void changeEmail(UserDetails userDetails, Long UserId, EmailChangeDTO emailChangeDTO);

    void changePassword(Long UserId, PasswordChangeDTO passwordChangeDTO);

    void deleteUser(Long UserId);

    List<User> findAllUsers();

    void initRoles();

    void makeAdmin(UserDetails userDetails, User user);

}
