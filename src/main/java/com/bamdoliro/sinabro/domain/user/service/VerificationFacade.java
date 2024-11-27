package com.bamdoliro.sinabro.domain.user.service;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import com.bamdoliro.sinabro.domain.user.exception.VerifyingHasFailedException;
import com.bamdoliro.sinabro.infrastructure.persistence.user.SignUpVerificationRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class VerificationFacade {

    private final SignUpVerificationRepository signUpVerificationRepository;

    @Transactional(readOnly = true)
    public SignUpVerification getVerification(String id) {
        return signUpVerificationRepository.findById(id)
                .orElseThrow(VerifyingHasFailedException::new);
    }
}
