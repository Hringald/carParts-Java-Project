package com.carParts.service;


import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

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

    void changePhone(UserDetails userDetails, PhoneChangeDTO phoneChangeDTO);

    void changePhoneView(UserDetails userDetails, Model model);

    void changeEmail(UserDetails userDetails, EmailChangeDTO emailChangeDTO);

    void changePassword(UserDetails userDetails, PasswordChangeDTO passwordChangeDTO);

    void deleteUser(UserDetails userDetails);

    List<User> findAllUsers();

    void initRoles();

    void makeAdmin(UserDetails userDetails, MakeAdminDTO makeAdminDTO);

    void usersPartsView(Model model);

    void editUserPartsView(Long userId, Model model);

    void emailChangeView(UserDetails userDetails, Model model);
}
