import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
        .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
        .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void addFirstFromEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(1);
    }

    @Test
    public void addLastFromEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addLast(1);
    }

    @Test
    public void addFirstFromNonEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addFirst(8);
    }

    @Test
    public void addLastFromNonEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
    }

    @Test
    public void getValidTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(10);
        assertThat(a.get(0)).isEqualTo(10);
        a.addLast(20);
        a.addFirst(30); // [30, 10, 20]
        assertThat(a.get(0)).isEqualTo(30);
    }

    @Test
    public void getOOBLarge() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.get(0)).isNull();
        assertThat(a.get(20)).isNull();
        a.addFirst(28);
        a.addFirst(2);
        a.addLast(8);
        a.addFirst(33);
        assertThat(a.get(10)).isNull();
    }

    @Test
    public void getOOBNeg() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.get(-1)).isNull();
        assertThat(a.get(-20)).isNull();
    }

    @Test
    public void sizeTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.size()).isEqualTo(0);
        a.addLast(10);
        a.addLast(60);
        a.addLast(10);
        assertThat(a.size()).isEqualTo(3);
    }

    @Test
    public void sizeAfterRemoveToEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addLast(10);
        a.addLast(60);
        a.addLast(10);
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        assertThat(a.size()).isEqualTo(0);
    }

    @Test
    public void sizeAfterRemoveFromEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.removeFirst()).isNull();
        assertThat(a.removeLast()).isNull();
    }

    @Test
    public void isEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.isEmpty()).isTrue();
        a.addLast(100);
        assertThat(a.isEmpty()).isFalse();
    }

    @Test
    public void toListEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        assertThat(a.toList()).containsExactly().inOrder();
    }

    @Test
    public void toListNonEmptyTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addLast(10);
        assertThat(a.toList()).containsExactly(10).inOrder();
        a.addLast(50); // [10, 50]
        a.addLast(28); // [10, 50, 28]
        a.addFirst(2); // [2, 10, 50, 28]
        assertThat(a.toList()).containsExactly(2, 10, 50, 28).inOrder();
        a.addFirst(9); // [9, 2, 10, 50, 28]
        a.addLast(38); // [9, 2, 10, 50, 28, 38]

        assertThat(a.toList()).containsExactly(9, 2, 10, 50, 28, 38).inOrder();
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(10);
        a.addFirst(20); // [20, 10]
        assertThat(a.removeFirst()).isEqualTo(20);
        assertThat(a.toList()).containsExactly(10).inOrder();
        a.addLast(2); // [10, 2]
        a.addLast(7); // [10, 2, 7]
        a.addLast(19); // [10, 2, 7, 19]
        a.addFirst(32); // [32, 10, 2, 7, 19]
        assertThat(a.toList()).containsExactly(32, 10, 2, 7, 19).inOrder();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(19).inOrder();
        a.removeFirst();
        assertThat(a.toList()).containsExactly().inOrder();
    }

    @Test
    public void removeLastTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(10);
        a.addFirst(20); // [20, 10]
        assertThat(a.removeLast()).isEqualTo(10);
        assertThat(a.toList()).containsExactly(20).inOrder();
        a.addLast(2); // [20, 2]
        a.addLast(7); // [20, 2, 7]
        a.addLast(19); // [20, 2, 7, 19]
        a.addFirst(32); // [32, 20, 2, 7, 19]
        assertThat(a.toList()).containsExactly(32, 20, 2, 7, 19).inOrder();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        assertThat(a.toList()).containsExactly(32).inOrder();
        a.removeLast();
        assertThat(a.toList()).containsExactly().inOrder();
    }

    @Test
    public void addFirstTriggerResizeTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addFirst(8);
        assertThat(a.toList()).containsExactly(8, 7, 6, 5, 4, 3, 2, 1).inOrder();
        a.addFirst(100);
        assertThat(a.toList()).containsExactly(100, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
        assertThat(a.size()).isEqualTo(9);
    }

    @Test
    public void addLastTriggerResizeTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        assertThat(a.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8).inOrder();
        a.addLast(100);
        assertThat(a.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 100).inOrder();
        assertThat(a.size()).isEqualTo(9);
    }

    @Test
    public void removeFirstTriggerResizeTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        for (int i = 0; i < 16; i++) {
            a.addFirst(i + 1);
        }
        assertThat(a.size()).isEqualTo(16);
        for (int j = 0; j < 11; j++) {
            a.removeFirst();
        }
        assertThat(a.toList()).containsExactly(5, 4, 3, 2, 1).inOrder();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        assertThat(a.toList()).containsExactly(2, 1).inOrder();
    }

    @Test
    public void removeLastTriggerResizeTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        for (int i = 0; i < 16; i++) {
            a.addLast(i + 1);
        }
        assertThat(a.size()).isEqualTo(16);
        for (int j = 0; j < 11; j++) {
            a.removeLast();
        }
        assertThat(a.toList()).containsExactly(1, 2, 3, 4, 5).inOrder();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        assertThat(a.toList()).containsExactly(1, 2).inOrder();
    }

    @Test
    public void resizeUpNDownTest() {
        ArrayDeque61B<Integer> a = new ArrayDeque61B<>();
        // 33個items(1-33), usageRatio = 33 / 64 = 0.515
        for (int i = 0; i < 33; i++) {
            a.addLast(i + 1);
        }
        // remove值到usageRatio <= 0.25 (list剩下16個items)
        // removeFirst後 [17-33]
        for (int j = 0; j < 16; j++) {
            a.removeFirst();
        }
        a.removeLast();
        a.removeLast();
        a.removeLast();
        assertThat(a.toList()).containsExactly(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30).inOrder();
    }
}
