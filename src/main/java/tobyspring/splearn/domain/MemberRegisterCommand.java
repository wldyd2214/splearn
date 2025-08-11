package tobyspring.splearn.domain;

public record MemberRegisterCommand(String email, String nickname, String password) {
}
