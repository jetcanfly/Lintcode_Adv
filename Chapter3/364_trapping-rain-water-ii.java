class Node {
    int x,y,h;
    Node(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.h - b.h;
    }
}

public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights[0] == null || heights[0].length == 0) {
            return 0;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>(1, new NodeComparator());
        // Put four edges to queue
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            q.offer(new Node(i, 0, heights[i][0]));
            q.offer(new Node(i, n - 1, heights[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 1; j < n; j++) {
            q.offer(new Node(0, j, heights[0][j]));
            q.offer(new Node(m - 1, j, heights[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        //
        int ans = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!q.isEmpty()) {
            Node head = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = head.x + dx[i];
                int y = head.y + dy[i];
                if (0 <= x && x < m && 0 <= y && y < n && !visited[x][y]) {
                    q.offer(new Node(x, y, Math.max(head.h, heights[x][y])));
                    ans += Math.max(0, head.h - heights[x][y]);
                    visited[x][y] = true;
                }
            }
        }
        return ans;
    }
};
