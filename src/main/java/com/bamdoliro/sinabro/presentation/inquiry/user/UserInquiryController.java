package com.bamdoliro.sinabro.presentation.inquiry.user;

import com.bamdoliro.sinabro.application.inquiry.user.CreateInquiryUseCase;
import com.bamdoliro.sinabro.application.inquiry.user.GetAllUserInquiriesUseCase;
import com.bamdoliro.sinabro.application.inquiry.user.GetUserInquiryUseCase;
import com.bamdoliro.sinabro.application.inquiry.user.UpdateInquiryUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.request.InquiryRequest;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.ListUserInquiryResponse;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.UserInquiryResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user/inquiries")
@RestController
public class UserInquiryController {

    private final CreateInquiryUseCase createInquiryUseCase;
    private final GetAllUserInquiriesUseCase getAllUserInquiriesUseCase;
    private final GetUserInquiryUseCase getUserInquiryUseCase;
    private final UpdateInquiryUseCase updateInquiryUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IdResponse createInquiry(
            @AuthenticationPrincipal(authority = Authority.USER) User user,
            @RequestBody @Valid InquiryRequest request
    ) {
        return createInquiryUseCase.execute(user, request);
    }

    @GetMapping
    public ListCommonResponse<ListUserInquiryResponse> getAllInquiries(
            @AuthenticationPrincipal(authority = Authority.USER) User user
    ) {
        return CommonResponse.ok(
                getAllUserInquiriesUseCase.execute(user)
        );
    }

    @GetMapping("/{inquiry-id}")
    public SingleCommonResponse<UserInquiryResponse> getInquiry(
            @AuthenticationPrincipal(authority = Authority.USER) User user,
            @PathVariable(name = "inquiry-id") Long inquiryId
    ) {
        return CommonResponse.ok(
                getUserInquiryUseCase.execute(user, inquiryId)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{inquiry-id}")
    public void updateInquiry(
            @AuthenticationPrincipal(authority = Authority.USER) User user,
            @PathVariable(name = "inquiry-id") Long inquiryId
    ) {
        updateInquiryUseCase.execute(user, inquiryId);
    }
}
