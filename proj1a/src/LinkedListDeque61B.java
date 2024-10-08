import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel = new Node(null, null, null);
    private Node last = sentinel;
    private int size = 0;

    public class Node {
        private Node prev;
        private Node next;
        private T item;

        public Node(T x, Node n, Node p) {
            item = x;
            next = n;
            prev = p;
        }
    }

    @Override
    public void addFirst(T x) {
        size += 1;
        Node p = new Node(x, sentinel.next, sentinel);
        if (isEmpty()) {
            last = p;
        } else {
            sentinel.next.prev = p;
        }
        sentinel.next = p;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node b = new Node(x, sentinel, last);
        if (isEmpty()) {
            sentinel.next = b;
        }
        last.next = b;
        last = b;
        sentinel.prev = last;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        Node p = sentinel.next;
        while (p != sentinel && p != null) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Node d = sentinel.next;
        // 如果是最後一個節點
        if (d.next == sentinel) {
            last = sentinel;
        }
        sentinel.next = d.next;
        sentinel.next.prev = sentinel;
        return d.item;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Node d = last;
        if (d.prev == sentinel) {
            last = sentinel;
        } else {
            last = d.prev;
        }
        last.next = sentinel;

        return d.item;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        int counter = 0;

        if (index > size || index < 0) {
            return null;
        }
        while (counter != index) {
            p = p.next;
            counter += 1;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        if (p == null) {
            return null;
        }
        return getRecursiveHelper(p.next, index - 1);
    }


    public LinkedListDeque61B() {
    }
}
