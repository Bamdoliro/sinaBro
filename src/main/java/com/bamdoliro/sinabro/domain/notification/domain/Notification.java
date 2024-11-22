package com.bamdoliro.sinabro.domain.notification.domain;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_notification")
@Entity
public class Notification extends BaseTimeEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }
}
