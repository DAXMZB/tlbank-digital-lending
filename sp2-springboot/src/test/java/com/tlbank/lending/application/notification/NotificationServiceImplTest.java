package com.tlbank.lending.application.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tlbank.lending.application.notification.service.NotificationServiceImpl;
import com.tlbank.lending.infrastructure.notification.EmailMessage;
import com.tlbank.lending.infrastructure.notification.EmailSender;
import com.tlbank.lending.infrastructure.notification.NotificationTemplate;
import com.tlbank.lending.infrastructure.notification.SmsMessage;
import com.tlbank.lending.infrastructure.notification.SmsSender;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    private static final String MOBILE = "0912345678";
    private static final String EMAIL = "test@example.com";
    private static final String APPLICATION_ID = "APP-20240101120000-1234";

    @Mock
    private SmsSender smsSender;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationServiceImpl(smsSender, emailSender);
    }

    @Test
    void sendOtpNotification_shouldCallBothSenders() {
        notificationService.sendOtpNotification(MOBILE, "123456", 5);

        verify(smsSender).send(any(SmsMessage.class));
        verify(emailSender).send(any(EmailMessage.class));
    }

    @Test
    void sendOtpNotification_whenSmsFails_shouldNotThrow() {
        doThrow(new RuntimeException("SMS gateway down")).when(smsSender).send(any(SmsMessage.class));

        assertThatCode(() -> notificationService.sendOtpNotification(MOBILE, "123456", 5))
                .doesNotThrowAnyException();

        verify(emailSender).send(any(EmailMessage.class));
    }

    @Test
    void sendApplicationSubmittedNotification_shouldUseCorrectTemplate() {
        notificationService.sendApplicationSubmittedNotification(APPLICATION_ID, MOBILE, EMAIL);

        ArgumentCaptor<SmsMessage> smsCaptor = ArgumentCaptor.forClass(SmsMessage.class);
        verify(smsSender).send(smsCaptor.capture());

        SmsMessage smsMessage = smsCaptor.getValue();
        assertThat(smsMessage.to()).isEqualTo(MOBILE);
        assertThat(smsMessage.message())
                .isEqualTo(NotificationTemplate.formatSubmitSms(APPLICATION_ID));

        ArgumentCaptor<EmailMessage> emailCaptor = ArgumentCaptor.forClass(EmailMessage.class);
        verify(emailSender).send(emailCaptor.capture());

        EmailMessage emailMessage = emailCaptor.getValue();
        assertThat(emailMessage.to()).isEqualTo(EMAIL);
        assertThat(emailMessage.subject())
                .isEqualTo(NotificationTemplate.formatSubmitEmailSubject(APPLICATION_ID));
        assertThat(emailMessage.body())
                .isEqualTo(NotificationTemplate.formatSubmitEmailBody(APPLICATION_ID));
    }
}
