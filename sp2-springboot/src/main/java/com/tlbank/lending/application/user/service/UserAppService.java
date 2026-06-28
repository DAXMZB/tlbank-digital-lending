package com.tlbank.lending.application.user.service;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.user.Role;
import com.tlbank.lending.domain.user.User;
import com.tlbank.lending.domain.user.UserId;
import com.tlbank.lending.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Application service coordinating user management use cases.
 */
@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME, "Username already exists: " + command.username());
        }

        Set<Role> roles = command.roles() == null || command.roles().isEmpty()
                ? EnumSet.of(Role.ROLE_USER)
                : command.roles();

        User user = User.builder()
                .userId(UserId.generate())
                .username(command.username())
                .passwordHash(passwordEncoder.encode(command.rawPassword()))
                .fullName(command.fullName())
                .email(command.email())
                .roles(roles)
                .enabled(true)
                .build();

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateStatus(UpdateUserStatusCommand command) {
        User user = userRepository.findById(UserId.of(command.userId()))
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "User not found: " + command.userId()));

        if (command.enabled()) {
            user.enable();
        } else {
            user.disable();
        }

        return toResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(String userId) {
        User user = userRepository.findById(UserId.of(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "User not found: " + userId));
        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        Set<String> roles = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toSet());

        return new UserResponse(
                user.getUserId().value(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                roles,
                user.isEnabled(),
                user.getLastLoginAt(),
                user.getCreatedAt()
        );
    }
}
