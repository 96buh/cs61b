import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {

    @Test
    public void equalsLinkedListDeque61BTest() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld1.addFirst(30);
        lld1.addFirst(20);
        lld1.addFirst(10);

        lld2.addLast(10);
        lld2.addLast(20);
        lld2.addLast(30);
        assertThat(lld1.equals(lld2)).isTrue();

        lld2.addLast(121);
        assertThat(lld2.equals(lld1)).isFalse();
    }

    @Test
    public void equalsEmptyLinkedListTest() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> lld2 = new LinkedListDeque61B<>();
        assertThat(lld1.equals(lld2)).isTrue();
    }

    @Test
    public void linkedListIteratorTest() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(10);
        lld1.addLast(20);
        lld1.addLast(30); // [10, 20, 30]
        for (int i : lld1) {
            System.out.println(i);
            assertThat(i).isEqualTo(i);
        }
    }

    @Test
    public void linkedListToStringTest() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }
}
