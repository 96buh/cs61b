import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void addFirstTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        lst.addFirst(1);
        lst.addFirst(2);
        lst.addFirst(3);
        lst.addFirst(4);
        lst.addFirst(5);
        lst.addFirst(6);
        lst.addFirst(7);
        lst.addFirst(8);
        lst.addFirst(9);
        lst.addFirst(10);

    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        lst.addLast(1);
        lst.addLast(2);
        lst.addLast(3);
        lst.addLast(4);
        lst.addLast(5);
        lst.addLast(6);
        lst.addLast(7);
        lst.addLast(8);
        assertThat(lst.get(7)).isEqualTo(8);
        assertThat(lst.toList()).containsExactly(1,2,3,4,5,6,7,8).inOrder();
        lst.addLast(9);
        lst.addLast(10);


    }

    @Test
    public void getTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        lst.addLast(1);
        lst.addLast(2);
        lst.addLast(3);
        lst.addLast(4);
        assertThat(lst.get(3)).isEqualTo(4);
        lst.addFirst(10);
        assertThat(lst.get(0)).isEqualTo(10);
        lst.addFirst(20);
        assertThat(lst.get(0)).isEqualTo(20);
        assertThat(lst.get(1)).isEqualTo(10);
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        assertThat(lst.size()).isEqualTo(3);
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        assertThat(lst.size()).isEqualTo(8);
        lst.addLast(100); // [8, 6, 4, 3, 1, 2, 5, 7, 100]
        lst.addLast(77);  // [8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
        lst.addFirst(87); // [87, 8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
        assertThat(lst.size()).isEqualTo(11);
    }


    @Test
    public void resizeTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        assertThat(lst.size()).isEqualTo(8);
        lst.addLast(100); // [8, 6, 4, 3, 1, 2, 5, 7, 100]
        lst.addLast(77);  // [8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
        lst.addFirst(87); // [87, 8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        assertThat(lst.isEmpty()).isTrue();
        lst.addFirst(100);
        assertThat(lst.isEmpty()).isFalse();
        lst.addLast(200); // [100, 200]
        lst.removeFirst();
        assertThat(lst.isEmpty()).isFalse();
        lst.removeLast();
        assertThat(lst.isEmpty()).isTrue();
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        assertThat(lst.toList()).containsExactly().inOrder();
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        assertThat(lst.toList()).containsExactly(4, 3, 1, 2, 5).inOrder();
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        assertThat(lst.toList()).containsExactly(6, 4, 3, 1, 2, 5).inOrder();
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        assertThat(lst.toList()).containsExactly(8, 6, 4, 3,1, 2, 5, 7).inOrder();
        lst.addLast(100); // [8, 6, 4, 3, 1, 2, 5, 7, 100]
        lst.addLast(77);  // [8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
        assertThat(lst.toList()).containsExactly(8, 6, 4, 3,1 ,2, 5, 7, 100, 77).inOrder();
        lst.addFirst(87); // [87, 8, 6, 4, 3, 1, 2, 5, 7, 100, 77]
        assertThat(lst.toList()).containsExactly(87, 8, 6, 4, 3, 1, 2, 5, 7, 100, 77).inOrder();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        assertThat(lst.removeFirst()).isEqualTo(null);
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        lst.removeFirst();
        lst.removeFirst();
        lst.removeFirst();
        lst.removeFirst();
        assertThat(lst.toList()).containsExactly(1, 2, 5, 7).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        assertThat(lst.removeLast()).isEqualTo(null);
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        lst.removeLast();
        assertThat(lst.toList()).containsExactly(8, 6, 4, 3).inOrder();
        lst.removeFirst();
        assertThat(lst.toList()).containsExactly(6, 4, 3).inOrder();
    }

    @Test
    public void resizeDownTest() {
        Deque61B<Integer> lst = new ArrayDeque61B<>();
        for(int i = 1; i < 21; i++) {
            lst.addLast(i);
        }
        for(int j = 0; j < 20; j++) {
            lst.removeLast();
        }
        lst.addLast(1); // [1]
        lst.addLast(2); // [1, 2]
        lst.addFirst(3); // [3, 1, 2]
        lst.addFirst(4); // [4, 3, 1, 2]
        lst.addLast(5); // [4, 3, 1, 2, 5]
        lst.addFirst(6); // [6, 4, 3, 1, 2, 5]
        lst.addLast(7);  // [6, 4, 3, 1, 2, 5, 7]
        lst.addFirst(8); // [8, 6, 4, 3, 1, 2, 5, 7]
        assertThat(lst.get(0)).isEqualTo(8);
        assertThat(lst.toList()).containsExactly(8, 6, 4, 3, 1, 2, 5, 7).inOrder();
        lst.addLast(1);
        lst.addLast(2);
        lst.addFirst(3);
        lst.addFirst(4);
        lst.addLast(5);
        lst.addFirst(6);
        lst.addLast(7);
        lst.addFirst(8);
        for(int w = 0; w < 14; w++){
            lst.removeFirst();
        }
    }
}
