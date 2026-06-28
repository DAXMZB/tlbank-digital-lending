package com.tlbank.lending.presentation.api.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlbank.lending.application.dto.request.SendOtpRequest;
import com.tlbank.lending.application.dto.request.VerifyOtpRequest;
import com.tlbank.lending.application.otp.service.OtpAppService;
import com.tlbank.lending.application.otp.service.OtpResponse;
import com.tlbank.lending.application.otp.service.SendOtpCommand;
import com.tlbank.lending.application.otp.service.VerifyOtpCommand;
import com.tlbank.lending.application.otp.service.VerifyOtpResponse;
import com.tlbank.lending.common.config.StandardApiResponses;
import com.tlbank.lending.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API for OTP send and verify actions.
 */
@RestController
@RequestMapping("/api/v1/otp")
@Tag(name = "OTP", description = "One-time password verification APIs")
@StandardApiResponses
@RequiredArgsConstructor
public class OtpApiController {

    private final OtpAppService otpAppService;

    @PostMapping("/actions/send")
    @Operation(summary = "Send OTP", description = "Generates and sends an OTP to the applicant mobile number.")
    public ApiResponse<OtpResponse> sendOtp(@Valid @RequestBody SendOtpRequest request) {
        SendOtpCommand command = new SendOtpCommand(
                request.applicationId(),
                request.mobile(),
                request.purpose()
        );
        return ApiResponse.success(otpAppService.sendOtp(command));
    }

    @PostMapping("/actions/verify")
    @Operation(summary = "Verify OTP", description = "Verifies an OTP code and advances the application workflow.")
    public ApiResponse<VerifyOtpResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        VerifyOtpCommand command = new VerifyOtpCommand(
                request.applicationId(),
                request.mobile(),
                request.otpCode()
        );
        return ApiResponse.success(otpAppService.verifyOtp(command));
    }
}
