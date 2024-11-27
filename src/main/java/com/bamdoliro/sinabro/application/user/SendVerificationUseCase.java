package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.Verification;
import com.bamdoliro.sinabro.infrastructure.mail.MailService;
import com.bamdoliro.sinabro.infrastructure.persistence.user.VerificationRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.SendVerificationRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SendVerificationUseCase {

    private final MailService mailService;
    private final VerificationRepository verificationRepository;

    public void execute(SendVerificationRequest request) {
        Verification verification = new Verification(request.getEmail());

        String subject = "시나브로 회원가입 인증번호";
        String text = String.format(
                "[시나브로] 회원가입 인증번호는 [%s]입니다.",
                verification.getCode()
        );

        mailService.execute(
                subject,
                request.getEmail(),
                text
        );

        verificationRepository.save(verification);
    }
}
