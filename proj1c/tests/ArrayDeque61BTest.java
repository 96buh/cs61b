import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void toStringTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
        Deque61B<Integer> m = new ArrayDeque61B<>();
        m.addLast(100);
        m.addLast(2);
        m.addLast(34); // [100, 2, 34]
        m.addFirst(500);
        m.addFirst(40);
        m.addFirst(40); // [40, 40, 500, 100, 2, 34]
        m.addLast(666); // [40, 40, 500, 100, 2, 34, 666]
        assertThat(m.toString()).isEqualTo("[40, 40, 500, 100, 2, 34, 666]");
    }

    @Test
    public void equalsTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<String> lld2 = new ArrayDeque61B<>();
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
