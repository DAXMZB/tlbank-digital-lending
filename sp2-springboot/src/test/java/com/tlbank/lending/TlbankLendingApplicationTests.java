package com.tlbank.lending;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test verifying the Spring application context loads under the dev profile.
 */
@SpringBootTest
@ActiveProfiles("dev")
class TlbankLendingApplicationTests {

    @Test
    void contextLoads() {
    }
}
