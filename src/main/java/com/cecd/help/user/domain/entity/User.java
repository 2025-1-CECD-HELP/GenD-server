package com.cecd.help.user.domain.entity;


import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "social_id", nullable = false, unique = true)
    private String socialId;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "goal_distance")
    private Double goalDistance;

    @Column(name = "user_role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EUserRole userRole;

    @Column(name = "login_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Column(name = "profile_image_url", length = 2048)
    private String profileImageUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "credit")
    @ColumnDefault("0")
    private Integer credit;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "reports_cnt")
    @ColumnDefault("0")
    private Integer reportsCnt;

    //--------------------------------------------------

    @Builder(access = AccessLevel.PRIVATE)
    public User(String socialId, String nickname, EUserRole userRole, ELoginProvider provider, String fcmToken) {
        this.socialId = socialId;
        this.nickname = nickname;
        this.goalDistance = 0.0;
        this.userRole = userRole;
        this.provider = provider;
        this.fcmToken = fcmToken;
        this.profileImageUrl = null;
        this.refreshToken = null;
        this.credit = 0;
        this.createdAt = LocalDateTime.now();
        this.reportsCnt = 0;
    }

    public static User toUserEntity(String socialId, String nickname, EUserRole userRole, ELoginProvider provider,
                                    String fcmToken) {
        return User.builder()
                .socialId(socialId)
                .nickname(nickname)
                .userRole(userRole)
                .provider(provider)
                .fcmToken(fcmToken)
                .build();
    }

    public void updateGoal(Double goalDistance) {
        this.goalDistance = goalDistance;
    }


    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void updateReportCnt() {
        this.reportsCnt += 1;
    }
}
