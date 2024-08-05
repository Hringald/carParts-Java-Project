package com.carParts.service.impl;

import com.carParts.model.entity.User;
import com.carParts.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CarPartsUserDetailsServiceTests {
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PASSWORD = "1234";
    private static UserRepo mockUserRepo;
    private static CarPartsUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock();

        toTest = new CarPartsUserDetailsService(mockUserRepo);
    }

    @Test
    void loadUserByUsername_UserFound() {

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        when(mockUserRepo.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

        UserDetails userDetails = this.toTest.loadUserByUsername(TEST_EMAIL);

        assertEquals(userDetails.getUsername(), TEST_EMAIL);
        assertEquals(userDetails.getPassword(), TEST_PASSWORD);
    }

    @Test
    void loadUserByUsername_UserNotFound() {

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> this.toTest.loadUserByUsername(TEST_EMAIL));
        assertEquals("Username with email test@abv.bg not found", exception.getMessage());
    }
}
