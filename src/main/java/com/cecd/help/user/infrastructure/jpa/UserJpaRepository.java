package com.cecd.help.user.infrastructure.jpa;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.user.domain.type.EUserRole;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByEmail(String email);

    @Query("select u.id as id, u.userRole as role, u.password as password "
            + "from User u where u.id = :id and u.refreshToken is not null")
    Optional<UserSecurityForm> findFormById(@Param("id") UUID id);

    @Query("select u.id as id, u.userRole as role, u.password as password from User u where u.id = :id and u.refreshToken = :refreshToken")
    Optional<UserSecurityForm> findFormByIdAndRefreshToken(@Param("id") UUID id, @Param("refreshToken") String refreshToken);

    @Query("select u.id as id, u.userRole as role, u.password as password from User u where u.socialId = :serialId and u.provider = :provider")
    Optional<UserSecurityForm> findFormBySerialIdAndProvider(@Param("serialId") String serialId, @Param("provider") ELoginProvider provider);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.refreshToken = :refreshToken where u.id = :id")
    void updateRefreshToken(UUID id, String refreshToken);

    Optional<User> findBySocialId(String socialId);



    interface UserSecurityForm {
        UUID getId();
        EUserRole getRole();
        String getPassword();

        // User To UserSecurityForm
        static UserSecurityForm of(User user) {
            return new UserSecurityForm() {
                @Override
                public UUID getId() {
                    return user.getId();
                }

                @Override
                public EUserRole getRole() {
                    return user.getUserRole();
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }
            };
        }
    }
}
