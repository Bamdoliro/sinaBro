package com.bamdoliro.sinabro.domain.user.service;

import com.bamdoliro.sinabro.domain.user.domain.Verification;
import com.bamdoliro.sinabro.domain.user.exception.VerifyingHasFailedException;
import com.bamdoliro.sinabro.infrastructure.persistence.user.VerificationRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class VerificationFacade {

    private final VerificationRepository verificationRepository;

    @Transactional(readOnly = true)
    public Verification getVerification(String id) {
        return verificationRepository.findById(id)
                .orElseThrow(VerifyingHasFailedException::new);
    }
}
