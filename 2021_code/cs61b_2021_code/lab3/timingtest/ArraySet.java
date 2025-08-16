package timingtest;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T[] values;

    public ArraySet(){
        size = 0;
        values = (T[]) new Object[100];
    }

    public ArraySet(T val){
        size = 1;
        values = (T[]) new Object[100];
        values[0] = val;
    }

    public void add(T value){
        if (value==null){
            throw new IllegalArgumentException("你不能把null加到ArraySet里");
        }
        if (this.contains(value)) return;
        values[size] = value;
        size++;
    }

    public int size(){
        return size;
    }

    public boolean contains(T value){
        for(int i = 0 ; i < size ; i++){
            if (values[i].equals(value)) return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder("{");
        for (int i = 0 ; i <size-1;i++){
            returnString.append(values[i]+",");
        }
        returnString.append(values[size-1]);
        returnString.append("}");
        return returnString.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        ArraySet<T> other = (ArraySet<T>) obj;
        if (other.size != this.size) return false;
        for (T item : this) {
            if (! other.contains(item)) return false ;
        }
        return true;
    }

    public static <type> ArraySet<type> of(type ...stuff){
        ArraySet<type> a = new ArraySet<>();
        for (type x: stuff){
            a.add(x);
        }
        return a;
    }

    private class ArraySetIterator implements Iterator<T>{
        private int wizpos;

        public ArraySetIterator(){
            wizpos = 0;
        }
        @Override
        public T next() {
            T returnItem = values[wizpos];
            wizpos++;
            return returnItem;
        }

        @Override
        public boolean hasNext() {
            return wizpos < size;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }


}
