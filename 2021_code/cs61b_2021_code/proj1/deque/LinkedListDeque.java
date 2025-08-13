package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class ListNode{
        public T item;
        public ListNode next;
        public ListNode prev;

        public ListNode(T item,ListNode l1,ListNode l2){
            this.item = item;
            this.next = l1;
            this.prev = l2;
        }
    }

    private ListNode sentFront;
    private ListNode sentBack;
    private int size;

    public LinkedListDeque(){
        sentFront = new ListNode(null,null,null);
        sentBack = new ListNode(null,null,sentFront);
        sentFront.next = sentBack;
        size = 0;
    }

    public void addFirst(T item){
        size++;
        ListNode newNode = new ListNode(item , sentFront.next , sentFront);
        sentFront.next.prev = newNode;
        sentFront.next = newNode;
    }

    public T getFirst(){
        return sentFront.next.item;
    }
    public T getLast(){
        return sentBack.prev.item;
    }

    public void addLast(T item){
        size++;
        ListNode newNode = new ListNode(item,sentBack,sentBack.prev);
        sentBack.prev.next = newNode;
        sentBack.prev = newNode;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ListNode p = sentFront.next;
        if (p==sentBack){
            System.out.println("该Deque为空");
            return;
        }
        while (p!= sentBack){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(this.isEmpty()) return null;
        size--;
        T ans = this.getFirst();
        sentFront.next.next.prev = sentFront;
        sentFront.next = sentFront.next.next;
        return ans;
    }

    public T removeLast(){
        if(this.isEmpty()) return null;
        size--;
        T ans = this.getLast();
        sentBack.prev.prev.next = sentBack;
        sentBack.prev = sentBack.prev.prev;
        return ans;
    }

    public T get(int index){
        ListNode p = sentFront;
        while(p!=null){
            if (index == 0) return p.item;
            index--;
            p=p.next;
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecursive(sentFront, index);
    }

    public T getRecursive(ListNode loc,int index){
        if (index == 0) return loc.item;
        return this.getRecursive(loc.next,index -1);
    }


}
