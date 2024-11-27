package com.bamdoliro.sinabro.infrastructure.persistence.inquiry;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("select i " +
            "from Inquiry i " +
            "where i.user = :user " +
            "and (:status is null or i.status = :status)")
    List<Inquiry> findAllByUserAndStatus(@Param("user") User user, @Param("status") InquiryStatus status);

    @Query("select i " +
            "from Inquiry i " +
            "where (:status is null or i.status = :status)")
    List<Inquiry> findAllByStatus(@Param("status") InquiryStatus status);

    Long id(Long id);
}
