package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        size = 0;
        items= (T[]) new Object[8];
        nextFirst = 4 ;
        nextLast = 5;
    }

    public void resizeDeque(int capacity){
        T[] a =(T[]) new Object[capacity];
        System.arraycopy(items,0,a,size/2,size);
        nextFirst = (size/2)-1;
        nextLast = size*(3/2)+1;
        items = a;
    }

    public void addFirst(T item){
        if (size == items.length){
            resizeDeque(items.length*2);
        }
        if (nextFirst<0) nextFirst = items.length-1;
        items[nextFirst]=item;
        size++;
        nextFirst--;
    }

    public void addLast(T item){
        if (size == items.length){
            resizeDeque(items.length*2);
        }
        if (nextLast == items.length ) nextLast = 0;
        items[nextLast]=item;
        size++;
        nextLast++;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int index = 0;index < items.length;index++){
            System.out.print(items[index]+" ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(this.isEmpty()) return null;
        size--;
        if (nextFirst==size-1) nextFirst = -1;
        nextFirst++;
        T ans = items[nextFirst];
        items[nextFirst] = null;
        return ans;
    }

    public T removeLast(){
        if(this.isEmpty()) return null;
        size--;
        if (nextLast== 0 ) nextLast = items.length;
        nextLast--;
        T ans = items[nextLast];
        items[nextLast] = null;
        return ans;
    }

    public T get(int index){
        if ( index < 0 || index >= items.length || this.isEmpty()) return null;
        return items[index];
    }



}
