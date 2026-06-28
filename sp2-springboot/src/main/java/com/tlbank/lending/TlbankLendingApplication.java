package com.tlbank.lending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the TLBank Digital Lending Platform.
 * <p>
 * Bootstraps the Spring Boot application context for the credit card
 * application system. Business modules are wired in subsequent sprints.
 */
@SpringBootApplication
public class TlbankLendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlbankLendingApplication.class, args);
    }
}
