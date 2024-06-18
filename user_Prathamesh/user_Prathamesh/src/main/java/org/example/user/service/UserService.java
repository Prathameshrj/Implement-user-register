package org.example.user.service;

import org.example.user.entity.User;
import org.example.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            // Handle database access exceptions
            // You can log the exception and/or throw a custom exception
            throw new CustomDatabaseException("Failed to register user", e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new CustomServiceException("An unexpected error occurred while registering the user", e);
        }
    }

    public Optional<User> getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (DataAccessException e) {
            // Handle database access exceptions
            throw new CustomDatabaseException("Failed to fetch user by username", e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new CustomServiceException("An unexpected error occurred while fetching the user", e);
        }
    }

    public class CustomDatabaseException extends RuntimeException {
        public CustomDatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class CustomServiceException extends RuntimeException {
        public CustomServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}

