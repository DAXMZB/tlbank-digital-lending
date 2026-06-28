package com.tlbank.lending.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.tlbank.lending.common.exception.BusinessException;
import com.tlbank.lending.common.exception.ErrorCode;
import com.tlbank.lending.common.exception.WorkflowException;

class ApplicationTest {

    @Test
    void verifyOtp_whenStatusIsInit_shouldTransitionToOtpVerified() {
        Application application = sampleApplication(ApplicationStatus.INIT);

        application.verifyOtp("applicant");

        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.OTP_VERIFIED);
    }

    @Test
    void verifyOtp_whenStatusIsNotInit_shouldThrowWorkflowException() {
        Application application = sampleApplication(ApplicationStatus.OTP_VERIFIED);

        assertThatThrownBy(() -> application.verifyOtp("applicant"))
                .isInstanceOf(WorkflowException.class)
                .hasMessageContaining("Cannot transition from OTP_VERIFIED to OTP_VERIFIED");
    }

    @Test
    void submit_whenDocumentUploaded_shouldSetSubmittedAt() {
        Application application = sampleApplication(ApplicationStatus.DOCUMENT_UPLOADED);
        application.getDocumentInfos().addAll(allRequiredDocuments());

        application.submit("applicant");

        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.SUBMITTED);
        assertThat(application.getSubmittedAt()).isNotNull();
    }

    @Test
    void submit_whenDocumentsIncomplete_shouldThrowBusinessException() {
        Application application = sampleApplication(ApplicationStatus.DOCUMENT_UPLOADED);
        application.getDocumentInfos().add(sampleDocument(DocumentType.NATIONAL_ID));

        assertThatThrownBy(() -> application.submit("applicant"))
                .isInstanceOf(BusinessException.class)
                .satisfies(ex -> assertThat(((BusinessException) ex).getErrorCode())
                        .isEqualTo(ErrorCode.INCOMPLETE_DOCUMENTS));
    }

    @Test
    void cancel_fromOtpVerified_shouldTransitionToCancelled() {
        Application application = sampleApplication(ApplicationStatus.OTP_VERIFIED);

        application.cancel("applicant", "no longer needed");

        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.CANCELLED);
        assertThat(application.getWorkflowHistories()).hasSize(1);
    }

    @Test
    void cancel_fromDocumentUploaded_shouldTransitionToCancelled() {
        Application application = sampleApplication(ApplicationStatus.DOCUMENT_UPLOADED);

        application.cancel("applicant", "no longer needed");

        assertThat(application.getStatus()).isEqualTo(ApplicationStatus.CANCELLED);
    }

    @Test
    void cancel_whenApproved_shouldThrowWorkflowException() {
        Application application = sampleApplication(ApplicationStatus.APPROVED);

        assertThatThrownBy(() -> application.cancel("admin", "user request"))
                .isInstanceOf(WorkflowException.class)
                .hasMessageContaining("Cannot transition from APPROVED to CANCELLED");
    }

    @ParameterizedTest
    @EnumSource(value = ApplicationStatus.class, names = {"SUBMITTED", "UNDER_REVIEW", "APPROVED", "REJECTED"})
    void cancel_fromNonCancellableStatus_shouldThrowWorkflowException(ApplicationStatus status) {
        Application application = sampleApplication(status);

        assertThatThrownBy(() -> application.cancel("admin", "request"))
                .isInstanceOf(WorkflowException.class);
    }

    @Test
    void uploadDocuments_whenNotOtpVerifiedOrDocumentUploaded_shouldThrowWorkflowException() {
        Application application = sampleApplication(ApplicationStatus.INIT);

        assertThatThrownBy(() -> application.uploadDocuments(List.of(sampleDocument(DocumentType.NATIONAL_ID)), "applicant"))
                .isInstanceOf(WorkflowException.class)
                .hasMessageContaining("Cannot upload documents from status INIT");
    }

    @Test
    void approve_whenNotUnderReview_shouldThrowWorkflowException() {
        Application application = sampleApplication(ApplicationStatus.SUBMITTED);

        assertThatThrownBy(() -> application.approve("reviewer", "ok"))
                .isInstanceOf(WorkflowException.class);
    }

    @Test
    void eachBusinessMethod_shouldAddWorkflowHistory() {
        Application init = sampleApplication(ApplicationStatus.INIT);
        init.verifyOtp("op1");
        assertThat(init.getWorkflowHistories()).hasSize(1);
        assertHistory(init.getWorkflowHistories().get(0), ApplicationStatus.INIT, ApplicationStatus.OTP_VERIFIED, "op1");

        Application otpVerified = sampleApplication(ApplicationStatus.OTP_VERIFIED);
        otpVerified.uploadDocuments(List.of(sampleDocument(DocumentType.NATIONAL_ID)), "op2");
        assertThat(otpVerified.getWorkflowHistories()).hasSize(1);
        assertHistory(otpVerified.getWorkflowHistories().get(0),
                ApplicationStatus.OTP_VERIFIED, ApplicationStatus.DOCUMENT_UPLOADED, "op2");

        Application docUploaded = sampleApplication(ApplicationStatus.DOCUMENT_UPLOADED);
        docUploaded.getDocumentInfos().addAll(allRequiredDocuments());
        docUploaded.submit("op3");
        assertThat(docUploaded.getWorkflowHistories()).hasSize(1);
        assertThat(docUploaded.getSubmittedAt()).isNotNull();
        assertHistory(docUploaded.getWorkflowHistories().get(0),
                ApplicationStatus.DOCUMENT_UPLOADED, ApplicationStatus.SUBMITTED, "op3");

        Application submitted = sampleApplication(ApplicationStatus.SUBMITTED);
        submitted.startReview("op4");
        assertThat(submitted.getWorkflowHistories()).hasSize(1);
        assertHistory(submitted.getWorkflowHistories().get(0),
                ApplicationStatus.SUBMITTED, ApplicationStatus.UNDER_REVIEW, "op4");

        Application underReview = sampleApplication(ApplicationStatus.UNDER_REVIEW);
        underReview.approve("op5", "approved");
        assertThat(underReview.getWorkflowHistories()).hasSize(1);
        assertHistory(underReview.getWorkflowHistories().get(0),
                ApplicationStatus.UNDER_REVIEW, ApplicationStatus.APPROVED, "op5");

        underReview = sampleApplication(ApplicationStatus.UNDER_REVIEW);
        underReview.reject("op6", "rejected");
        assertThat(underReview.getWorkflowHistories()).hasSize(1);
        assertHistory(underReview.getWorkflowHistories().get(0),
                ApplicationStatus.UNDER_REVIEW, ApplicationStatus.REJECTED, "op6");

        Application cancellable = sampleApplication(ApplicationStatus.INIT);
        cancellable.cancel("op7", "cancelled");
        assertThat(cancellable.getWorkflowHistories()).hasSize(1);
        assertThat(cancellable.getStatus()).isEqualTo(ApplicationStatus.CANCELLED);
        assertHistory(cancellable.getWorkflowHistories().get(0),
                ApplicationStatus.INIT, ApplicationStatus.CANCELLED, "op7");
    }

    private void assertHistory(WorkflowHistory history, ApplicationStatus from, ApplicationStatus to, String operator) {
        assertThat(history.getFromStatus()).isEqualTo(from);
        assertThat(history.getToStatus()).isEqualTo(to);
        assertThat(history.getOperator()).isEqualTo(operator);
        assertThat(history.getOperatedAt()).isNotNull();
    }

    private Application sampleApplication(ApplicationStatus status) {
        return Application.builder()
                .applicationId(ApplicationId.of("APP-20250607120000-1234"))
                .applicant(sampleApplicant())
                .cardProductId(CardProductId.of("TL-CLASSIC"))
                .status(status)
                .workflowHistories(new ArrayList<>())
                .documentInfos(new ArrayList<>())
                .build();
    }

    private Applicant sampleApplicant() {
        return new Applicant(
                "Test User",
                "A123456789",
                MobileNumber.of("0912345678"),
                Email.of("test@example.com"),
                LocalDate.of(1990, 1, 1),
                new Address("台北市", "信義區", "信義路一段1號", "110")
        );
    }

    private DocumentInfo sampleDocument(DocumentType type) {
        return new DocumentInfo(
                type,
                type.name().toLowerCase() + ".pdf",
                "/uploads/" + type.name().toLowerCase() + ".pdf",
                1024L,
                LocalDateTime.now()
        );
    }

    private List<DocumentInfo> allRequiredDocuments() {
        return List.of(
                sampleDocument(DocumentType.NATIONAL_ID),
                sampleDocument(DocumentType.INCOME_PROOF),
                sampleDocument(DocumentType.RESIDENCE_PROOF));
    }
}
