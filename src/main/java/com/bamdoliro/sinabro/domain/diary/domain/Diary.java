package com.bamdoliro.sinabro.domain.diary.domain;

import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_diary")
@Entity
public class Diary extends BaseTimeEntity {

    @Id
    @Column(name = "diary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT", length = 5000)
    private String content;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "tbl_emotion",
            joinColumns = @JoinColumn(name = "diary_id")
    )
    @Enumerated(EnumType.STRING)
    private List<Emotion> emotionList;

    @Column(nullable = false)
    private LocalDate writtenAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Diary(String content, List<Emotion> emotionList, LocalDate writtenAt, User author) {
        this.content = content;
        this.emotionList = emotionList;
        this.writtenAt = writtenAt;
        this.author = author;
    }

    public boolean isAuthor(User user) {
        return author.equals(user);
    }

    public void update(String content, List<Emotion> emotionList) {
        this.content = content;
        this.emotionList = emotionList;
    }
}
