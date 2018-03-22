public class Solution {
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] left = getLeft(grid);
        int[][] right = getRight(grid);
        int[][] up = getUp(grid);
        int[][] down = getDown(grid);


        int best = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    int curr = left[i][j] + right[i][j] + up[i][j] + down[i][j];
                    best = Math.max(best, curr);
                }
            }
        }

        return best;
    }

    /*
     * How many enemies can kill on the left if put a bomb at (i, j)
     * left[i][j] = left[i - 1][j] + 1 (if grid[i][j] == 'E')
     *            = left[i - 1][j]     (if grid[i][j] == '0')
     *            = 0                  (if grid[i][j] == 'W')
     */
    private int[][] getLeft(char[][] grid) {
        int[][] left = new int[grid.length][grid[0].length];

        // Initialization
        for (int i = 0; i < grid.length; i++) {
            left[i][0] = grid[i][0] == 'E' ? 1 : 0;
        }

        // Function
        for (int i = 0; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                left[i][j] = left[i][j - 1];
                if (grid[i][j] == 'W') {
                    left[i][j] = 0;
                }
                if (grid[i][j] == 'E') {
                    left[i][j] += 1;
                }
            }
        }
        return left;
    }


    private int[][] getRight(char[][] grid) {
        int[][] right = new int[grid.length][grid[0].length];

        // Initialization
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            right[i][n - 1] = grid[i][n - 1] == 'E' ? 1 : 0;
        }

        // Function
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid[i].length - 2; j >= 0; j--) {
                right[i][j] = right[i][j + 1];
                if (grid[i][j] == 'W') {
                    right[i][j] = 0;
                }
                if (grid[i][j] == 'E') {
                    right[i][j] += 1;
                }
            }
        }
        return right;
    }


    private int[][] getUp(char[][] grid) {
        int[][] up = new int[grid.length][grid[0].length];

        // Initialization
        for (int j = 0; j < grid[0].length; j++) {
            up[0][j] = grid[0][j] == 'E' ? 1 : 0;
        }

        // Function
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                up[i][j] = up[i - 1][j];
                if (grid[i][j] == 'W') {
                    up[i][j] = 0;
                }
                if (grid[i][j] == 'E') {
                    up[i][j] += 1;
                }
            }
        }
        return up;
    }


    private int[][] getDown(char[][] grid) {
        int[][] down = new int[grid.length][grid[0].length];

        // Initialization
        int m = grid.length;
        for (int j = 0; j < grid[0].length; j++) {
            down[m - 1][j] = grid[m - 1][j] == 'E' ? 1 : 0;
        }

        for (int i = grid.length - 2; i >= 0; i--) {
            for (int j = 0; j < grid[i].length; j++) {
                down[i][j] = down[i + 1][j];
                if (grid[i][j] == 'W') {
                    down[i][j] = 0;
                }
                if (grid[i][j] == 'E') {
                    down[i][j] += 1;
                }
            }
        }
        return down;
    }
}
