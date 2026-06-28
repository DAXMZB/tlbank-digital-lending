package com.tlbank.lending.presentation.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web controller serving Thymeleaf authentication pages.
 */
@Controller
public class AuthController {

    /**
     * Displays the login page, redirecting authenticated users to the home page.
     */
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal())) {
            return "redirect:/";
        }
        return "auth/login";
    }
}
