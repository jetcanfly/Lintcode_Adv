class Node {
    int x,y,val;
    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        return a.val - b.val;
    }
}

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */

    /* Given an n x n matrix, where every row and column is sorted in non-decreasing order. */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(k, new NodeComparator());
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        pq.offer(new Node(0, 0, matrix[0][0]));
        visited[0][0] = true;

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};

        for (int i = 1; i < k; i++) {
            Node root = pq.poll();
            for (int j = 0; j < 2; j++) {
                int nx = root.x + dx[j];
                int ny = root.y + dy[j];
                if (nx < matrix.length && ny < matrix[0].length && !visited[nx][ny]) {
                    pq.offer(new Node(nx, ny, matrix[nx][ny]));
                    visited[nx][ny] = true;
                }
            }
        }

        return pq.peek().val;
    }
}
