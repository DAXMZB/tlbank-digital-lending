package com.tlbank.lending.infrastructure.event;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.notification.service.NotificationService;
import com.tlbank.lending.domain.event.ApplicationSubmittedEvent;

@ExtendWith(MockitoExtension.class)
class NotificationEventHandlerTest {

    private static final String APPLICATION_ID = "APP-20240101120000-1234";
    private static final String MOBILE = "0912345678";
    private static final String EMAIL = "test@example.com";

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationEventHandler notificationEventHandler;

    @BeforeEach
    void setUp() {
        notificationEventHandler = new NotificationEventHandler(notificationService);
    }

    @Test
    void onApplicationSubmitted_shouldCallNotificationService() {
        ApplicationSubmittedEvent event = new ApplicationSubmittedEvent(
                APPLICATION_ID, MOBILE, EMAIL, LocalDateTime.now());

        notificationEventHandler.onApplicationSubmitted(event);

        verify(notificationService).sendApplicationSubmittedNotification(APPLICATION_ID, MOBILE, EMAIL);
    }

    @Test
    void onApplicationSubmitted_whenNotificationFails_shouldNotPropagate() {
        ApplicationSubmittedEvent event = new ApplicationSubmittedEvent(
                APPLICATION_ID, MOBILE, EMAIL, LocalDateTime.now());

        doThrow(new RuntimeException("notification failed"))
                .when(notificationService)
                .sendApplicationSubmittedNotification(APPLICATION_ID, MOBILE, EMAIL);

        assertThatCode(() -> notificationEventHandler.onApplicationSubmitted(event))
                .doesNotThrowAnyException();
    }
}
