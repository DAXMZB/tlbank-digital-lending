package com.tlbank.lending.common.audit;

/**
 * Test-only service used by {@link AuditAspectTest}.
 */
public class AuditableTestService {

    @Auditable(action = AuditAction.DOCUMENT_UPLOAD)
    public String success(String applicationId) {
        return applicationId;
    }

    @Auditable(action = AuditAction.DOCUMENT_UPLOAD)
    public void failure(String applicationId) {
        throw new RuntimeException("audit failed");
    }
}
