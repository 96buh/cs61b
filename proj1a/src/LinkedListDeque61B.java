import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    public class LinkedListNode {
        public T item;
        public LinkedListNode prev;
        public LinkedListNode next;
        public LinkedListNode(T i, LinkedListNode p, LinkedListNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    // 宣告instance variable(sentinel)
    public LinkedListNode sentinel;
    public int size;

    public LinkedListDeque61B() {
        sentinel = new LinkedListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        size += 1;
        LinkedListNode newNode = new LinkedListNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        LinkedListNode newNode = new LinkedListNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return returnList;
        }
        LinkedListNode tempNode = sentinel.next;
        while (tempNode != sentinel) {
            returnList.add(tempNode.item);
            tempNode = tempNode.next;
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
        size -= 1;
        LinkedListNode deletedNode = sentinel.next;
        sentinel.next = deletedNode.next;
        deletedNode.next.prev = sentinel;

        return deletedNode.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        LinkedListNode deletedNode = sentinel.prev;
        deletedNode.prev.next = sentinel;
        sentinel.prev = deletedNode.prev;
        return deletedNode.item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        LinkedListNode p = sentinel.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int counter = 0;
        LinkedListNode p = sentinel.next;
        return getRecursive(counter, index, p);
    }

    // 遞迴helper method
    private T getRecursive(int counter, int index, LinkedListNode p) {
        if (counter < index) {
            return getRecursive(counter + 1, index, p.next);
        }
        return p.item;
    }
}
