package tobyspring.splearn.domain;

public record MemberCreateCommand(String email, String nickname, String password) {
}
