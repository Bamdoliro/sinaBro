package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.presentation.answer.dto.response.AnswerResponse;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.request.UpdateInquiryStatusRequest;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.InquiryAdminResponse;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.ListInquiryAdminResponse;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.request.InquiryRequest;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.InquiryUserResponse;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.ListInquiryUserResponse;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public class InquiryFixture {

    public static Inquiry createInquiry() {
        return new Inquiry(
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                "솔이가 편지를 보낼 때 갑자기 이상한 ~~말투를 써요 빠른 해결 부탁드립니다 몰입이 확 깨네요",
                UserFixture.createUser()
        );
    }

    public static InquiryRequest createInquiryRequest() {
        return new InquiryRequest(
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                "솔이가 편지를 보낼 때 갑자기 이상한 ~~말투를 써요 빠른 해결 부탁드립니다 몰입이 확 깨네요"
        );
    }

    public static ListInquiryUserResponse createListInquiryUserResponse() {
        return new ListInquiryUserResponse(
                1L,
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                InquiryStatus.WAITING,
                LocalDateTime.now()
        );
    }

    public static InquiryUserResponse createInquiryUserResponse() {
        return new InquiryUserResponse(
                1L,
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                "솔이가 편지를 보낼 때 갑자기 이상한 ~~말투를 써요 빠른 해결 부탁드립니다 몰입이 확 깨네요",
                InquiryStatus.WAITING,
                List.of(
                        new AnswerResponse(AnswerFixture.createAnswer())
                ),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static ListInquiryAdminResponse createListInquiryAdminResponse() {
        return new ListInquiryAdminResponse(
                1L,
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                InquiryStatus.WAITING,
                new UserResponse(UserFixture.createUser()),
                LocalDateTime.now()
        );
    }

    public static InquiryAdminResponse createInquiryAdminResponse() {
        return new InquiryAdminResponse(
                1L,
                "솔이가 편지를 보낼 때 갑자기 이상한 말투를 써요",
                "솔이가 편지를 보낼 때 갑자기 이상한 ~~말투를 써요 빠른 해결 부탁드립니다 몰입이 확 깨네요",
                InquiryStatus.WAITING,
                List.of(
                        new AnswerResponse(AnswerFixture.createAnswer())
                ),
                new UserResponse(UserFixture.createUser()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static UpdateInquiryStatusRequest createUpdateInquiryStatusRequest() {
        return new UpdateInquiryStatusRequest(
                InquiryStatus.IN_PROGRESS
        );
    }
}
