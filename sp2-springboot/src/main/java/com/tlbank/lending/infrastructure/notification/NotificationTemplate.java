package com.tlbank.lending.infrastructure.notification;

import java.text.MessageFormat;

import lombok.experimental.UtilityClass;

/**
 * Centralized notification message templates.
 */
@UtilityClass
public class NotificationTemplate {

    public static final String OTP_SMS =
            "【TLBank】Your OTP: {0}. Valid {1} min. Keep it secret.";

    public static final String SUBMIT_SMS =
            "【TLBank】Application {0} received. We will review shortly.";

    public static final String APPROVED_SMS =
            "【TLBank】Congratulations! Application {0} approved.";

    public static final String REJECTED_SMS =
            "【TLBank】Application {0} was not approved. Contact us for details.";

    public static final String OTP_EMAIL_SUBJECT = "TLBank - OTP Verification";

    public static final String OTP_EMAIL_BODY =
            "A one-time password has been sent to your registered mobile number. "
                    + "It is valid for {0} minutes. Please do not share it with anyone.";

    public static final String SUBMIT_EMAIL_SUBJECT = "TLBank - Application Received: {0}";

    public static final String SUBMIT_EMAIL_BODY =
            "Dear customer,\n\nYour credit card application {0} has been received. "
                    + "We will review it shortly and notify you of the result.\n\nTLBank Digital Lending";

    public static final String APPROVED_EMAIL_SUBJECT = "TLBank - Application Approved: {0}";

    public static final String APPROVED_EMAIL_BODY =
            "Dear customer,\n\nCongratulations! Your credit card application {0} has been approved.\n\n"
                    + "TLBank Digital Lending";

    public static final String REJECTED_EMAIL_SUBJECT = "TLBank - Application Result: {0}";

    public static final String REJECTED_EMAIL_BODY =
            "Dear customer,\n\nYour credit card application {0} was not approved at this time. "
                    + "Please contact us for more details.\n\nTLBank Digital Lending";

    public static String formatOtpSms(String otpCode, int expireMinutes) {
        return MessageFormat.format(OTP_SMS, otpCode, expireMinutes);
    }

    public static String formatSubmitSms(String applicationId) {
        return MessageFormat.format(SUBMIT_SMS, applicationId);
    }

    public static String formatApprovedSms(String applicationId) {
        return MessageFormat.format(APPROVED_SMS, applicationId);
    }

    public static String formatRejectedSms(String applicationId) {
        return MessageFormat.format(REJECTED_SMS, applicationId);
    }

    public static String formatOtpEmailBody(int expireMinutes) {
        return MessageFormat.format(OTP_EMAIL_BODY, expireMinutes);
    }

    public static String formatSubmitEmailSubject(String applicationId) {
        return MessageFormat.format(SUBMIT_EMAIL_SUBJECT, applicationId);
    }

    public static String formatApprovedEmailSubject(String applicationId) {
        return MessageFormat.format(APPROVED_EMAIL_SUBJECT, applicationId);
    }

    public static String formatRejectedEmailSubject(String applicationId) {
        return MessageFormat.format(REJECTED_EMAIL_SUBJECT, applicationId);
    }

    public static String formatSubmitEmailBody(String applicationId) {
        return MessageFormat.format(SUBMIT_EMAIL_BODY, applicationId);
    }

    public static String formatApprovedEmailBody(String applicationId) {
        return MessageFormat.format(APPROVED_EMAIL_BODY, applicationId);
    }

    public static String formatRejectedEmailBody(String applicationId) {
        return MessageFormat.format(REJECTED_EMAIL_BODY, applicationId);
    }
}
