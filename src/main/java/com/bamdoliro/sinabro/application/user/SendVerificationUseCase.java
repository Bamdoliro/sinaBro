package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import com.bamdoliro.sinabro.infrastructure.mail.MailService;
import com.bamdoliro.sinabro.infrastructure.persistence.user.SignUpVerificationRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.SendVerificationRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SendVerificationUseCase {

    private final MailService mailService;
    private final SignUpVerificationRepository signUpVerificationRepository;

    public void execute(SendVerificationRequest request) {
        SignUpVerification signUpVerification = new SignUpVerification(request.getEmail());

        String subject = "시나브로 회원가입 인증번호";
        String text = String.format(
                "[시나브로] 회원가입 인증번호는 [%s]입니다.",
                signUpVerification.getCode()
        );

        mailService.execute(
                subject,
                request.getEmail(),
                text
        );

        signUpVerificationRepository.save(signUpVerification);
    }
}
