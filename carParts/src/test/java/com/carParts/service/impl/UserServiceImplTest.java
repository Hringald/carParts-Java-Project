package com.carParts.service.impl;

import com.carParts.model.CarPartsUserDetails;
import com.carParts.model.dto.EmailChangeDTO;
import com.carParts.model.dto.PasswordChangeDTO;
import com.carParts.model.dto.PhoneChangeDTO;
import com.carParts.model.dto.UserDTO;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.repository.PartRepo;
import com.carParts.repository.UserRepo;
import com.carParts.repository.UserRoleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private final String TEST_INVALID_EMAIL = "312321321@abv.bg";
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_CHANGED_EMAIL = "test123@abv.bg";
    private final String TEST_INITIAL_PHONE = "1245784578";
    private final String TEST_CHANGED_PHONE = "7454787457";
    private final String TEST_PASSWORD = "1234";

    private final String TEST_CHANGED_PASSWORD = "7894";

    private UserServiceImpl toTest;
    private UserRepo mockUserRepo;
    private PasswordEncoder mockEncoder;
    private UserRoleRepo mockUserRoleRepo;

    private PartRepo mockPartRepo;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockEncoder = Mockito.mock();
        mockUserRoleRepo = Mockito.mock();
        mockPartRepo = Mockito.mock();

        toTest = new UserServiceImpl(mockUserRepo, mockEncoder, mockUserRoleRepo, mockPartRepo);
    }

    @Test
    void testFindUserByEmail_UserFound() {

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(this.mockEncoder.encode(TEST_PASSWORD));

        when(mockUserRepo.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

        User expectedUser = this.toTest.findUserByEmail(TEST_EMAIL);

        Assertions.assertInstanceOf(User.class, expectedUser);
        Assertions.assertEquals(expectedUser.getEmail(), TEST_EMAIL);
    }

    @Test
    void testFindUserByEmail_UserNotFound() {
        User expectedUser = this.toTest.findUserByEmail(TEST_INVALID_EMAIL);

        Assertions.assertNull(expectedUser);
    }

    @Test
    void testFindId_UserFound() {

        User user = new User();
        user.setEmail(TEST_EMAIL);

        when(mockUserRepo.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> expectedUser = this.toTest.findUserById(user.getId());

        Assertions.assertEquals(expectedUser.get().getEmail(), user.getEmail());
    }

    @Test
    void testFindById_UserNotFound() {

        Optional<User> expectedUser = this.toTest.findUserById(null);

        Assertions.assertTrue(expectedUser.isEmpty());
    }

    @Test
    void testChangePhone() {
        User user = new User();
        user.setPhone(TEST_INITIAL_PHONE);

        PhoneChangeDTO phoneChangeDTO = new PhoneChangeDTO();
        phoneChangeDTO.setPhone(TEST_CHANGED_PHONE);

        when(mockUserRepo.findByEmail(null)).thenReturn(Optional.of(user));

        toTest.changePhone(Mockito.mock(), phoneChangeDTO);

        Assertions.assertEquals(user.getPhone(), TEST_CHANGED_PHONE);
    }

    @Test
    void testChangeEmail() {
        User user = new User();
        user.setEmail(TEST_EMAIL);

        EmailChangeDTO emailChangeDTO = new EmailChangeDTO();
        emailChangeDTO.setEmailChange(TEST_CHANGED_EMAIL);

        when(mockUserRepo.findByEmail(null)).thenReturn(Optional.of(user));

        toTest.changeEmail(Mockito.mock(), emailChangeDTO);

        Assertions.assertEquals(user.getEmail(), TEST_CHANGED_EMAIL);
    }

    @Test
    void testChangePassword() {
        User user = new User();
        user.setPassword(TEST_PASSWORD);

        PasswordChangeDTO passwordChangeDTO = new PasswordChangeDTO();
        passwordChangeDTO.setNewPassword(TEST_CHANGED_PASSWORD);

        when(toTest.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(mockUserRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(this.mockEncoder.encode(TEST_CHANGED_PASSWORD)).thenReturn(TEST_CHANGED_PASSWORD);

        toTest.changePassword(Mockito.mock(), passwordChangeDTO);

        Assertions.assertEquals(user.getPassword(), TEST_CHANGED_PASSWORD);
    }

    @Test
    void testFindAllUsers_UserFound() {

        List<User> allUsers = toTest.findAllUsers();
        Assertions.assertEquals(allUsers, new ArrayList<>());
    }

    @Test
    void testFindAllUsers_UserNotFound() {

        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);

        when(mockUserRepo.findAll()).thenReturn(users);

        List<User> allUsers = toTest.findAllUsers();
        Assertions.assertEquals(allUsers.stream().count(), 1);
    }
}
