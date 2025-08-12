package tobyspring.splearn.application.required;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import tobyspring.splearn.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static tobyspring.splearn.domain.MemberFixture.*;

@DataJdbcTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void createMember() {
      Member member = Member.register(createMemberRegisterCommand(), createPasswordEncoder());

      assertThat(member.getId()).isNull();

      memberRepository.save(member);

      assertThat(member.getId()).isNotNull();

      entityManager.flush();
    }
}