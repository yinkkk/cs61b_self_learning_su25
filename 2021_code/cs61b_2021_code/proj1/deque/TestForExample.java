package deque;

public class TestForExample {
    public static void main(String[] args){
        LinkedListDeque a = new LinkedListDeque();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.printDeque();
        System.out.println(a.getRecursive(1));
//        System.out.println(a.getLast());
//        a.addFirst("his");
//        System.out.println(a.getFirst());
//        a.addLast(100);
//        System.out.println(a.getFirst());
//        a.addLast(20);
//        System.out.println(a.getLast());
//        a.printDeque();
//        System.out.println(a.get(1));
    }
}
