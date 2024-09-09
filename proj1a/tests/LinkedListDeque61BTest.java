import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        // empty list test
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        // not empty test
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addFirst(10);
        lld2.addLast(40);
        lld2.addFirst(670);
        assertThat(lld2.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        // empty list test
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        // one element
        lld1.addFirst(10);
        assertThat(lld1.size()).isEqualTo(1);
        lld1.addFirst(54);
        lld1.addFirst(20);
        assertThat(lld1.size()).isEqualTo(3);
        lld1.addLast(1000);
        lld1.addLast(666);
        lld1.addLast(3332);
        assertThat(lld1.size()).isEqualTo(6);
        // string list test
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addFirst("Wukong");
        lld2.addLast("goku");
        assertThat(lld2.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        // negative index
        assertThat(lld1.get(-1)).isEqualTo(null);
        // too large index
        assertThat(lld1.get(1000000000)).isEqualTo(null);
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        assertThat(lld1.get(0)).isEqualTo(0);
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.get(0)).isEqualTo(-2);
        assertThat(lld1.get(3)).isEqualTo(1);
        lld1.addLast(1000); // [-2, -1, 0, 1, 2, 1000]
        assertThat(lld1.get(5)).isEqualTo(1000);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        // negative index
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
        // too large index
        assertThat(lld1.getRecursive(1000000000)).isEqualTo(null);
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.getRecursive(0)).isEqualTo(0);
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.size()).isEqualTo(5);
        assertThat(lld1.getRecursive(0)).isEqualTo(-2);
        assertThat(lld1.getRecursive(3)).isEqualTo(1);
        lld1.addLast(1000); // [-2, -1, 0, 1, 2, 1000]
        assertThat(lld1.getRecursive(5)).isEqualTo(1000);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();

        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
        lld1.addLast(2);   // [0, 1, 2]
        lld1.addFirst(-2); // [-2, 0, 1, 2]
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(0, 1, 2).inOrder();
        lld1.removeFirst(); // [1, 2]
        assertThat(lld1.size()).isEqualTo(2);
        lld1.removeFirst(); // [2]
        assertThat(lld1.toList()).containsExactly(2).inOrder();
        lld1.removeFirst(); // []
        // remove to empty list size test
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.toList()).containsExactly().inOrder();
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addLast(10);
        assertThat(lld1.toList()).containsExactly(10).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.removeLast()).isEqualTo(null);
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(-1, 0).inOrder();
        lld1.addLast(2);   // [-1, 0, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 2]
        lld1.addFirst(100); // [100, -2, -1, 0, 2]
        lld1.removeLast();   // [100, -2, -1, 0]
        lld1.removeLast();   // [100, -2, -1]
        assertThat(lld1.toList()).containsExactly(100, -2, -1).inOrder();
        lld1.removeLast();   // [100, -2]
        lld1.removeLast();   // [100]
        assertThat(lld1.toList()).containsExactly(100).inOrder();
        lld1.removeLast(); // []
        assertThat(lld1.toList()).containsExactly().inOrder();
    }

    @Test
    public void addFirstAfterRemoveToEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0); // [0]
        lld1.addFirst(1); // [1, 0]
        lld1.addFirst(10); // [10, 1, 0]
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.addFirst(666);
        assertThat(lld1.toList()).containsExactly(666).inOrder();
    }

    @Test
    public void addLastAfterRemoveToEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0); // [0]
        lld1.addFirst(1); // [1, 0]
        lld1.addFirst(10); // [10, 1, 0]
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.addLast(666);
        assertThat(lld1.toList()).containsExactly(666).inOrder();
    }

    @Test
    public void integrationTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.toList()).containsExactly().inOrder();
        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst(10); // [10]
        lld1.addFirst(100); // [100, 10]
        lld1.addLast(55); // [100, 10, 55]
        assertThat(lld1.size()).isEqualTo(3);
        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.toList()).containsExactly(100, 10, 55).inOrder();
        assertThat(lld1.get(2)).isEqualTo(55);
        lld1.addFirst(43); // [43, 100, 10, 55]
        assertThat(lld1.getRecursive(0)).isEqualTo(43);
        assertThat(lld1.get(0)).isEqualTo(43);
        assertThat(lld1.getRecursive(2)).isEqualTo(10);

        lld1.removeLast(); // [43, 100, 10]
        lld1.removeFirst(); // [100, 10]
        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.get(1)).isEqualTo(10);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.removeFirst();
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly().inOrder();
        // removeFirst empty list
        assertThat(lld1.removeFirst()).isEqualTo(null);
        lld1.addLast(10);
        lld1.addFirst(23);
        lld1.addLast(100);
        lld1.addLast(500);
        assertThat(lld1.toList()).containsExactly(23, 10, 100, 500).inOrder();
        assertThat(lld1.isEmpty()).isFalse();
        assertThat(lld1.getRecursive(200)).isEqualTo(null);
        assertThat(lld1.getRecursive(-23)).isEqualTo(null);
        assertThat(lld1.get(3)).isEqualTo(500);
        assertThat(lld1.getRecursive(3)).isEqualTo(500);


    }
}