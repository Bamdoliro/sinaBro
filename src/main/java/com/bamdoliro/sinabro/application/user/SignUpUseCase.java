package com.bamdoliro.sinabro.application.user;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.infrastructure.persistence.user.SignUpVerificationRepository;
import com.bamdoliro.sinabro.infrastructure.persistence.user.UserRepository;
import com.bamdoliro.sinabro.presentation.user.dto.request.SignUpRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final SignUpVerificationRepository signUpVerificationRepository;

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
        SignUpVerification signUpVerification = signUpVerificationRepository.findById(request.getEmail())
                .orElseThrow(RuntimeException::new);

        if (!signUpVerification.getIsVerified()) {
            throw new RuntimeException();
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException();
        }

        signUpVerificationRepository.delete(signUpVerification);
    }
}
