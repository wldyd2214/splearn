package tobyspring.splearn.domain;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {
    @Test
    void test() {
        var email1 = new Email("jypark@splearn.app");
        var email2 = new Email("jypark@splearn.app");

        assertThat(email1).isEqualTo(email2);
    }
}