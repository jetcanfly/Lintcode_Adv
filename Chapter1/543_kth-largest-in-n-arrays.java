class Node {
    int x, y, val;
    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return -(a.val - b.val);
    }
}

public class Solution {
    /**
     * @param arrays a list of array
     * @param k an integer
     * @return an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        if (arrays == null || arrays.length == 0) {
            return -1;
        }

        // Sort array
        for (int i = 0; i < arrays.length; i++) {
            Arrays.sort(arrays[i]);
        }

        // Add the largest to the queue
        PriorityQueue<Node> q = new PriorityQueue<Node>(k, new NodeComparator());
        for (int i = 0; i < arrays.length; i++) {
            int j = arrays[i].length - 1;
            if (j >= 0) { //case [[],[],[1],[1,2,3,4],[11,10,9,8,7]], 5
                q.offer(new Node(i, j, arrays[i][j]));
            }
        }

        // Loop
        for (int i = 1; i < k; i++) {
            Node biggest = q.poll();
            int nx = biggest.x;
            int ny = biggest.y - 1;
            if (ny >= 0) {
                q.offer(new Node(nx, ny, arrays[nx][ny]));
            }
        }

        return q.peek().val;
    }
}
