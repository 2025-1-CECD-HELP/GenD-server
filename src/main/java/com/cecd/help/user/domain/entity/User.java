package com.cecd.help.user.domain.entity;


import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import com.cecd.help.workspace.domain.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name =  "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

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

    @Column(name = "is_alarm")
    private Boolean isAlarm;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Member> members;

    //--------------------------------------------------

    @Builder
    public User(String socialId, String nickname, String email, EUserRole userRole, String password, ELoginProvider provider, String fcmToken, String loginId) {
        this.socialId = socialId;
        this.nickname = nickname;
        this.userRole = userRole;
        this.provider = provider;
        this.fcmToken = fcmToken;
        this.password = password;
        this.profileImageUrl = null;
        this.refreshToken = null;
        this.email = email;
        this.loginId = loginId;
        this.createdAt = LocalDateTime.now();
        this.isAlarm = true;
    }

    public void updateAlarm(Boolean isAlarm) {
        this.isAlarm = !isAlarm;
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

}

