import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        private K key;
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
    private Set<K> keySet;
    private V removedNodeValue = null;


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
        if (!containsKey(key)) {
            return null;
        }
        BSTNode getNode = get(root, key);
        return getNode.value;
    }

    /**
     * 得到特定key的節點
     */
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
        if (getNode == null) {
            return false;
        }
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
        keySet = new TreeSet<>();
        inOrder(root);
        return keySet;
    }

    /**
     * BST使用inOrder可以從小到大遍歷
     */
    private void inOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        keySet.add(node.key);
        inOrder(node.right);
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
        if (!containsKey(key)) {
            return null;
        }
        root = remove(root, key);
        size -= 1;
        return removedNodeValue;
    }

    private BSTNode remove(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else {
            if (node.left == null) {
                removedNodeValue = node.value;
                return node.right;
            }
            if (node.right == null) {
                removedNodeValue = node.value;
                return node.left;
            }
            BSTNode maxNode = findMaxInLeftTree(node.left);
            removedNodeValue = node.value;
            node.key = maxNode.key;
            node.value = maxNode.value;
            node.left = remove(node.left, maxNode.key);
        }
        return node;
    }

    private BSTNode findMaxInLeftTree(BSTNode node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     * return an iterator over the keys, in sorted order
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private int wizPos;
        private int counter;
        private final K[] keyArray;

        BSTIterator() {
            wizPos = 0;
            counter = 0;
            keyArray = (K[]) new Comparable[size];
            nextHelper(root);
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }
        @Override
        public K next() {
            K returnKey = keyArray[wizPos];
            wizPos += 1;
            return returnKey;
        }
        private void nextHelper(BSTNode node) {
            if (node == null) {
                return;
            }
            nextHelper(node.left);
            keyArray[counter] = node.key;
            counter += 1;
            nextHelper(node.right);
        }
    }
}
