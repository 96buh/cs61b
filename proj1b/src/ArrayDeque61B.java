import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int size;

    public ArrayDeque61B() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(Object x) {
        if (size == items.length) {
            resize(size * 2);
        }

        if (nextFirst - 1 < 0) {
            items[nextFirst] = (T) x;
            nextFirst = Math.floorMod(nextFirst - 1, items.length);
        } else {
            items[nextFirst] = (T) x;
            nextFirst -= 1;
        }

        size += 1;
    }

    @Override
    public void addLast(Object x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = (T) x;
        if (nextLast + 1 == items.length) {
            nextLast = Math.floorMod(nextLast + 1, items.length);
        } else {
            nextLast += 1;
        }

        size += 1;
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        int startIndex = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            if (startIndex + 1 > items.length) {
                startIndex = Math.floorMod(startIndex + 1, items.length);
            } else {
                startIndex += 1;
            }
            returnList.add(items[startIndex - 1]);

        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int startIndex = Math.floorMod(nextFirst + 1, items.length);

        for (int i = 0; i < items.length; i++) {
            if (startIndex + 1 > items.length) {
                startIndex = Math.floorMod(startIndex + 1, items.length);
            } else {
                startIndex += 1;
            }
            a[i] = items[startIndex - 1];
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    public void resizeDown(double capacity) {
        int intCapacity = (int) capacity;
        T[] a = (T[]) new Object[intCapacity];
        int startIndex = Math.floorMod(nextFirst + 1, items.length);

        for (int i = 0; i < size; i++) {
            if (startIndex + 1 > items.length) {
                startIndex = Math.floorMod(startIndex + 1, items.length);
            } else {
                startIndex += 1;
            }
            a[i] = items[startIndex - 1];
        }
        nextFirst = intCapacity - 1;
        nextLast = size;
        items = a;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < items.length * 0.25) {
            resizeDown(0.5 * items.length);
        }
        T deletedItem = items[nextFirst];
        if (nextFirst + 1 == items.length) {
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            items[nextFirst] = null;
        } else {
            nextFirst += 1;
            items[nextFirst] = null;
        }
        size -= 1;
        return deletedItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < items.length * 0.25) {
            resizeDown(0.5 * items.length);
        }
        T deletedItem;
        if (nextLast - 1 < 0) {
            nextLast = Math.floorMod(nextLast - 1, items.length);
            deletedItem = items[nextLast];
            items[nextLast] = null;
        } else {
            nextLast -= 1;
            deletedItem = items[nextLast];
            items[nextLast] = null;
        }
        size -= 1;
        return deletedItem;
    }

    @Override
    public T get(int index) {
        if (nextFirst + 1 + index >= items.length) {
            int a = Math.floorMod(nextFirst + 1 + index, items.length);
            return items[a];
        }
        return items[nextFirst + 1 + index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
