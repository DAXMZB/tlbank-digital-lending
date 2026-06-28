package com.tlbank.lending.presentation.web;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tlbank.lending.application.audit.service.AuditLogService;
import com.tlbank.lending.application.parameter.service.SystemParameterService;
import com.tlbank.lending.application.user.service.UserAppService;
import com.tlbank.lending.common.audit.AuditAction;

import lombok.RequiredArgsConstructor;

/**
 * Web controller serving Thymeleaf-based admin pages.
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserAppService userAppService;
    private final SystemParameterService systemParameterService;
    private final AuditLogService auditLogService;

    /**
     * Displays the user management page.
     */
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userAppService.findAll());
        return "admin/users";
    }

    /**
     * Displays the system parameters management page.
     */
    @GetMapping("/system-parameters")
    public String systemParameters(Model model) {
        model.addAttribute("parameterGroups", systemParameterService.findAllEnabled().stream()
                .collect(Collectors.groupingBy(
                        param -> param.paramGroup(),
                        LinkedHashMap::new,
                        Collectors.toList())));
        return "admin/system-parameters";
    }

    /**
     * Displays the audit log query page.
     */
    @GetMapping("/audit-logs")
    public String auditLogs(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) AuditAction action,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("logs", auditLogService.search(username, action, dateFrom, dateTo, pageable));
        model.addAttribute("username", username);
        model.addAttribute("action", action);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("actions", AuditAction.values());
        return "admin/audit-logs";
    }

    /**
     * Displays recent notification-related audit log entries.
     */
    @GetMapping("/notifications")
    public String notifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("logs", auditLogService.searchNotificationAttempts(pageable));
        return "admin/notifications";
    }

    /**
     * Displays the daily statistics report generation page.
     */
    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("defaultReportDate", LocalDate.now());
        return "admin/reports";
    }
}
