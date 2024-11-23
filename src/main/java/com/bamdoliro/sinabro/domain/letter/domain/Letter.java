package com.bamdoliro.sinabro.domain.letter.domain;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_letter")
@Entity
public class Letter extends BaseTimeEntity {

    @Id
    @Column(name = "letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT", length = 5000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Letter(String content, User user) {
        this.content = content;
        this.user = user;
    }
}
