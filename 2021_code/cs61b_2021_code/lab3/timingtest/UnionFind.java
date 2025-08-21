package timingtest;

public class UnionFind {
    private int[] Items;


    public UnionFind(int n){
        Items = new int[n];
        for (int i = 0 ; i<n;i++){
            Items[i] = -1;
        }
    }
    @Override
    public String toString(){
        StringBuilder a = new StringBuilder("[ ");
        for (int i = 0;i<Items.length-1;i++){
            a.append(Items[i]+" , ");
        }
        a.append(Items[Items.length-1] +" ]");
        return a.toString();
    }

    public int sizeOf(int v1){
        return -Items[FindRoot(v1)];
    }

    public int parent(int v1){
        return Items[v1];
    }

    public void validate(int v1){
        if (v1 >= Items.length){
             throw new IllegalArgumentException("### ERROR ### This is not a valid index.");
        }
    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = FindRoot(v1);
        int root2 = FindRoot(v2);

        if (root1 == root2) return;

        if (-Items[root1] < -Items[root2]) { // root2 的树更大
            Items[root2] += Items[root1];    // 更新 root2 的 size
            Items[root1] = root2;            // root1 挂到 root2
        } else {
            Items[root1] += Items[root2];    // 更新 root1 的 size
            Items[root2] = root1;            // root2 挂到 root1
        }
    }

    private int FindRoot(int v){
        if (Items[v] < 0){
            return v;
        }
        return FindRoot(Items[v]);
    }

    public boolean connected(int v1, int v2){
        return FindRoot(v1)==FindRoot(v2);
    }



}
