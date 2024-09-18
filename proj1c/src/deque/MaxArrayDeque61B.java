package deque;

import java.util.Comparator;
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comp;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        this.comp = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxValue = null;
        for (T element : this) {
            if (element != null && (maxValue == null || comp.compare(element, maxValue) > 0)) {
                maxValue = element;
            }
        }
        return maxValue;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxValue = null;
        for (T element : this) {
            if (maxValue == null || c.compare(element, maxValue) > 0) {
                maxValue = element;
            }
        }
        return maxValue;
    }
}
