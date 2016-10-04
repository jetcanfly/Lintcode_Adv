class UnionFind {
    HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

    UnionFind(int n) {
        for (int i = 0; i < n; i++) {
            father.put(i, i);
        }
    }

    int find(int x) {
        int parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }

        while (x != father.get(x)) {
            int next = father.get(x);
            father.put(x, parent);
            x = next;
        }
        return parent;
    }

    void union(int x, int y) {
        int fax = find(x);
        int fay = find(y);
        if (fax != fay) {
            father.put(fax, fay);
        }
    }
}

public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // tree should have n nodes with n-1 edges
        if (n - 1 != edges.length) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }

        return true;
    }
}
