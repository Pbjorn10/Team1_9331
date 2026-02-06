package com.team1.ecommerce.server.repository;

import com.team1.ecommerce.shared.model.User;

import java.util.List;

/**
 * Repository abstraction for working with {@link User} entities.
 *
 * <p>The rest of the application should depend on this interface instead of
 * knowing how users are stored (XML, database, etc.). Team member 2 will
 * provide the concrete XML-backed implementation.</p>
 */
public interface UserRepository {

    /**
     * Finds a user by its unique username.
     *
     * @param username the username to search for
     * @return matching {@link User} or {@code null} if none exists
     */
    User findByUsername(String username);

    /**
     * Persists a new user.
     *
     * @param user user to save
     */
    void save(User user);

    /**
     * Returns all users.
     *
     * @return list of all users (may be empty, never {@code null})
     */
    List<User> findAll();
}

