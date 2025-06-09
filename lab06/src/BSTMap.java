import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        private final K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        private BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private BSTNode root;
    private int size = 0;


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
        if (root == null) {
            size += 1;
            root = new BSTNode(key, value, null, null);
        }
        put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, value, null, null);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) == 0) {
            node.value = value;
        }
        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        BSTNode getNode = get(root, key);
        return getNode.value;
    }

    private BSTNode get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node = get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node = get(node.left, key);
        }
        return node;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        BSTNode getNode = get(root, key);
        return getNode.key != null;
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
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
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
