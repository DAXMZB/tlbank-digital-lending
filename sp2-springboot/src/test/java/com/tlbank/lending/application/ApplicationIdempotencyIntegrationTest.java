package com.tlbank.lending.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ApplicationIdempotencyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createApplication_withSameIdempotencyKey_shouldReturnSameApplicationId() throws Exception {
        String payload = """
                {
                  "cardProductId": "TL-CLASSIC",
                  "applicant": {
                    "fullName": "Idempotency Test User",
                    "nationalId": "A222222222",
                    "mobile": "0912888888",
                    "email": "idempotency@example.com",
                    "dateOfBirth": "1990-05-15",
                    "address": {
                      "city": "Taipei",
                      "district": "Xinyi",
                      "street": "Road 1",
                      "zipCode": "110"
                    }
                  }
                }
                """;

        MvcResult first = mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", "integration-key-001")
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn();

        String firstApplicationId = objectMapper.readTree(first.getResponse().getContentAsString())
                .get("data").get("applicationId").asText();

        MvcResult second = mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", "integration-key-001")
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn();

        String secondApplicationId = objectMapper.readTree(second.getResponse().getContentAsString())
                .get("data").get("applicationId").asText();

        org.assertj.core.api.Assertions.assertThat(secondApplicationId).isEqualTo(firstApplicationId);
    }

    @Test
    void createApplication_withSameKeyDifferentBody_shouldReturn409() throws Exception {
        String payload1 = """
                {
                  "cardProductId": "TL-CLASSIC",
                  "applicant": {
                    "fullName": "User A",
                    "nationalId": "A333333333",
                    "mobile": "0912777777",
                    "email": "a@example.com",
                    "dateOfBirth": "1990-05-15",
                    "address": {
                      "city": "Taipei",
                      "district": "Xinyi",
                      "street": "Road 1",
                      "zipCode": "110"
                    }
                  }
                }
                """;

        String payload2 = """
                {
                  "cardProductId": "TL-CLASSIC",
                  "applicant": {
                    "fullName": "User B",
                    "nationalId": "A333333333",
                    "mobile": "0912777777",
                    "email": "b@example.com",
                    "dateOfBirth": "1990-05-15",
                    "address": {
                      "city": "Taipei",
                      "district": "Xinyi",
                      "street": "Road 1",
                      "zipCode": "110"
                    }
                  }
                }
                """;

        mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", "conflict-key-001")
                        .content(payload1))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", "conflict-key-001")
                        .content(payload2))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("IDEMPOTENCY_KEY_CONFLICT"));
    }
}
