package com.tlbank.lending.presentation.web;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tlbank.lending.application.application.service.ApplicationAppService;
import com.tlbank.lending.application.application.service.ApplicationDetailResponse;
import com.tlbank.lending.application.application.service.ApplicationSummaryResponse;
import com.tlbank.lending.application.application.service.DocumentInfoResponse;
import com.tlbank.lending.application.dto.request.AddressRequest;
import com.tlbank.lending.application.dto.request.ApplicantRequest;
import com.tlbank.lending.application.dto.request.CreateApplicationRequest;
import com.tlbank.lending.application.otp.service.OtpAppService;
import com.tlbank.lending.application.otp.service.OtpResponse;
import com.tlbank.lending.application.otp.service.SendOtpCommand;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.DocumentType;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.otp.OtpPurpose;

import lombok.RequiredArgsConstructor;

/**
 * Web controller serving Thymeleaf-based application flow pages.
 */
@Controller
@RequiredArgsConstructor
public class ApplicationWebController {

    private final ApplicationAppService applicationAppService;
    private final OtpAppService otpAppService;

    @GetMapping("/")
    public String home(@RequestParam(required = false) Boolean loginRequired, Model model) {
        if (Boolean.TRUE.equals(loginRequired)) {
            model.addAttribute("loginRequiredMessage", "Please log in first");
        }
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", applicationAppService.findAllEnabledProducts());
        return "products/list";
    }

    @GetMapping("/apply")
    public String applyForm(@RequestParam String cardProductId, Model model) {
        ApplicationForm form = new ApplicationForm();
        form.setCardProductId(cardProductId);
        model.addAttribute("applicationForm", form);
        return "application/form";
    }

    @PostMapping("/apply")
    public String createApplication(@ModelAttribute ApplicationForm form) {
        CreateApplicationRequest request = new CreateApplicationRequest(
                new ApplicantRequest(
                        form.getFullName(),
                        form.getNationalId(),
                        form.getMobile(),
                        form.getEmail(),
                        form.getDateOfBirth(),
                        new AddressRequest(
                                form.getCity(),
                                form.getDistrict(),
                                form.getStreet(),
                                form.getZipCode())
                ),
                form.getCardProductId()
        );

        ApplicationSummaryResponse response = applicationAppService.createApplication(request);
        return "redirect:/apply/otp?applicationId=" + response.applicationId()
                + "&mobile=" + form.getMobile();
    }

    @GetMapping("/apply/otp")
    public String otpPage(@RequestParam String applicationId, @RequestParam String mobile, Model model) {
        ApplicationDetailResponse application = applicationAppService.getApplication(applicationId);

        if (application.status() == ApplicationStatus.OTP_VERIFIED
                || application.status() == ApplicationStatus.DOCUMENT_UPLOADED) {
            return "redirect:/apply/upload?applicationId=" + applicationId;
        }
        if (application.status() != ApplicationStatus.INIT) {
            return "redirect:/application/complete?applicationId=" + applicationId;
        }

        OtpResponse otpResponse = otpAppService.sendOtp(
                new SendOtpCommand(applicationId, mobile, OtpPurpose.APPLICATION_VERIFICATION));

        model.addAttribute("applicationId", applicationId);
        model.addAttribute("mobile", mobile);
        model.addAttribute("maskedMobile", MobileNumber.of(mobile).masked());
        model.addAttribute("expiredAt", otpResponse.expiredAt());
        return "application/otp";
    }

    @GetMapping("/apply/upload")
    public String uploadPage(@RequestParam String applicationId, Model model) {
        ApplicationDetailResponse application = applicationAppService.getApplication(applicationId);
        Set<DocumentType> uploadedTypes = application.documents().stream()
                .map(DocumentInfoResponse::documentType)
                .collect(Collectors.toSet());

        model.addAttribute("appDetail", application);
        model.addAttribute("documentTypes", Arrays.asList(DocumentType.values()));
        model.addAttribute("uploadedTypes", uploadedTypes);
        model.addAttribute("allDocumentsUploaded", uploadedTypes.containsAll(Set.of(DocumentType.values())));
        return "application/upload";
    }

    @GetMapping("/apply/submit")
    public String submitConfirmPage(@RequestParam String applicationId, Model model) {
        model.addAttribute("appDetail", applicationAppService.getApplication(applicationId));
        return "application/submit-confirm";
    }

    @PostMapping("/apply/submit")
    public String submitApplication(@RequestParam String applicationId) {
        applicationAppService.submitApplication(applicationId);
        return "redirect:/application/complete?applicationId=" + applicationId + "&submitted=true";
    }

    @GetMapping("/application/complete")
    public String applicationComplete(@RequestParam String applicationId,
                                      @RequestParam(required = false) Boolean verified,
                                      @RequestParam(required = false) Boolean submitted,
                                      Model model) {
        model.addAttribute("applicationId", applicationId);
        model.addAttribute("verified", Boolean.TRUE.equals(verified));
        model.addAttribute("submitted", Boolean.TRUE.equals(submitted));
        return "application/complete";
    }

    /**
     * Form backing object for the web-based application submission flow.
     */
    @lombok.Data
    public static class ApplicationForm {
        private String cardProductId;
        private String fullName;
        private String nationalId;
        private String mobile;
        private String email;
        private LocalDate dateOfBirth;
        private String city;
        private String district;
        private String street;
        private String zipCode;
    }
}
