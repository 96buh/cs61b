import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }


    @Test
    public void integerTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        assertThat(m.max()).isEqualTo(null);
        m.addLast(100);
        m.addLast(2);
        m.addLast(34);
        assertThat(m.max()).isEqualTo(100);
        m.addFirst(500);
        assertThat(m.max()).isEqualTo(500);
        m.addFirst(40);
        m.addFirst(40);
        assertThat(m.max()).isEqualTo(500);
        m.addLast(666);
        assertThat(m.max()).isEqualTo(666);
    }

}
