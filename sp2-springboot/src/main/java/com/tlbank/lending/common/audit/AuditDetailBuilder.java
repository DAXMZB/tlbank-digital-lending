package com.tlbank.lending.common.audit;

import com.tlbank.lending.application.otp.service.SendOtpCommand;
import com.tlbank.lending.application.otp.service.VerifyOtpCommand;
import com.tlbank.lending.application.dto.request.GenerateReportRequest;
import com.tlbank.lending.application.review.service.ApproveCaseCommand;
import com.tlbank.lending.application.review.service.RejectCaseCommand;
import com.tlbank.lending.common.util.MaskingUtil;

import lombok.experimental.UtilityClass;

/**
 * Builds sanitized audit log detail strings from method arguments.
 */
@UtilityClass
public class AuditDetailBuilder {

    private static final int MAX_DETAIL_LENGTH = 500;

    /**
     * Extracts meaningful, non-sensitive information from method arguments.
     */
    public static String buildDetail(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        StringBuilder detail = new StringBuilder();
        for (Object arg : args) {
            appendArg(detail, arg);
        }

        return truncate(detail.toString().replaceAll("^,\\s*", ""), MAX_DETAIL_LENGTH);
    }

    private static void appendArg(StringBuilder detail, Object arg) {
        if (arg == null) {
            return;
        }

        if (arg instanceof String value) {
            appendStringArg(detail, value);
            return;
        }

        if (arg instanceof SendOtpCommand command) {
            appendSegment(detail, "applicationId", command.applicationId());
            appendSegment(detail, "mobile", MaskingUtil.maskMobile(command.mobile()));
            appendSegment(detail, "purpose", command.purpose() != null ? command.purpose().name() : null);
            return;
        }

        if (arg instanceof VerifyOtpCommand command) {
            appendSegment(detail, "applicationId", command.applicationId());
            appendSegment(detail, "mobile", MaskingUtil.maskMobile(command.mobile()));
            return;
        }

        if (arg instanceof ApproveCaseCommand command) {
            appendSegment(detail, "reviewCaseId", command.reviewCaseId());
            appendSegment(detail, "operator", command.operator());
            return;
        }

        if (arg instanceof RejectCaseCommand command) {
            appendSegment(detail, "reviewCaseId", command.reviewCaseId());
            appendSegment(detail, "operator", command.operator());
            return;
        }

        if (arg instanceof GenerateReportRequest request) {
            appendSegment(detail, "reportDate", request.reportDate() != null ? request.reportDate().toString() : null);
            appendSegment(detail, "format", request.format() != null ? request.format().name() : null);
            return;
        }

        String value = String.valueOf(arg);
        if (!isSensitiveKey(value)) {
            appendSegment(detail, "arg", sanitize(value));
        }
    }

    private static void appendStringArg(StringBuilder detail, String value) {
        if (value.isBlank() || isSensitiveKey(value)) {
            return;
        }

        if (value.startsWith("APP-")) {
            appendSegment(detail, "applicationId", value);
        } else if (value.startsWith("RC-")) {
            appendSegment(detail, "reviewCaseId", value);
        } else if (value.matches("^\\d{6}$")) {
            appendSegment(detail, "otp", "******");
        } else {
            appendSegment(detail, "value", sanitize(value));
        }
    }

    private static void appendSegment(StringBuilder detail, String key, String value) {
        if (value == null || value.isBlank()) {
            return;
        }
        if (!detail.isEmpty()) {
            detail.append(", ");
        }
        detail.append(key).append('=').append(sanitize(value));
    }

    private static String sanitize(String value) {
        if (value == null) {
            return null;
        }
        String sanitized = value
                .replaceAll("(?i)password=\\S+", "password=****")
                .replaceAll("(?i)otpCode=\\S+", "otpCode=******")
                .replaceAll("(?i)nationalId=\\S+", "nationalId=****");
        if (sanitized.matches("^[A-Z]\\d{9}$")) {
            return MaskingUtil.maskNationalId(sanitized);
        }
        return sanitized;
    }

    private static boolean isSensitiveKey(String value) {
        String lower = value.toLowerCase();
        return lower.contains("password") || lower.contains("otpcode") || lower.contains("nationalid");
    }

    private static String truncate(String value, int maxLength) {
        if (value == null || value.isBlank()) {
            return null;
        }
        if (value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength);
    }
}
