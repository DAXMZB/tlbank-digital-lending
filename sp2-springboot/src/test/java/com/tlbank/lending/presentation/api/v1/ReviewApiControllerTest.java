package com.tlbank.lending.presentation.api.v1;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Integration tests for the review cases REST API.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ReviewApiControllerTest {

    private static final String PASSWORD = "Password123!";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCases_withRoleReviewer_shouldReturn200() throws Exception {
        MvcResult loginResult = loginAs("reviewer1");

        mockMvc.perform(get("/api/v1/review/cases")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void getCases_withRoleUser_shouldReturn403() throws Exception {
        MvcResult loginResult = loginAs("applicant1");

        mockMvc.perform(get("/api/v1/review/cases")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("FORBIDDEN"));
    }

    @Test
    @Sql(statements = {
            "INSERT INTO applications (application_no, product_id, status, submitted_at, "
                    + "applicant_full_name, applicant_national_id, applicant_mobile, applicant_email, "
                    + "applicant_date_of_birth, address_city, address_district, address_street, address_zip_code, "
                    + "created_at, updated_at) "
                    + "VALUES ('APP-20250607120000-0001', 1, 'UNDER_REVIEW', CURRENT_TIMESTAMP, "
                    + "'Test Applicant', 'A123456789', '0912345678', 'test@example.com', '1990-01-01', "
                    + "'台北市', '信義區', '信義路一段1號', '110', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO review_cases (review_case_no, application_no, application_id, status, created_at, updated_at) "
                    + "SELECT 'RC-20250607-9999', 'APP-20250607120000-0001', a.id, 'UNDER_REVIEW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP "
                    + "FROM applications a WHERE a.application_no = 'APP-20250607120000-0001'"
    })
    void approveCase_withRoleReviewer_shouldReturn200() throws Exception {
        MvcResult loginResult = loginAs("reviewer1");

        mockMvc.perform(post("/api/v1/review/cases/RC-20250607-9999/actions/approve")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"remark\":\"Approved by reviewer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
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
