public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        if (board[0] == null || board[0].length == 0) {
            return false;
        }

        if (word == null) { return false; }
        if (word.length() == 0) { return true; }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean ans = dfs(board, word, i, j, 0);
                    if (ans) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    private boolean dfs(char[][] board, String word, int i, int j, int start) {
        if (start == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(start)) {
            return false;
        }

        board[i][j] = 'X';  //  same letter cell may not be used more than once.
        boolean left = dfs(board, word, i - 1, j, start + 1);
        boolean right = dfs(board, word, i + 1, j, start + 1);
        boolean up = dfs(board, word, i, j - 1, start + 1);
        boolean down = dfs(board, word, i, j + 1, start + 1);
        board[i][j] = word.charAt(start);

        return left || right || up || down;
    }
}
