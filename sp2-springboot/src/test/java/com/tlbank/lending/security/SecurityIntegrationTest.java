package com.tlbank.lending.security;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Integration tests verifying Sprint 2 security and login behaviour.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Import(SecurityIntegrationTest.ReviewProbeConfig.class)
class SecurityIntegrationTest {

    private static final String PASSWORD = "Password123!";
    private static final String BCRYPT_HASH = "$2b$10$EWu4jpysblxqMHy69Gc0MOWQwB9YD9a7e/fKsHsxdsLAUvlkak8oC";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login_withValidCredentials_shouldReturn200WithUserInfo() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("reviewer1"))
                .andExpect(jsonPath("$.data.fullName").value("Review Officer"))
                .andExpect(jsonPath("$.data.roles", hasItem("REVIEWER")))
                .andExpect(jsonPath("$.data.sessionExpiredAt").exists());
    }

    @Test
    void login_withInvalidPassword_shouldReturn401Json() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", "WrongPassword!"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"))
                .andExpect(jsonPath("$.message", containsString("Invalid username or password")));
    }

    @Test
    @Sql(statements = "INSERT INTO users (user_id, username, password, email, full_name, mobile, national_id, status, created_at, updated_at) "
            + "VALUES ('USR-DISABLED1', 'disabled_user', '" + BCRYPT_HASH + "', 'disabled@tlbank.local', 'Disabled User', "
            + "'0911888888', 'A188888888', 'DISABLED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    void login_withDisabledUser_shouldReturn401Json() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "disabled_user")
                        .param("password", PASSWORD))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"));
    }

    @Test
    void accessAdminPage_withoutAuth_shouldRedirectToHomeWithMessage() throws Exception {
        mockMvc.perform(get("/admin/notifications"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?loginRequired=true"));

        mockMvc.perform(get("/").param("loginRequired", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please log in first")));
    }

    @Test
    void accessReviewEndpoint_withoutAuth_shouldReturn401Json() throws Exception {
        mockMvc.perform(get("/api/v1/review/probe")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"));
    }

    @Test
    void accessReviewEndpoint_withRoleUser_shouldReturn403Json() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "applicant1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/api/v1/review/probe")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("FORBIDDEN"));
    }

    @Test
    void accessReviewEndpoint_withRoleReviewer_shouldReturn200() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/api/v1/review/probe")
                        .session((org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void logout_whenAuthenticated_shouldReturn200AndInvalidateSession() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();

        var session = (org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession();

        mockMvc.perform(post("/api/v1/auth/logout")
                        .session(session)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logout successful"));

        mockMvc.perform(get("/api/v1/review/probe")
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"));
    }

    @Test
    void sessionExpiry_shouldReturn401() throws Exception {
        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();

        var session = (org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession();
        session.invalidate();

        mockMvc.perform(get("/api/v1/review/probe")
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"));
    }

    @Test
    void concurrentLogin_shouldInvalidatePreviousSession() throws Exception {
        MvcResult firstLogin = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andReturn();

        var firstSession = (org.springframework.mock.web.MockHttpSession) firstLogin.getRequest().getSession();

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "reviewer1")
                        .param("password", PASSWORD))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/review/probe")
                        .session(firstSession)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorCode").value("UNAUTHORIZED"));
    }

    /**
     * Test-only controller used to verify review endpoint authorization without implementing review module.
     */
    @TestConfiguration
    static class ReviewProbeConfig {

        @Bean
        ReviewProbeController reviewProbeController() {
            return new ReviewProbeController();
        }
    }

    @RestController
    @RequestMapping("/api/v1/review")
    static class ReviewProbeController {

        @GetMapping("/probe")
        public String probe() {
            return "ok";
        }
    }
}
