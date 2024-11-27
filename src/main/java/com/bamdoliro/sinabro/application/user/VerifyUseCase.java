package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import com.bamdoliro.sinabro.domain.user.exception.VerificationCodeMismatchException;
import com.bamdoliro.sinabro.domain.user.service.VerificationFacade;
import com.bamdoliro.sinabro.infrastructure.persistence.user.SignUpVerificationRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.VerifyRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class VerifyUseCase {

    private final SignUpVerificationRepository signUpVerificationRepository;
    private final VerificationFacade verificationFacade;

    @Transactional
    public void execute(VerifyRequest request) {
        SignUpVerification signUpVerification = verificationFacade.getVerification(request.getEmail());
        System.out.println(request.getCode());
        System.out.println(signUpVerification.getCode());

        if (!signUpVerification.getCode().equals(request.getCode())) {
            throw new VerificationCodeMismatchException();
        }

        signUpVerificationRepository.updateSignUpVerification(
                signUpVerification.getEmail(),
                true
        );
    }
}
