package IntList;

public class List {
    public int value;

    public List next;

    public void List(int val,List p){
        this.value = val;
        this.next = p;
    }

    public int ListSize(){
        if (this.next == null){
            return 1;
        }
        return this.next.ListSize()+1;
    }

    public int iterativeSize(){
        List p = this;
        int len = 0;

        while (p!=null){
            len++;
            p=p.next;
        }
        return len;
    }

    public int get(int index){
        if (index == 0) return this.value;
        return this.next.get(index -1);
    }

    public static List incrList(List l,int x){
        List ans = new List();
        l.addConstant(x);
        ans.copyList(l);
        return ans;
    }

    public static List dincrList(List l ,int x){
        l.addConstant(-x);
        return l;
    }

    public void addConstant(int c){
        this.value +=c;
        if (this.next == null ) return;
        this.next.addConstant(c);
    }

    public void copyList(List origin){
        List p = this;
        int size = origin.ListSize();
        while (size >1){
            p.value = origin.value;
            List tmp = new List();
            p.next = tmp;
            p=p.next;
            origin = origin.next;
            size--;
        }
        p.value  = origin.value;
    }

    public static void printList(List l){
        if (l.next == null) {
            System.out.println(l.value);
            return;
        }
        System.out.print(l.value+"->");
        printList(l.next);
    }


}
