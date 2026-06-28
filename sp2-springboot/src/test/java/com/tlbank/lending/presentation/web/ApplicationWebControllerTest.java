package com.tlbank.lending.presentation.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.tlbank.lending.domain.otp.OtpPurpose;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ApplicationWebControllerTest {

    private static final String TEST_OTP = "123456";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @Test
    void uploadPage_afterOtpVerify_shouldReturn200() throws Exception {
        String applicationId = createApplication("0912777777");
        sendOtp(applicationId, "0912777777");
        setOtpCode("0912777777", TEST_OTP);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/otp/actions/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "applicationId": "%s",
                                  "mobile": "0912777777",
                                  "otpCode": "%s"
                                }
                                """.formatted(applicationId, TEST_OTP)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/apply/upload").param("applicationId", applicationId))
                .andExpect(status().isOk())
                .andExpect(view().name("application/upload"));
    }

    private String createApplication(String mobile) throws Exception {
        MvcResult result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "applicant": {
                                    "fullName": "測試用戶",
                                    "nationalId": "A123456789",
                                    "mobile": "%s",
                                    "email": "test@example.com",
                                    "dateOfBirth": "1990-01-01",
                                    "address": {
                                      "city": "台北市",
                                      "district": "信義區",
                                      "street": "信義路一段1號",
                                      "zipCode": "110"
                                    }
                                  },
                                  "cardProductId": "TL-CLASSIC"
                                }
                                """.formatted(mobile)))
                .andExpect(status().isCreated())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        int start = body.indexOf("\"applicationId\":\"") + 17;
        int end = body.indexOf("\"", start);
        return body.substring(start, end);
    }

    private void sendOtp(String applicationId, String mobile) throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/v1/otp/actions/send")
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

    private void setOtpCode(String mobile, String otpCode) {
        jdbcTemplate.update("UPDATE otp_records SET otp_code = ? WHERE mobile = ?", otpCode, mobile);
    }
}
