package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int arrayLengthToResizeDown = 16; // 如果array大小比這個大的話，在考慮resize down

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /* myPointer指的是 nextFirst或是nextLast
       和用來計算要放在array的哪個位置(index)
    */
    private int whereToPlace(int myPointer, int capacity) {
        return Math.floorMod(myPointer, capacity);
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextFirst < 0) {
            nextFirst = whereToPlace(nextFirst, items.length);
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = this.get(i);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextLast >= items.length) {
            nextLast = whereToPlace(nextLast, items.length);
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return returnList;
        }
        for (int i = 0; i < size; i++) {
            returnList.add(this.get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        nextFirst += 1;
        if (nextFirst >= items.length) {
            nextFirst = whereToPlace(nextFirst, items.length);
        }
        size -= 1;
        // 如果array length >= 16 以及 usageRatio <= 0.25就resize down
        if (items.length >= arrayLengthToResizeDown && ((double) size / items.length) <= 0.25) {
            resize(items.length / 2);
        }
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast = whereToPlace(nextLast, items.length);
        }
        size -= 1;
        // 如果array length >= 16 以及 usageRatio <= 0.25就resize down
        if (items.length >= arrayLengthToResizeDown && ((double) size / items.length) <= 0.25) {
            resize(items.length / 2);
        }
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        // nextFirst + 1 是list中第一個item的index
        int pos = (nextFirst + 1) + index;
        if (pos >= size) {
            pos = Math.floorMod(pos, items.length);
        }
        return items[pos];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
