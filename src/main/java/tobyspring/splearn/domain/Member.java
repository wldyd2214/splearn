package tobyspring.splearn.domain;

import lombok.Getter;

@Getter
public class Member {
    private String email;
    private String nickname;
    private String password;
    private MemberStatus status;

    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.status = MemberStatus.PENDING;
    }
}
