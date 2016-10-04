/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

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
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> ans = new ArrayList<Integer>();
        if (m < 0 || n < 0 || operators == null || operators.length == 0) {
            return ans;
        }

        UnionFind uf = new UnionFind(m*n);
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        boolean[][] isIsland = new boolean[n][m];

        int count = 0;
        for (Point op : operators) {
            count++;
            int i = op.x;
            int j = op.y;
            if (!isIsland[i][j]) {
                isIsland[i][j] = true;
                int idx = ind2sub(n, m, i, j);
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < n && y >= 0 && y < m && isIsland[x][y]) {
                        int now = ind2sub(n, m, x, y);
                        int fax = uf.find(idx);
                        int fay = uf.find(now);
                        if (fax != fay) {
                            uf.union(idx, now);
                            count--;
                        }
                    }
                }
            }
            ans.add(count);
        }

        return ans;
    }

    private int ind2sub(int n, int m, int x, int y) {
        return x * m + y;
    }
}
