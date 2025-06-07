package deque;
import java.util.Comparator;
import java.util.Iterator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            T maxItem = iterator.next();
            for (T item : iterable) {
                int comp = item.compareTo(maxItem);
                if (comp > 0) {
                    maxItem = item;
                }
            }
            return maxItem;
        }
        return null;
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        Iterator<T> a = iterable.iterator();
        if (a.hasNext()) {
            T maxItem = a.next();
            for (T item : iterable) {
                if (comp.compare(item, maxItem) > 0) {
                    maxItem = item;
                }
            }
            return maxItem;
        }
        return null;
    }
}
