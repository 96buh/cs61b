import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {
    @Test
    public void toStringTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }

    @Test
    public void equalsTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        assertThat(lld1.equals(lld2)).isTrue();

        lld2.addFirst("wow");
        assertThat(lld1.equals(lld2)).isFalse();

        lld1.addFirst("wow");
        assertThat(lld2.equals(lld1)).isTrue();
    }
}
