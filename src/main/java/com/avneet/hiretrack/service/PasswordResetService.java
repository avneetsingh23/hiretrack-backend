package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.ForgotPasswordRequest;
import com.avneet.hiretrack.dto.ResetPasswordRequest;
import com.avneet.hiretrack.dto.VerifyOtpRequest;

public interface PasswordResetService {

    String sendOtp(ForgotPasswordRequest request);

    String verifyOtp(VerifyOtpRequest request);

    String resetPassword(ResetPasswordRequest request);

}