package bstmap;

public class BSTMapTest {
    public static void main(String[] args){
        BSTMap<Integer,Integer> a = new BSTMap<>();
        a.put(100,2);
        a.put(10,1);
        a.clear();
        System.out.println(a.containsKey(10));
        System.out.println(a.containsKey(5));
        a.put(20,(int) Math.log10(20));
        a.put(1000,3);
        a.put(500,(int) Math.log10(500));
        a.put(1500,3);
//        a.printInOrder();
        System.out.println(a.get(100));
        for (Object aa : a){
            System.out.print(aa+" ");
        }
    }
}
