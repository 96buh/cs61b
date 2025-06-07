package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T> {
        private LinkedListNode wizPos;

        LinkedListIterator() {
            wizPos = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return wizPos != sentinel;
        }

        @Override
        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque61B otherLinkedList) {
            if (this.size != otherLinkedList.size) {
                return false;
            }
            LinkedListNode p = sentinel.next;
            LinkedListNode otherNode = otherLinkedList.sentinel.next;
            while (p != sentinel) {
                if (p.item != otherNode.item) {
                    return false;
                }
                p = p.next;
                otherNode = otherNode.next;
            }
            return true;
        }
        return false;
    }

    private class LinkedListNode {
        private final T item;
        private LinkedListNode prev;
        private LinkedListNode next;
        private LinkedListNode(T i, LinkedListNode p, LinkedListNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    // 宣告instance variable(sentinel)
    private final LinkedListNode sentinel;
    private int size;

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
