package deque;

public class TestForArrayDeque {
    public static void main(String[] args){
        ArrayDeque a =new ArrayDeque<>();
        a.addLast("a");
        a.addLast("b");
        a.addLast("c");
        a.addLast("d");
        a.addLast("e");
        a.addLast("f");
        a.printDeque();
        a.removeLast();
        a.printDeque();
    }
}
