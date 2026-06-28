package com.tlbank.lending.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.tlbank.lending.application.notification.service.NotificationService;
import com.tlbank.lending.domain.event.ApplicationApprovedEvent;
import com.tlbank.lending.domain.event.ApplicationRejectedEvent;
import com.tlbank.lending.domain.event.ApplicationSubmittedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles domain events by dispatching customer notifications.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final NotificationService notificationService;

    @EventListener
    public void onApplicationSubmitted(ApplicationSubmittedEvent event) {
        try {
            notificationService.sendApplicationSubmittedNotification(
                    event.applicationId(),
                    event.applicantMobile(),
                    event.applicantEmail());
        } catch (Exception ex) {
            log.warn("Failed to send submitted notification for application {}: {}",
                    event.applicationId(), ex.getMessage());
        }
    }

    @EventListener
    public void onApplicationApproved(ApplicationApprovedEvent event) {
        try {
            notificationService.sendApplicationApprovedNotification(
                    event.applicationId(),
                    event.mobile(),
                    event.email());
        } catch (Exception ex) {
            log.warn("Failed to send approved notification for application {}: {}",
                    event.applicationId(), ex.getMessage());
        }
    }

    @EventListener
    public void onApplicationRejected(ApplicationRejectedEvent event) {
        try {
            notificationService.sendApplicationRejectedNotification(
                    event.applicationId(),
                    event.mobile(),
                    event.email());
        } catch (Exception ex) {
            log.warn("Failed to send rejected notification for application {}: {}",
                    event.applicationId(), ex.getMessage());
        }
    }
}
