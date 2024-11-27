package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.Verification;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.infrastructure.persistence.user.UserRepository;
import com.bamdoliro.sinabro.infrastructure.persistence.user.VerificationRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.SignUpRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;

    @Transactional
    public void execute(SignUpRequest request) {
        validate(request);

        userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .password(request.getPassword())
                        .authority(Authority.USER)
                        .build()
        );
    }

    private void validate(SignUpRequest request) {
        Verification verification = verificationRepository.findById(request.getEmail())
                .orElseThrow(RuntimeException::new);

        if (!verification.getIsVerified()) {
            throw new RuntimeException();
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException();
        }

        verificationRepository.delete(verification);
    }
}
