package com.OTP.aditya.controller;

import com.OTP.aditya.dto.PasswordRe;
import com.OTP.aditya.dto.PasswordResponse;
import com.OTP.aditya.service.OtpService;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/otp")
    public class OtpController {

        private final OtpService otpService;

        public OtpController(OtpService otpService) {
            this.otpService = otpService;
        }

        @PostMapping("/send")
        public PasswordResponse sendOtp(@RequestBody PasswordRe request) {
            return otpService.sendOtp(request);
        }

        @PostMapping("/verify")
        public String verifyOtp(@RequestParam String username, @RequestParam String otp) {
            boolean isValid = otpService.validate(otp, username);
            return isValid ? "✅ OTP Verified" : "❌ Invalid OTP";
        }
    }


