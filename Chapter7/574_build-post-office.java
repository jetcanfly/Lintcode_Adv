// O(mnk) k: number of houses
public class Solution {
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        if (grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        List<int[]> houses = findLocations(grid);

        if (houses.size() == 0) {
            return -1;
        }

        return findBest(grid, houses);
    }

    private List<int[]> findLocations(int[][] grid) {
        List<int[]> houses = new ArrayList<int[]>();

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }

        return houses;
    }

    private int findBest(int[][] grid, List<int[]> houses) {

        int best = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int d = 0;
                    for (int[] h : houses) {
                        d += Math.abs(i - h[0]) + Math.abs(j - h[1]);
                    }
                    best = Math.min(best, d);
                }
            }
        }

        return best;
    }
}
