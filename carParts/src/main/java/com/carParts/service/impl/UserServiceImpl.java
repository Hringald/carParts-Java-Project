package com.carParts.service.impl;

import com.carParts.model.dto.*;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.model.entity.UserRoleEntity;
import com.carParts.model.enums.UserRoleEnum;
import com.carParts.repository.UserRepo;
import com.carParts.repository.UserRoleRepo;
import com.carParts.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final UserRoleRepo userRoleRepo;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.userRoleRepo = userRoleRepo;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public User findUserByEmail(String email) {
        User user = this.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        return this.userRepo.findByEmail(email).orElse(null);
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
    public void changePhone(UserDetails userDetails, PhoneChangeDTO phoneChangeDTO) {
        User currentUser = findUserByEmail(userDetails.getUsername());
        currentUser.setPhone(phoneChangeDTO.getPhone());
        userRepo.save(currentUser);
    }

    @Override
    public void changePhoneView(UserDetails userDetails, Model model) {
        User currentUser = findUserByEmail(userDetails.getUsername());

        String CurrentUserEmail = currentUser.getEmail();
        String CurrentUserPhone = currentUser.getPhone();

        model.addAttribute("userEmail", CurrentUserEmail);
        model.addAttribute("userPhone", CurrentUserPhone);
    }

    @Override
    public void changeEmail(UserDetails userDetails, EmailChangeDTO emailChangeDTO) {
        User currentUser = findUserByEmail(userDetails.getUsername());
        currentUser.setEmail(emailChangeDTO.getEmailChange());
        userRepo.save(currentUser);
    }

    @Override
    public void changePassword(UserDetails userDetails, PasswordChangeDTO passwordChangeDTO) {
        User currentUser = findUserByEmail(userDetails.getUsername());
        currentUser.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));
        userRepo.save(currentUser);
    }

    @Override
    public void deleteUser(UserDetails userDetails) {
        User currentUser = findUserByEmail(userDetails.getUsername());
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
    public void makeAdmin(UserDetails userDetails, MakeAdminDTO makeAdminDTO) {
        User user = findUserByEmail(userDetails.getUsername());
        user.setUsername(makeAdminDTO.getUsername());

        var adminRole = this.userRoleRepo.findByRole(UserRoleEnum.ADMIN);

        var userRoles = user.getRoles();
        userRoles.add(adminRole.orElse(null));

        user.setRoles(userRoles);

        this.userRepo.save(user);
    }

    @Override
    public void usersPartsView(Model model) {
        List<User> users = findAllUsers();

        List<UserDTO> usersDTOs = users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("users", usersDTOs);
    }

    @Override
    public void editUserPartsView(Long userId, Model model) {
        User user = findUserById(userId).orElse(null);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        Set<Part> userParts = user.getParts();

        List<AddPartDTO> userPartsDTOs = userParts
                .stream()
                .map(userPart -> modelMapper.map(userPart, AddPartDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("user", userDTO);
        model.addAttribute("userParts", userPartsDTOs);
    }

    @Override
    public void emailChangeView(UserDetails userDetails, Model model) {
        User currentUser = findUserByEmail(userDetails.getUsername());
        String CurrentUserEmail = currentUser.getEmail();

        model.addAttribute("userEmail", CurrentUserEmail);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }
}
