package IntList;

import java.util.Arrays;

public class SLListTest {
    public static void main(String[] args){
        SLList a = new SLList(20);
        SLList b =new SLList();
//        a.addFirst(10);
//        a.addLast(30);
//        a.addLast(40);
//        a.deleteFirst();
//        a.printList();
//        a.returnSize();
        int[] test = new int[]{1,4,2};
        b.ArrayToList(test);
//        b.addTwo();
        b.printList();
        b.addAndSquare(3);
        b.printList();
//        System.out.println(b.isNeiborEqual());
//        int[] c = b.getArray();
//        System.out.println(Arrays.toString(c));
    }
}

