package org.example.user.service;

import org.example.user.entity.User;
import org.example.user.repo.UserRepository;
import org.example.user.service.UserService.CustomDatabaseException;
import org.example.user.service.UserService.CustomServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testRegisterUser() {
        // Given
        when(userRepository.save(user)).thenReturn(user);

        // When
        User registeredUser = userService.registerUser(user);

        // Then
        assertEquals(user, registeredUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUser_DataAccessException() {
        // Given
        when(userRepository.save(user)).thenThrow(new DataAccessException("Database error") {});

        // When and Then
        assertThrows(CustomDatabaseException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUser_RuntimeException() {
        // Given
        when(userRepository.save(user)).thenThrow(new RuntimeException("Unexpected error"));

        // When and Then
        assertThrows(CustomServiceException.class, () -> userService.registerUser(user));
    }

    @Test
    void testGetUserByUsername() {
        // Given
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        // When
        Optional<User> fetchedUser = userService.getUserByUsername("username");

        // Then
        assertTrue(fetchedUser.isPresent());
        assertEquals(user, fetchedUser.get());
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    void testGetUserByUsername_DataAccessException() {
        // Given
        when(userRepository.findByUsername("username")).thenThrow(new DataAccessException("Database error") {});

        // When and Then
        assertThrows(CustomDatabaseException.class, () -> userService.getUserByUsername("username"));
    }

    @Test
    void testGetUserByUsername_RuntimeException() {
        // Given
        when(userRepository.findByUsername("username")).thenThrow(new RuntimeException("Unexpected error"));

        // When and Then
        assertThrows(CustomServiceException.class, () -> userService.getUserByUsername("username"));
    }
}