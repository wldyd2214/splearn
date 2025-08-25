package tobyspring.splearn.domain;

public class MemberFixture {

    public static MemberRegisterCommand createMemberRegisterCommand(String email) {
        return new MemberRegisterCommand(email, "Jiyong", "verysecret");
    }

    public static MemberRegisterCommand createMemberRegisterCommand() {
        return createMemberRegisterCommand("jypark@splearn.app");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }
}
