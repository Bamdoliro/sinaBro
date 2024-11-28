package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.Verification;
import com.bamdoliro.sinabro.domain.user.exception.VerificationCodeMismatchException;
import com.bamdoliro.sinabro.domain.user.service.VerificationFacade;
import com.bamdoliro.sinabro.infrastructure.persistence.user.VerificationRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.VerifyRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class VerifyUseCase {

    private final VerificationRepository verificationRepository;
    private final VerificationFacade verificationFacade;

    @Transactional
    public void execute(VerifyRequest request) {
        Verification verification = verificationFacade.getVerification(request.getEmail());

        if (!verification.getCode().equals(request.getCode())) {
            throw new VerificationCodeMismatchException();
        }

        verificationRepository.updateSignUpVerification(
                verification.getEmail(),
                true
        );
    }
}
