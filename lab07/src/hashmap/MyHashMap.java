package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** load factor = N / M
     * N: map裡面存了多少東西(Node)
     * M: buckets數量 == buckets.length
     */
    private int initialCapacity;
    private int size;
    private int numberOfNodes; // N
    private double loadFactor;


    /** Constructors */
    public MyHashMap() {
        this.initialCapacity = 16;
        this.loadFactor = 0.75;
        this.numberOfNodes = 0;
        this.size = 0;
        putBuckets();
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = 0.75;
        this.numberOfNodes = 0;
        this.size = 0;
        putBuckets();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.numberOfNodes = 0;
        this.size = 0;
        putBuckets();
    }

    private void putBuckets() {
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        Node item = new Node(key, value);
        int whichBucketToPut = whichBucket(key);

        if (containsKey(key)) {
            for (Node n : buckets[whichBucketToPut]) {
                if (n.key.equals(key)) {
                    n.value = value;
                }
            }
        } else {
            buckets[whichBucketToPut].add(item);
            size += 1;
            numberOfNodes += 1;
            if (((double) numberOfNodes / buckets.length) >= loadFactor) {
                resize();
            }
        }
    }

    private void resize() {
        // 創建一個新的buckets
        Collection<Node>[] oldBuckets = buckets;
        initialCapacity *= 2;
        clear();
        for (int i = 0; i < oldBuckets.length; i++) {
            for (Node n : oldBuckets[i]) {
                put(n.key, n.value);
            }
        }
    }

    private int whichBucket(K key) {
        int keyHash = key.hashCode();
        return Math.floorMod(keyHash, buckets.length);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int whichBucket = whichBucket(key);
        for (Node n : buckets[whichBucket]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int whichBucket = whichBucket(key);
        for (Node n : buckets[whichBucket]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        numberOfNodes = 0;
        putBuckets();
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int whichBucket = whichBucket(key);
        V returnValue = null;
        for (Node n : buckets[whichBucket]) {
            if (n.key.equals(key)) {
                returnValue = n.value;
                buckets[whichBucket].remove(n);
            }
        }
        return returnValue;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
