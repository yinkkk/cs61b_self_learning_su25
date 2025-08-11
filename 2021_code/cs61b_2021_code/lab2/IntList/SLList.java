package IntList;

public class SLList {
        public class ListNode{
            public int val;
            public ListNode next;
            public ListNode(int v , ListNode l){
                this.val = v;
                this.next = l ;
            }
        }

        private ListNode sentinel;  // 哨兵节点，避免一些空节点的可能。
        private int size;

        public SLList(){
            sentinel = new ListNode(24,null);
            size = 0;
        }

        public void returnSize(){
            System.out.println(this.size);
        }

        public SLList(int x){
            ListNode first = new ListNode(x,null);
            sentinel = new ListNode(24,first);
            size = 1;
        }

        public void addFirst(int x){
            sentinel.next = new ListNode(x,this.sentinel.next);
            size++;
        }

        public int getFirst() {
            return sentinel.next.val;
        }

        public void addLast(int x){
            size++;

            ListNode p = sentinel;
            while (p.next !=null) {
                p = p.next;
            }
            p.next = new ListNode(x,null);
        }

        public void deleteFirst(){
            size--;
            sentinel.next = sentinel.next.next;
        }

        public boolean isNeiborEqual(){
            ListNode p = sentinel.next;
            while (p.next!= null){
                if (p.val == p.next.val) return true;
                p = p.next;
            }
            return false;
        }

        public void addTwoSame(){
           ListNode p =sentinel.next;
           while ( p!= null && p.next!= null){
               if (p.val == p.next.val){
                   p.val *= 2;
                   p.next=p.next.next;
               }
               p=p.next;
           }
        }

        public void addTwo(){
            while (this.isNeiborEqual()) this.addTwoSame();
        }

        public void addAndSquare(int x){
            ListNode p =sentinel.next;
            while (p.next!=null){
                p.next = new ListNode(p.val*p.val,p.next);
                p=p.next.next;
            }
            p.next = new ListNode(p.val*p.val,p.next);
            p.next.next = new ListNode(x,null);
        }

        public void ArrayToList(int[] array){
            size = array.length;
            ListNode p = sentinel;
            int i = size;
            while (i>0){
                p.next = new ListNode(array[size - i],null);
                p=p.next;
                i--;
            }

        }

        public int[] getArray(){
            int[] ans = new int[this.size];
            ListNode p = sentinel.next;
            int i = 0;
            while (p!=null){
                ans[i] = p.val;
                i++;
                p=p.next;
            }
            return ans;
        }

//        public static int[] SumTwoSame(int[] a){
//            int size = a.length;
//            int index = 0;
//            while (index + 1 < size){
//                if (a[index] == a[index+1]){
//
//                }
//            }
//
//        }


        public void printList(){
//            if (sentinel.next == null) {
//                System.out.print("该链表为空");
//                return;
//            }
            ListNode p = sentinel.next;
            while (p!= null){
                System.out.print(p.val+"->");
                p=p.next;
            }
            System.out.println("End");
        }




}
