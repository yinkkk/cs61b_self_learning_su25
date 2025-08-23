package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;

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
        LinkedList<K> a = new LinkedList<>();
        KeytoList(root,a);
        for (K s : a ){
            System.out.print(s+" ,");
        }
    }

    private void print(BSTNode p) {
        if (p == null) return;
        System.out.print(" " + p.key + ":" + p.val + " ");
        print(p.left);
        print(p.right);
    }

    private void KeytoList(BSTNode p,LinkedList<K> KeyList){
        if (p == null) return;
        KeyList.add(p.key);
        KeytoList(p.left,KeyList);
        KeytoList(p.right,KeyList);
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
        Set<K> KeySet = new HashSet<>();
        for (K item: this){
            KeySet.add(item);
        }
        return KeySet;
    }
    private NodePair findKeyNodeAndPrev(K key){
        if (root.key == null){
            return null;
        }

        BSTNode p = root;
        BSTNode prev = null;
        while (p!=null){
            if (key.compareTo(p.key) < 0){
                prev = p;
                p=p.left;
            }
            else if (key.compareTo(p.key) > 0){
                prev = p;
                p=p.right;
            }
            else{
                return new NodePair(prev,p);
            }
        }
        return null;
    }
    private NodePair findLess(BSTNode prev,BSTNode p){
        if (p.right == null){
            return new NodePair(prev,p);
        }
        return findLess(p,p.right);
    }
    private class NodePair{
        private BSTNode prev;
        private BSTNode curr;

        public NodePair(BSTNode prev,BSTNode curr){
            this.prev = prev;
            this.curr = curr;
        }
    }
    @Override
    public V remove(K key) {
        NodePair a = findKeyNodeAndPrev(key);
        if (a==null) return null;
        V tmp = a.curr.val;
        size--;

        if (a.prev == null){
            if (a.curr.left == null && a.curr.right ==null){
                this.clear();
                return tmp;
            }
            if (a.curr.left == null){
                root = root.right;
                return tmp;
            }
            if (a.curr.right == null){
                root = root.left;
                return tmp;
            }

            NodePair newNode = findLess(a.curr,a.curr.left);
            if (newNode.prev == a.curr) {
                // 前驱就是 a.curr.left
                newNode.curr.right = a.curr.right;
                root = newNode.curr;
            } else {
                // 前驱在更深的位置
                newNode.prev.right = newNode.curr.left;
                newNode.curr.left = a.curr.left;
                newNode.curr.right = a.curr.right;
                root = newNode.curr;
            }
            return tmp;
        }
        if (a.curr.left == null && a.curr.right ==null){ // no leaves
            if (a.prev.left == a.curr){
                a.prev.left = null;
                return tmp;
            }
            else {
                a.prev.right = null;
                return tmp;
            }
        }

        if (a.curr.left == null ){  // one leave
            if (a.prev.left == a.curr){
                a.prev.left = a.curr.right;
                return tmp;
            }
            else {
                a.prev.right = a.curr.right;
                return tmp;
            }
        }
        if (a.curr.right == null){
            if (a.prev.left == a.curr){
                a.prev.left = a.curr.left;
                return tmp;
            }
            else {
                a.prev.right = a.curr.left;
                return tmp;
            }
        }

        NodePair newNode = findLess(a.curr,a.curr.left);
        newNode.prev.right = newNode.curr.left;
        newNode.curr.left = a.curr.left;
        newNode.curr.right = a.curr.right;
        if (a.prev.left == a.curr){
            a.prev.left = newNode.curr;
        }
        else {
            a.prev.right = newNode.curr;
        }
        return tmp;
    }
    @Override
    public V remove(K key, V value) {
        if (get(key)!= value) return null;
        remove(key);
        return value;
    }
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K>{
        private int wiz;
        private LinkedList<K> Items = new LinkedList<>();
        public BSTMapIterator(){
            wiz = 0;
            KeytoList(root,Items);
        }
        @Override
        public boolean hasNext() {
          return wiz < size;
        }
        @Override
        public K next() {
            if (hasNext()){
                wiz += 1;
                return Items.get(wiz-1);
            }
            return null;
        }
    }

}
