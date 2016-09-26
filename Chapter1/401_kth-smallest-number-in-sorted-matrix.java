class Node {
    int x,y,val;
    public Node(int x_, int y_, int val_) {
        this.x = x_;
        this.y = y_;
        this.val = val_;
    }
}

class NodeComparator implements Comparator<Node> {
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

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>(k, new NodeComparator());
        boolean[][] hash = new boolean[matrix.length][matrix[0].length];
        q.offer(new Node(0, 0, matrix[0][0]));
        hash[0][0] = true;

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};

        for (int i = 1; i < k; i++) {
            Node smallest = q.poll();
            for (int j = 0; j < 2; j++) {
                int nx = smallest.x + dx[j];
                int ny = smallest.y + dy[j];
                if (nx < matrix.length && ny < matrix[0].length && !hash[nx][ny]) {
                    q.offer(new Node(nx, ny, matrix[nx][ny]));
                    hash[nx][ny] = true;
                }
            }
        }

        return q.peek().val;
    }
}
