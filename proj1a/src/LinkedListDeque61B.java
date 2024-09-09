import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel = new Node(null, null, null);
    private Node last = sentinel;
    private int size = 0;

    public class Node {
        public Node prev;
        public Node next;
        public T item;

        public Node(T x, Node n, Node p) {
            item = x;
            next = n;
            prev = p;
        }
    }

    @Override
    public void addFirst(T x) {
        size += 1;
        sentinel.next = new Node(x, sentinel.next, sentinel);
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
        if (sentinel.next == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public LinkedListDeque61B() {

    }
}
