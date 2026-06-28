package com.tlbank.lending.application.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tlbank.lending.application.user.service.CreateUserCommand;
import com.tlbank.lending.application.user.service.UpdateUserStatusCommand;
import com.tlbank.lending.application.user.service.UserAppService;
import com.tlbank.lending.application.user.service.UserResponse;
import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.domain.user.Role;
import com.tlbank.lending.domain.user.User;
import com.tlbank.lending.domain.user.UserId;
import com.tlbank.lending.domain.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserAppServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAppService userAppService;

    @Test
    void createUser_whenValidCommand_shouldReturnUserResponse() {
        CreateUserCommand command = new CreateUserCommand(
                "newuser",
                "Password123!",
                "New User",
                "newuser@example.com",
                EnumSet.of(Role.ROLE_USER)
        );

        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(passwordEncoder.encode("Password123!")).thenReturn("hashed-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return User.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .passwordHash(user.getPasswordHash())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .roles(user.getRoles())
                    .enabled(user.isEnabled())
                    .createdAt(LocalDateTime.now())
                    .build();
        });

        UserResponse response = userAppService.createUser(command);

        assertThat(response.username()).isEqualTo("newuser");
        assertThat(response.fullName()).isEqualTo("New User");
        assertThat(response.email()).isEqualTo("newuser@example.com");
        assertThat(response.roles()).containsExactly("ROLE_USER");
        assertThat(response.enabled()).isTrue();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getPasswordHash()).isEqualTo("hashed-password");
    }

    @Test
    void createUser_whenDuplicateUsername_shouldThrowBusinessException() {
        CreateUserCommand command = new CreateUserCommand(
                "admin",
                "Password123!",
                "Duplicate",
                "dup@example.com",
                EnumSet.of(Role.ROLE_USER)
        );

        when(userRepository.existsByUsername("admin")).thenReturn(true);

        assertThatThrownBy(() -> userAppService.createUser(command))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.DUPLICATE_USERNAME);

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateStatus_disable_shouldSetEnabledFalse() {
        User existing = sampleUser(true);
        when(userRepository.findById(UserId.of("USR-TEST0001"))).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = userAppService.updateStatus(new UpdateUserStatusCommand("USR-TEST0001", false));

        assertThat(response.enabled()).isFalse();
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().isEnabled()).isFalse();
    }

    @Test
    void updateStatus_enable_shouldSetEnabledTrue() {
        User existing = sampleUser(false);
        when(userRepository.findById(UserId.of("USR-TEST0001"))).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = userAppService.updateStatus(new UpdateUserStatusCommand("USR-TEST0001", true));

        assertThat(response.enabled()).isTrue();
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().isEnabled()).isTrue();
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser(true)));

        List<UserResponse> responses = userAppService.findAll();

        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).username()).isEqualTo("tester");
    }

    @Test
    void findById_whenExists_shouldReturnUser() {
        when(userRepository.findById(UserId.of("USR-TEST0001"))).thenReturn(Optional.of(sampleUser(true)));

        UserResponse response = userAppService.findById("USR-TEST0001");

        assertThat(response.username()).isEqualTo("tester");
        assertThat(response.userId()).isEqualTo("USR-TEST0001");
    }

    @Test
    void findById_whenNotFound_shouldThrowBusinessException() {
        when(userRepository.findById(UserId.of("USR-MISSING"))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAppService.findById("USR-MISSING"))
                .isInstanceOf(BusinessException.class)
                .extracting(ex -> ((BusinessException) ex).getErrorCode())
                .isEqualTo(ErrorCode.NOT_FOUND);
    }

    private User sampleUser(boolean enabled) {
        return User.builder()
                .userId(UserId.of("USR-TEST0001"))
                .username("tester")
                .passwordHash("hashed")
                .fullName("Test User")
                .email("tester@example.com")
                .roles(Set.of(Role.ROLE_USER))
                .enabled(enabled)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
