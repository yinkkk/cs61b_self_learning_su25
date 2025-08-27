package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

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
    private int size;
    private double loadFactor;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap(){
        buckets = createTable(100);
        size = 0;
        loadFactor = 0.8;
        for (int i =0 ;i< 100;i++){
           buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize){
        buckets = createTable(initialSize) ;
        size = 0 ;
        loadFactor = 0.8 ;
        for (int i =0 ;i< initialSize;i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad){
        buckets = createTable(initialSize);
        size = 0;
        loadFactor = maxLoad;
        for (int i =0 ;i< initialSize;i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
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
        return new HashSet<>();  //默认使用hashset做bucket
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] ans = (Collection<Node>[]) new Collection[tableSize];
        for (int i =0 ;i< tableSize;i++){
            ans[i] = createBucket();
        }
        return ans;
    }

    // TODO: Implement the methods of the Map61B Interface below

    @Override
    public void clear() {
        this.buckets = createTable(100);
        for (int i =0 ;i< 100;i++){
            buckets[i] = createBucket();
        }
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0){
            return false;
        }

        int index = Math.floorMod(key.hashCode(), buckets.length);
        for(Node a: buckets[index]){
            if (a.key.equals(key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for(Node a: buckets[index]){
            if (a.key.equals(key)){
                return a.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            remove(key);
            put(key,value);
            return;
        }
        if ((double) size / buckets.length >= loadFactor){
            resize();
        }

        int index = Math.floorMod(key.hashCode(), buckets.length);
        buckets[index].add(createNode(key,value));
        size++;
    }

    private void resize(){
        int len = 2 * buckets.length;
        Collection<Node>[] a = createTable(len);

        // 初始化
        for (int j = 0; j <len;j++){
            a[j] = createBucket();
        }
        // 复制原有的Node到新buckets中
        for (int i = 0; i < buckets.length;i++){
            for (Node n: buckets[i]){
                a[Math.floorMod(n.key.hashCode(),a.length)].add(n);
            }
        }
        buckets = a;
    }

    @Override
    public Set<K> keySet() {
        Set<K> ans = new HashSet<>();
        for (K k : this){
            ans.add(k);
        }
        return ans;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)){
            return null;
        }

        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node n : buckets[index]){
            if (n.key== key){
                V tmp = n.value;
                buckets[index].remove(n);
                size--;
                return tmp;
            }
        }

        return null;
    }

    @Override
    public V remove(K key, V value) {
        remove(key);
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int bucketIndex;
        private Iterator<Node> bucketIterator;

        public HashMapIterator() {
            bucketIndex = 0;
            bucketIterator = advanceToNextBucket();
        }

        private Iterator<Node> advanceToNextBucket() {
            while (bucketIndex < buckets.length) {
                if (!buckets[bucketIndex].isEmpty()) {
                    return buckets[bucketIndex].iterator();
                }
                bucketIndex++;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return bucketIterator != null && (bucketIterator.hasNext() || bucketIndex < buckets.length - 1);
        }

        @Override
        public K next() {
            if (bucketIterator == null) throw new NoSuchElementException();
            if (!bucketIterator.hasNext()) {
                bucketIndex++;
                bucketIterator = advanceToNextBucket();
                if (bucketIterator == null) throw new NoSuchElementException();
            }
            return bucketIterator.next().key;
        }
    }


}
