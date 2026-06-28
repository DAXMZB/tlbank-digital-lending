package com.tlbank.lending.application.user.service;

/**
 * Command object for enabling or disabling a user account.
 */
public record UpdateUserStatusCommand(
        String userId,
        boolean enabled
) {
}
