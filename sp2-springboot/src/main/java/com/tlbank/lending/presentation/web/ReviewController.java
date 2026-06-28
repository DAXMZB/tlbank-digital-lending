package com.tlbank.lending.presentation.web;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlbank.lending.application.review.service.AddRemarkCommand;
import com.tlbank.lending.application.review.service.ApproveCaseCommand;
import com.tlbank.lending.application.review.service.RejectCaseCommand;
import com.tlbank.lending.application.review.service.ReviewAppService;
import com.tlbank.lending.domain.review.ReviewCaseSearchCriteria;
import com.tlbank.lending.domain.review.ReviewStatus;

import lombok.RequiredArgsConstructor;

/**
 * Web controller serving Thymeleaf-based credit review pages.
 */
@Controller
@RequestMapping("/review")
@PreAuthorize("hasAnyRole('REVIEWER','ADMIN')")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewAppService reviewAppService;

    @GetMapping("/cases")
    public String listCases(
            @RequestParam(required = false) ReviewStatus status,
            @RequestParam(required = false) String applicantName,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        ReviewCaseSearchCriteria criteria = new ReviewCaseSearchCriteria(
                status, applicantName, null, dateFrom, dateTo);

        model.addAttribute("cases", reviewAppService.searchCases(criteria, pageable));
        model.addAttribute("status", status);
        model.addAttribute("applicantName", applicantName);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("statuses", ReviewStatus.values());
        return "review/list";
    }

    @GetMapping("/cases/{reviewCaseId}")
    public String caseDetail(@PathVariable String reviewCaseId, Model model) {
        model.addAttribute("caseDetail", reviewAppService.getCaseDetail(reviewCaseId));
        return "review/detail";
    }

    @PostMapping("/cases/{reviewCaseId}/start")
    public String startReview(
            @PathVariable String reviewCaseId,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes) {
        reviewAppService.startCaseReview(reviewCaseId, user.getUsername());
        redirectAttributes.addFlashAttribute("message", "Review started");
        return "redirect:/review/cases/" + reviewCaseId;
    }

    @PostMapping("/cases/{reviewCaseId}/approve")
    public String approveCase(
            @PathVariable String reviewCaseId,
            @RequestParam String remark,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes) {
        reviewAppService.approveCase(new ApproveCaseCommand(reviewCaseId, remark, user.getUsername()));
        redirectAttributes.addFlashAttribute("message", "Case approved");
        return "redirect:/review/cases/" + reviewCaseId;
    }

    @PostMapping("/cases/{reviewCaseId}/reject")
    public String rejectCase(
            @PathVariable String reviewCaseId,
            @RequestParam String remark,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes) {
        reviewAppService.rejectCase(new RejectCaseCommand(reviewCaseId, remark, user.getUsername()));
        redirectAttributes.addFlashAttribute("message", "Case rejected");
        return "redirect:/review/cases/" + reviewCaseId;
    }

    @PostMapping("/cases/{reviewCaseId}/remarks")
    public String addRemark(
            @PathVariable String reviewCaseId,
            @RequestParam String content,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttributes) {
        reviewAppService.addRemark(new AddRemarkCommand(reviewCaseId, content, user.getUsername()));
        redirectAttributes.addFlashAttribute("message", "Remark added");
        return "redirect:/review/cases/" + reviewCaseId;
    }
}
