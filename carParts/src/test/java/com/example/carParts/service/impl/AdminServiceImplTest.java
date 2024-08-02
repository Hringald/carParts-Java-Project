package com.example.carParts.service.impl;

import com.carParts.model.dto.UserDTO;
import com.carParts.model.entity.Admin;
import com.carParts.model.entity.User;
import com.carParts.repository.AdminRepo;
import com.carParts.repository.UserRepo;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

class AdminServiceImplTest {

    private final String TEST_USERNAME = "test";
    private final String TEST_INVALID_USERNAME = "1234";

    private AdminServiceImpl toTest;
    private UserRepo mockUserRepo;
    private AdminRepo mockAdminRepo;
    private AdminUser mockAdminUser;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        mockAdminRepo = Mockito.mock();
        mockAdminUser = Mockito.mock();

        toTest = new AdminServiceImpl(mockUserRepo, mockAdminRepo, mockAdminUser);
    }

    @Test
    void testFindAdminByUsername_AdminFound() {

        Admin admin = new Admin();
        admin.setUsername(TEST_USERNAME);

        when(mockAdminRepo.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(admin));

        Admin expectedAdmin = this.toTest.findAdminByUsername(TEST_USERNAME);

        Assertions.assertInstanceOf(Admin.class, expectedAdmin);
        Assertions.assertEquals(expectedAdmin.getUsername(), TEST_USERNAME);
    }

    @Test
    void testFindAdminByUsername_AdminNotFound() {
        Admin expectedUser = this.toTest.findAdminByUsername(TEST_INVALID_USERNAME);

        Assertions.assertNull(expectedUser);
    }
}
