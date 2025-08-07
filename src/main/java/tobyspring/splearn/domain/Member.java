package tobyspring.splearn.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Member {
    private String email;
    private String nickname;
    private String passwordHash;
    private MemberStatus status;

    public Member(String email, String nickname, String passwordHash) {
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.passwordHash = Objects.requireNonNull(passwordHash);

        this.status = MemberStatus.PENDING;
    }

    public static Member create(String email, String nickname, String password, PasswordEncoder passwordEncoder) {
        return new Member(email, nickname, passwordEncoder.encode(password));
    }

    public void activate() {
//        state(status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");
//
//        this.status = MemberStatus
    }

    public void deactivate() {

    }
}
