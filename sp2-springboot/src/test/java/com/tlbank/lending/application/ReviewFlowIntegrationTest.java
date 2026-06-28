package com.tlbank.lending.application;

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
 * Integration tests for the credit review workflow.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ReviewFlowIntegrationTest {

    private static final String MOBILE = "0912444444";
    private static final String TEST_OTP = "654321";
    private static final String PASSWORD = "Password123!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void submitApplication_shouldCreateReviewCase() throws Exception {
        String applicationId = submitApplicationThroughFlow();

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM review_cases WHERE application_no = ?",
                Integer.class,
                applicationId);

        org.assertj.core.api.Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    void approveReviewCase_shouldUpdateApplicationStatus() throws Exception {
        String applicationId = submitApplicationThroughFlow();

        String reviewCaseId = jdbcTemplate.queryForObject(
                "SELECT review_case_no FROM review_cases WHERE application_no = ?",
                String.class,
                applicationId);

        jdbcTemplate.update("UPDATE review_cases SET status = 'UNDER_REVIEW' WHERE review_case_no = ?", reviewCaseId);
        jdbcTemplate.update("UPDATE applications SET status = 'UNDER_REVIEW' WHERE application_no = ?", applicationId);

        MvcResult loginResult = loginAs("reviewer1");

        mockMvc.perform(post("/api/v1/review/cases/" + reviewCaseId + "/actions/approve")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"remark\":\"Approved in integration test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        String status = jdbcTemplate.queryForObject(
                "SELECT status FROM applications WHERE application_no = ?",
                String.class,
                applicationId);

        org.assertj.core.api.Assertions.assertThat(status).isEqualTo("APPROVED");
    }

    private String submitApplicationThroughFlow() throws Exception {
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

        uploadDocument(applicationId, DocumentType.INCOME_PROOF, "income.pdf");
        uploadDocument(applicationId, DocumentType.RESIDENCE_PROOF, "residence.pdf");

        mockMvc.perform(post("/api/v1/applications/" + applicationId + "/actions/submit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("SUBMITTED"));

        return applicationId;
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
                    "fullName": "Review Flow User",
                    "nationalId": "A222222222",
                    "mobile": "%s",
                    "email": "reviewflow@example.com",
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
                .andExpect(status().isOk());
    }

    private MvcResult loginAs(String username) throws Exception {
        return mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", username)
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();
    }
}
