package com.tlbank.lending.common.audit;

/**
 * Types of auditable actions recorded in the audit log.
 */
public enum AuditAction {
    USER_LOGIN,
    USER_LOGIN_FAILED,
    USER_LOGOUT,
    APPLICATION_SUBMIT,
    APPLICATION_CANCEL,
    APPLICATION_APPROVE,
    APPLICATION_REJECT,
    OTP_SEND,
    OTP_VERIFY_SUCCESS,
    OTP_VERIFY_FAILED,
    REPORT_EXPORT,
    DOCUMENT_UPLOAD
}
