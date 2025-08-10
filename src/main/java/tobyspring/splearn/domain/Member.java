package tobyspring.splearn.domain;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Getter
@ToString
public class Member {
    private String email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String email, String nickname, String passwordHash) {
        this.email = requireNonNull(email);
        this.nickname = requireNonNull(nickname);
        this.passwordHash = requireNonNull(passwordHash);

        this.status = MemberStatus.PENDING;
    }

    public static Member create(MemberCreateCommand command, PasswordEncoder passwordEncoder) {
        return Member.builder()
                     .email(command.email())
                     .nickname(command.nickname())
                     .passwordHash(passwordEncoder.encode(command.password()))
                     .build();
    }

//    public static Member create(String email, String nickname, String password, PasswordEncoder passwordEncoder) {
//        return Member.builder()
//                     .email(email)
//                     .nickname(nickname)
//                     .passwordHash(passwordEncoder.encode(password))
//                     .build();
//    }

    public void activate() {
        state(status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATE;
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, passwordHash);
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
