class Node implements Comparable<Node>{
    int x, y, val;
    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Node another) {
        return this.val - another.val;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Node)) {
            return false;
        }
        Node another = (Node) obj;
        return another.x == this.x && another.y == this.y;
    }
    @Override
    public int hashCode() {
        return this.x * 33 + this.y;
    }
}

public class Solution {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        // Write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        if (B == null || B.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Node> q = new PriorityQueue<Node>();
        HashSet<Node> isVisited = new HashSet<Node>();
        q.offer(new Node(0, 0, A[0] + B[0]));
        isVisited.add(new Node(0, 0, A[0] + B[0]));

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        for (int i = 1; i < k; i++) {
            Node smallest = q.poll();
            for (int j = 0; j < 2; j++) {
                int nx = smallest.x + dx[j];
                int ny = smallest.y + dy[j];
                Node pair = new Node(nx, ny, 0);
                if (nx < A.length && ny < B.length && !isVisited.contains(pair)) {
                    pair.val = A[nx] + B[ny];
                    q.offer(pair);
                    isVisited.add(pair);
                }
            }
        }

        return q.peek().val;

    }
}
