package com.tlbank.lending.domain.user.repository;

import java.util.List;
import java.util.Optional;

import com.tlbank.lending.domain.user.User;
import com.tlbank.lending.domain.user.UserId;

/**
 * Domain repository port for {@link User} aggregate persistence.
 */
public interface UserRepository {

    Optional<User> findById(UserId userId);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
