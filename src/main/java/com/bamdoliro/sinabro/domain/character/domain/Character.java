package com.bamdoliro.sinabro.domain.character.domain;

import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_character")
@Entity
public class Character extends BaseTimeEntity {

    @Id
    @Column(name = "character_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CharacterType type;

    @Column(nullable = false)
    private Integer friendship;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Character(CharacterType type, User user) {
        this.type = type;
        this.friendship = 3;
        this.user = user;
    }

    public void increaseFriendShip() {
        this.friendship++;
    }
}
