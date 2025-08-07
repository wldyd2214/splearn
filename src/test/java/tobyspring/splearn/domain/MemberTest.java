package tobyspring.splearn.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    @Test
    void createMember() {
        var member = new Member("jypark@splearn.app", "Jiyong", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
    
    @Test
    void constructorNullCheck() {
        assertThatThrownBy(() -> new Member(null, "Jiyong", "secret"))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate() {
        var member = new Member("jypark@splearn.app", "Jiyong", "secret");
        member.activate();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }
}