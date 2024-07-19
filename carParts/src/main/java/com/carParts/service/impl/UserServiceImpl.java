package com.carParts.service.impl;

import com.carParts.model.dto.*;
import com.carParts.model.entity.User;
import com.carParts.repository.UserRepo;
import com.carParts.service.UserService;
import com.carParts.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = this.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
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
    public void login(String email) {
        User user = this.getUserByEmail(email);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setEmail(user.getEmail());
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepo.save(this.mapUser(registerDTO));
        this.login(registerDTO.getEmail());
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setEmail(null);
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

        userRepo.save(test);
    }

    @Override
    public void changePhone(Long UserId, PhoneChangeDTO phoneChangeDTO){
     User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setPhone(phoneChangeDTO.getPhone());
        userRepo.save(currentUser);
    }

    @Override
    public void changeEmail(Long UserId, EmailChangeDTO emailChangeDTO){
        User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setEmail(emailChangeDTO.getEmailChange());
        userRepo.save(currentUser);
    }

    @Override
    public void changePassword(Long UserId, PasswordChangeDTO passwordChangeDTO){
        User currentUser = this.findUserById(UserId).orElse(null);
        currentUser.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));
        userRepo.save(currentUser);
    }

    @Override
    public void deleteUser(Long UserId){
        User currentUser = this.findUserById(UserId).orElse(null);
        this.userRepo.delete(currentUser);
    }
}
