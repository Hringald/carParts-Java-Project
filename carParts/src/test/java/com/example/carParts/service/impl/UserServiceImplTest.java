package com.example.carParts.service.impl;

import com.carParts.model.dto.UserDTO;
import com.carParts.model.entity.User;
import com.carParts.repository.UserRepo;
import com.carParts.repository.UserRoleRepo;
import com.carParts.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private final String TEST_INVALID_EMAIL = "312321321@abv.bg";
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PASSWORD = "1234";

    private UserServiceImpl toTest;
    private UserRepo mockUserRepo;
    private PasswordEncoder mockEncoder;
    private UserRoleRepo mockUserRoleRepo;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockEncoder = Mockito.mock();
        mockUserRoleRepo = Mockito.mock();

        toTest = new UserServiceImpl(mockUserRepo, mockEncoder, mockUserRoleRepo);
    }

    @Test
    void testFindUserByEmail_UserFound() {

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(this.mockEncoder.encode(TEST_PASSWORD));

        when(mockUserRepo.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

        User expectedUser = this.toTest.findUserByEmail(TEST_EMAIL);

        Assertions.assertInstanceOf(UserDTO.class, expectedUser);
        Assertions.assertEquals(expectedUser.getEmail(), TEST_EMAIL);
    }

    @Test
    void testFindUserByEmail_UserNotFound() {
        User expectedUser = this.toTest.findUserByEmail(TEST_INVALID_EMAIL);

        Assertions.assertNull(expectedUser);
    }
}
