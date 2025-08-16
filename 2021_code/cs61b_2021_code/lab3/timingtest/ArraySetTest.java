package timingtest;

import java.util.Iterator;

public class ArraySetTest {
    public static void main(String[] args){
        ArraySet<Integer> a = new ArraySet<>();
        a.add(1);
        a.add(2);
        a.add(4);
        a.add(3);
        ArraySet<String> b =ArraySet.of("hi","I'm","here");
        System.out.println(b);

        Iterator<Integer> aIter = a.iterator();

        System.out.println(a.contains(1));
        System.out.println(a.size());
        System.out.println(a);

//        while (aIter.hasNext()){
//            System.out.println(aIter.next());
//        }
//
//        for(int i : a){
//            System.out.println(i);
//        }
    }
}
