package org.example.user.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        // You can add any cleanup code here
    }

    @Test
    void testGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void testSetId() {
        user.setId(1L);
        assertNotNull(user.getId());
    }

    @Test
    void testGetUsername() {
        user.setUsername("username");
        assertEquals("username", user.getUsername());
    }

    @Test
    void testSetUsername() {
        user.setUsername("username");
        assertNotNull(user.getUsername());
    }

    @Test
    void testGetEmail() {
        user.setEmail("email@example.com");
        assertEquals("email@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        user.setEmail("email@example.com");
        assertNotNull(user.getEmail());
    }

    @Test
    void testGetPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("password");
        assertNotNull(user.getPassword());
    }

    // Implement the rest of the test cases similarly
}
