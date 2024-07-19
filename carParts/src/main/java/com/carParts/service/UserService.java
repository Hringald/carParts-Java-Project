package com.carParts.service;


import com.carParts.model.dto.*;
import com.carParts.model.entity.User;

import java.util.Optional;

public interface UserService {

    UserDTO findUserByEmail(String email);

    boolean checkCredentials(String username, String password);

    void login(String username);

    void register(RegisterDTO registerDTO);

    void logout();

    void initAdmin();

    Optional<User> findUserById(Long id);

    void initTest();

    void changePhone(Long UserId, PhoneChangeDTO phoneChangeDTO);

    void changeEmail(Long UserId, EmailChangeDTO emailChangeDTO);

    void changePassword(Long UserId, PasswordChangeDTO passwordChangeDTO);

    void deleteUser(Long UserId);
}
