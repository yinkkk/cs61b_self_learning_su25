package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    private class BSTNode{
        public K key;
        public V val;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(){
            key = null;
            val = null;
            left = null;
            right = null;
        }
        public BSTNode(K k ,V v){
            key = k;
            val = v;
            left = null;
            right = null;
        }
        public BSTNode(K k ,V v,BSTNode l,BSTNode r){
            key = k;
            val = v;
            left = l;
            right = r;
        }

    }

    private BSTNode root;
    private int size;
    public BSTMap(){
        root = new BSTNode();
        size = 0;
    }
    @Override
    public void clear() {
        this.root = new BSTNode();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (root.key == null){
            return false;
        }

        BSTNode p = root;
        while (p!=null){
            if (key.compareTo(p.key) < 0){
                p = p.left;
            }
            else if (key.compareTo(p.key) > 0){
                p=p.right;
            }
            else{
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (root.key == null){
            return null;
        }

        BSTNode p = root;
        while (p!=null){
            if (key.compareTo(p.key) < 0){
                p = p.left;
            }
            else if (key.compareTo(p.key) > 0){
                p=p.right;
            }
            else{
                return p.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public void printInOrder(){
        print(root);
        System.out.println();
    }

    private void print(BSTNode p) {
        if (p == null) return;
        System.out.print(" " + p.key + ":" + p.val + " ");
        print(p.left);
        print(p.right);
    }
    @Override
    public void put(K key, V value) {
        if (root.key == null){
            root.key = key;
            root.val = value;
            size++;
            return;
        }

        BSTNode p = root;
        BSTNode prev = null;
        while (p!=null){
            if (key.compareTo(p.key) < 0){
                prev = p;
                p = p.left;
            }
            else if (key.compareTo(p.key) > 0){
                prev = p;
                p=p.right;
            }
            else{
                System.out.println("{ "+key + " } already exists.");
                return;
            }
        }

        if (key.compareTo(prev.key)< 0){
            prev.left = new BSTNode(key,value);
        }
        else{
            prev.right = new BSTNode(key,value);
        }
        size++;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unfinished");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unfinished");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unfinished");
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Unfinished");
    }
}
