package com.tlbank.lending.common.exception;

/**
 * Exception thrown when an application workflow state transition is not permitted.
 * <p>
 * Mapped to HTTP 409 Conflict with {@link ErrorCode#INVALID_WORKFLOW_TRANSITION}.
 */
public class WorkflowException extends BusinessException {

    public WorkflowException() {
        super(ErrorCode.INVALID_WORKFLOW_TRANSITION);
    }

    public WorkflowException(String message) {
        super(ErrorCode.INVALID_WORKFLOW_TRANSITION, message);
    }
}
