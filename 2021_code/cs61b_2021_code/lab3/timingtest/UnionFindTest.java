package timingtest;

public class UnionFindTest {
    public static void main(String[] args){
        UnionFind a = new UnionFind(7);
        System.out.println(a);
        a.union(0, 1);
        a.union(1, 2);
        a.union(5, 0);
        a.union(4, 5);
        a.union(5, 6);
        System.out.println(a.sizeOf(0));
        System.out.println(a);
    }
}
