package tobyspring.splearn.application.provided;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import tobyspring.splearn.SplearnTestConfiguration;
import tobyspring.splearn.domain.DuplicateEmailException;
import tobyspring.splearn.domain.Member;
import tobyspring.splearn.domain.MemberFixture;
import tobyspring.splearn.domain.MemberRegisterCommand;
import tobyspring.splearn.domain.MemberStatus;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
//@TestConstructor(autowireMode = AutowireMode.ALL)
public record MemberRegisterTest(MemberRegister memberRegister) {

    @Test
    void register() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterCommand());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterCommand());

        assertThatThrownBy(() -> memberRegister.register(MemberFixture.createMemberRegisterCommand()))
            .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void memberRegisterCommandTestFail() {
        extracted(new MemberRegisterCommand("jypark@splearn.app", "Jiyo", "longsecret"));
        extracted(new MemberRegisterCommand("jypark@splearn.app", "Jiyong_______________", "longsecret"));
        extracted(new MemberRegisterCommand("jyparksplearn.app", "Jiyong", "longsecret"));
    }

    public void extracted(MemberRegisterCommand invalid) {
        assertThatThrownBy(() -> memberRegister.register(invalid))
            .isInstanceOf(ConstraintViolationException.class);
    }
}
