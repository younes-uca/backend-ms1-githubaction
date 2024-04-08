import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class testapplication {

    @Test
    void itShouldPrintHello() {
        System.out.println("hello");
        Assertions.assertThat(true).isEqualTo(true);

    }
}
