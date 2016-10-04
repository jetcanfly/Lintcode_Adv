public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0) {
            return ;
        }

        if (board[0] == null || board[0].length == 0) {
            return ;
        }

        Queue<Node> q = new LinkedList<Node>();

        // 将4周的点push到queue里
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            enqueue(board, q, i, 0);
            enqueue(board, q, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            enqueue(board, q, 0, j);
            enqueue(board, q, m - 1, j);
        }

        while (!q.isEmpty()) {
            Node head = q.poll();
            int x = head.x;
            int y = head.y;

            /** Temp flag, 'O' starts from boarder shoundn't change.
              * ["XOX", "XOX", "XXX"] --> ["XOX","XOX","XXX"]
              */
            if (board[x][y] == 'O') {
                board[x][y] = 'D';
            }

            // Neighbors
            enqueue(board, q, x - 1, y);
            enqueue(board, q, x + 1, y);
            enqueue(board, q, x, y - 1);
            enqueue(board, q, x, y + 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'D') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void enqueue(char[][] board, Queue<Node> q, int x, int y) {
        if (0 <= x && x < board.length && 0 <= y && y < board[0].length && board[x][y] == 'O') {
            q.offer(new Node(x, y));
        }
    }

    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
