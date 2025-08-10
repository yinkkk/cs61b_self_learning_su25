package IntList;


public class ListTest {
    public static void main(String[] args){
        List a = new List();
        List b = new List();
        List c = new List();
        a.List(15,b);
        b.List(10,c);
        c.List(5,null);

        List.printList(a);
        List d = List.incrList(a,2);
        List.printList(d);
        d = List.dincrList(a,10);
        List.printList(d);

//        System.out.println(a.ListSize());
//        System.out.println(a.get(1));
//        List d = new List();
//        d.copyList(a);
//        System.out.println(d.ListSize());
//        System.out.println(d.get(0));
//        d.addConstant(2);
//        System.out.println(d.get(0));



    }
}
