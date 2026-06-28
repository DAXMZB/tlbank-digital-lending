package com.tlbank.lending.application;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlbank.lending.domain.application.DocumentType;
import com.tlbank.lending.domain.otp.OtpPurpose;

/**
 * Integration tests for the full credit card application workflow.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ApplicationFlowIntegrationTest {

    private static final String MOBILE = "0912111111";
    private static final String TEST_OTP = "123456";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void fullApplicationFlow_shouldCompleteAllStatusTransitions() throws Exception {
        String applicationId = createApplication(MOBILE);
        sendOtp(applicationId);
        setOtpCodeInDatabase(MOBILE, TEST_OTP);
        verifyOtp(applicationId);

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "national-id.pdf",
                "application/pdf",
                "mock-pdf-content".getBytes()
        );

        mockMvc.perform(multipart("/api/v1/applications/" + applicationId + "/documents")
                        .file(file)
                        .param("documentType", DocumentType.NATIONAL_ID.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.documentType").value("NATIONAL_ID"));

        uploadDocument(applicationId, DocumentType.INCOME_PROOF, "income.pdf");
        uploadDocument(applicationId, DocumentType.RESIDENCE_PROOF, "residence.pdf");

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/submit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("SUBMITTED"));

        mockMvc.perform(get("/api/v1/applications/" + applicationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("SUBMITTED"))
                .andExpect(jsonPath("$.data.workflowHistories", hasSize(3)))
                .andExpect(jsonPath("$.data.workflowHistories[0].toStatus").value("OTP_VERIFIED"))
                .andExpect(jsonPath("$.data.workflowHistories[1].toStatus").value("DOCUMENT_UPLOADED"))
                .andExpect(jsonPath("$.data.workflowHistories[2].toStatus").value("SUBMITTED"));
    }

    @Test
    void cancelApplication_fromInit_shouldSucceed() throws Exception {
        String applicationId = createApplication("0912222222");

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"不再需要\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("CANCELLED"));
    }

    @Test
    void cancelApplication_fromApproved_shouldReturn409() throws Exception {
        String applicationId = createApplication("0912333333");

        jdbcTemplate.update("UPDATE applications SET status = 'APPROVED' WHERE application_no = ?", applicationId);

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"嘗試取消\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("INVALID_WORKFLOW_TRANSITION"));
    }

    @Test
    void submitApplication_fromInit_shouldReturn400IncompleteDocuments() throws Exception {
        String applicationId = createApplication("0912555555");

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/submit"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("INCOMPLETE_DOCUMENTS"));
    }

    @Test
    void verifyOtp_whenApplicationNotInit_shouldReturn409() throws Exception {
        String applicationId = createApplication("0912666666");
        sendOtpForMobile(applicationId, "0912666666");
        setOtpCodeInDatabase("0912666666", TEST_OTP);
        jdbcTemplate.update("UPDATE applications SET status = 'SUBMITTED' WHERE application_no = ?", applicationId);

        mockMvc.perform(post("/api/v1/otp/actions/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "applicationId": "%s",
                                  "mobile": "0912666666",
                                  "otpCode": "%s"
                                }
                                """.formatted(applicationId, TEST_OTP)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("INVALID_WORKFLOW_TRANSITION"));
    }

    private void sendOtpForMobile(String applicationId, String mobile) throws Exception {
        mockMvc.perform(post("/api/v1/otp/actions/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "applicationId": "%s",
                                  "mobile": "%s",
                                  "purpose": "%s"
                                }
                                """.formatted(applicationId, mobile, OtpPurpose.APPLICATION_VERIFICATION.name())))
                .andExpect(status().isOk());
    }

    @Test
    void submitApplication_withIncompleteDocuments_shouldReturn400() throws Exception {
        String applicationId = createApplication(MOBILE);
        sendOtp(applicationId);
        setOtpCodeInDatabase(MOBILE, TEST_OTP);
        verifyOtp(applicationId);

        MockMultipartFile file = new MockMultipartFile(
                "file", "national-id.pdf", "application/pdf", "mock-pdf-content".getBytes());
        mockMvc.perform(multipart("/api/v1/applications/" + applicationId + "/documents")
                        .file(file)
                        .param("documentType", DocumentType.NATIONAL_ID.name()))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/submit"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INCOMPLETE_DOCUMENTS"));
    }

    private void uploadDocument(String applicationId, DocumentType type, String filename) throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", filename, "application/pdf", "mock-pdf-content".getBytes());
        mockMvc.perform(multipart("/api/v1/applications/" + applicationId + "/documents")
                        .file(file)
                        .param("documentType", type.name()))
                .andExpect(status().isOk());
    }

    private String createApplication(String mobile) throws Exception {
        String payload = """
                {
                  "cardProductId": "TL-CLASSIC",
                  "applicant": {
                    "fullName": "Flow Test User",
                    "nationalId": "A111111111",
                    "mobile": "%s",
                    "email": "flowtest@example.com",
                    "dateOfBirth": "1990-05-15",
                    "address": {
                      "city": "台北市",
                      "district": "大安區",
                      "street": "復興南路一段1號",
                      "zipCode": "106"
                    }
                  }
                }
                """.formatted(mobile);

        MvcResult result = mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.status").value("INIT"))
                .andReturn();

        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.get("data").get("applicationId").asText();
    }

    private void sendOtp(String applicationId) throws Exception {
        String payload = """
                {
                  "applicationId": "%s",
                  "mobile": "%s",
                  "purpose": "%s"
                }
                """.formatted(applicationId, MOBILE, OtpPurpose.APPLICATION_VERIFICATION.name());

        mockMvc.perform(post("/api/v1/otp/actions/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk());
    }

    private void setOtpCodeInDatabase(String mobile, String otpCode) {
        jdbcTemplate.update(
                "UPDATE otp_records SET otp_code = ? WHERE mobile = ? AND status = 'PENDING'",
                otpCode, mobile);
    }

    private void verifyOtp(String applicationId) throws Exception {
        String payload = """
                {
                  "applicationId": "%s",
                  "mobile": "%s",
                  "otpCode": "%s"
                }
                """.formatted(applicationId, MOBILE, TEST_OTP);

        mockMvc.perform(post("/api/v1/otp/actions/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.verified").value(true));
    }
}
