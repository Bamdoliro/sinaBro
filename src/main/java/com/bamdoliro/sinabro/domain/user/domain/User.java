package com.bamdoliro.sinabro.domain.user.domain;

import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.domain.user.domain.value.Password;
import com.bamdoliro.sinabro.shared.entity.BaseTimeEntity;
import com.bamdoliro.sinabro.shared.util.PasswordUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Authority authority;

    @Builder
    public User(String email, String name, String password, Authority authority) {
        this.email = email;
        this.name = name;
        this.password = new Password(PasswordUtil.encode(password));
        this.authority = authority;
    }
}
