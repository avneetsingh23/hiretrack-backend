package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.dto.ForgotPasswordRequest;
import com.avneet.hiretrack.dto.ResetPasswordRequest;
import com.avneet.hiretrack.dto.VerifyOtpRequest;
import com.avneet.hiretrack.entity.PasswordResetOtp;
import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.exception.ResourceNotFoundException;
import com.avneet.hiretrack.repository.PasswordResetOtpRepository;
import com.avneet.hiretrack.repository.UserRepository;
import com.avneet.hiretrack.service.EmailService;
import com.avneet.hiretrack.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetOtpRepository otpRepository;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;
    @Override
    public String sendOtp(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000));

        PasswordResetOtp passwordResetOtp =
                otpRepository.findByEmail(user.getEmail())
                        .orElse(new PasswordResetOtp());

        passwordResetOtp.setEmail(user.getEmail());
        passwordResetOtp.setOtp(otp);
        passwordResetOtp.setVerified(false);
        passwordResetOtp.setExpiryTime(
                LocalDateTime.now().plusMinutes(5));

        otpRepository.save(passwordResetOtp);

        String html = """
        <html>
        <body style="font-family:Arial;background:#f5f5f5;padding:30px;">

        <div style="
        max-width:600px;
margin:auto;
background:white;
padding:25px;
border-radius:10px;
text-align:center;">

                <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                                           width="100">

<h2 style="color:#2563eb;">
HireTrack
</h2>

<h3>Password Reset OTP</h3>

<p>
Use the OTP below to reset your password.
</p>
   <div style="text-align:center;margin:35px 0;">
                
                   <span style="
                   background:#2563eb;
                   color:white;
                   font-size:36px;
                   font-weight:bold;
                   padding:12px 25px;
                   border-radius:10px;
                   letter-spacing:8px;
                   display:inline-block;">
                
                   %s
                
                   </span>
                
                   </div>
<p>
This OTP is valid for <b>5 minutes</b>.
</p>

<hr>

<p style="color:gray;">
© 2026 HireTrack
</p>

</div>

</body>
</html>
""".formatted(otp);

        emailService.sendEmail(
                user.getEmail(),
                "HireTrack Password Reset OTP",
                html
        );

        return "OTP sent successfully";
    }

    @Override
    public String verifyOtp(VerifyOtpRequest request) {

        PasswordResetOtp passwordResetOtp =
                otpRepository.findByEmail(request.getEmail())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("OTP not found"));

        if (!passwordResetOtp.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (passwordResetOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP Expired");
        }

        passwordResetOtp.setVerified(true);

        otpRepository.save(passwordResetOtp);

        return "OTP verified successfully";
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        PasswordResetOtp passwordResetOtp = otpRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("OTP not found"));

        if (!passwordResetOtp.isVerified()) {
            throw new RuntimeException("Please verify OTP first");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        otpRepository.delete(passwordResetOtp);

        return "Password reset successfully";
    }
}