package com.bamdoliro.sinabro.presentation.inquiry.admin;

import com.bamdoliro.sinabro.application.inquiry.admin.GetAllInquiriesAdminUseCase;
import com.bamdoliro.sinabro.application.inquiry.admin.GetInquiryAdminUseCase;
import com.bamdoliro.sinabro.application.inquiry.admin.UpdateInquiryStatusUseCase;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.request.UpdateInquiryStatusRequest;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.ListInquiryAdminResponse;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.InquiryAdminResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/inquiries")
@RestController
public class AdminInquiryController {

    private final GetAllInquiriesAdminUseCase getAllInquiriesAdminUseCase;
    private final GetInquiryAdminUseCase getInquiryAdminUseCase;
    private final UpdateInquiryStatusUseCase updateInquiryStatusUseCase;

    @GetMapping
    public ListCommonResponse<ListInquiryAdminResponse> getAllInquiries(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @RequestParam(required = false) InquiryStatus status
    ) {
        return CommonResponse.ok(
                getAllInquiriesAdminUseCase.execute(status)
        );
    }

    @GetMapping("/{inquiry-id}")
    public SingleCommonResponse<InquiryAdminResponse> getInquiry(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "inquiry-id") Long inquiryId
    ) {
        return CommonResponse.ok(
                getInquiryAdminUseCase.execute(inquiryId)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{inquiry-id}")
    public void updateInquiryStatus(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "inquiry-id") Long inquiryId,
            @RequestBody @Valid UpdateInquiryStatusRequest request
    ) {
        updateInquiryStatusUseCase.execute(inquiryId, request);
    }
}
