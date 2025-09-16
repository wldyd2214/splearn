package tobyspring.splearn.domain.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;
import tobyspring.splearn.domain.AbstractEntity;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded
    private Profile profile;

    private String introduction;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberDetail(Profile profile, String introduction, LocalDateTime registeredAt,
        LocalDateTime activatedAt, LocalDateTime deactivatedAt) {
        this.profile = profile;
        this.introduction = introduction;
        this.registeredAt = registeredAt;
        this.activatedAt = activatedAt;
        this.deactivatedAt = deactivatedAt;
    }

    static MemberDetail create() {
        return MemberDetail.builder()
            .registeredAt(LocalDateTime.now())
            .build();
    }

    void activate() {
        Assert.isTrue(activatedAt == null, "이미 activatedAt은 설정되었습니다.");

        this.activatedAt = LocalDateTime.now();
    }

    void deactivate() {
        Assert.isTrue(deactivatedAt == null, "이미 deactivatedAt은 설정되었습니다.");

        this.deactivatedAt = LocalDateTime.now();
    }

    void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.profile = new Profile(updateRequest.profileAddress());
        this.introduction = Objects.requireNonNull(updateRequest.introduction());
    }
}
