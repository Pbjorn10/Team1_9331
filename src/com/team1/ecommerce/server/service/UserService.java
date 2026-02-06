package com.team1.ecommerce.server.service;

import com.team1.ecommerce.server.repository.UserRepository;
import com.team1.ecommerce.shared.model.User;
import com.team1.ecommerce.shared.util.PasswordUtil;
import com.team1.ecommerce.shared.util.ValidationUtil;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public User login(String username, String password) {

        if (!ValidationUtil.hasText(username) ||
                !ValidationUtil.hasText(password)) {
            return null;
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        String expectedHash = user.getPasswordHash();
        String actualHash = PasswordUtil.hash(password);

        if (expectedHash == null || !expectedHash.equals(actualHash)) {
            return null;
        }

        return user;
    }

    /**
     * Registers a new user if validation passes and the username is free.
     *
     * @param user user to create (password field should contain the RAW password)
     * @throws IllegalArgumentException when validation fails
     */
    public void register(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        if (!ValidationUtil.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (!ValidationUtil.isValidPassword(user.getPasswordHash())) {
            // in this simple design we temporarily store raw password in passwordHash
            throw new IllegalArgumentException("Invalid password");
        }

        // Check that username is not already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Replace raw password with hashed value before saving
        String hashed = PasswordUtil.hash(user.getPasswordHash());
        user.setPasswordHash(hashed);

        userRepository.save(user);
    }
}

