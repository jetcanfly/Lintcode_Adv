/* 逆向BFS O(kmn) */
import java.util.*;

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

        int m = grid.length;
        int n = grid[0].length;

        List<Node> house = new ArrayList<Node>();
        List<Node> space = new ArrayList<Node>();

        split(grid, house, space);

        if (house.size() == 0 || space.size() == 0) {
            return -1;
        }

        // Distance matrix from house to empty space
        int[][][] distance = new int[m][n][house.size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < house.size(); k++) {
                    distance[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        // BFS get distance from house to empty space [O(mnk)]
        for (int k = 0; k < house.size(); k++) {
            Node h = house.get(k);
            bfs(grid, distance, h, k);
        }

        // Scan through each empty space, get the distance O(mnk)
        int best = search(space, distance, house.size());
        return best == Integer.MAX_VALUE ? -1 : best;
    }


    private void split(int[][] grid, List<Node> house, List<Node> space) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    house.add(new Node(i, j, 0));
                }
                if (grid[i][j] == 0) {
                    space.add(new Node(i, j, 0));
                }
            }
        }
    }


    private int search(List<Node> space, int[][][] distance, int totalK) {
        // Scan through each empty space, get the distance O(mnk)
        int best = Integer.MAX_VALUE;
        for (Node s : space) {
            int nx = s.x;
            int ny = s.y;

            int sum = 0;
            for (int k = 0; k < totalK; k++) {
                int d = distance[nx][ny][k];
                if (d == Integer.MAX_VALUE) {
                    sum = Integer.MAX_VALUE;
                    break;
                }
                sum += d;
            }
            best = Math.min(best, sum);
        }
        return best;
    }


    private class Node {
        int x, y;
        int dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }


    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {-1, 1, 0, 0};

    private void bfs(int[][] grid, int[][][] d, Node curr, int k) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new LinkedList<Node>();
        q.offer(curr);

        while (!q.isEmpty()) {
            Node head = q.poll();
            visited[head.x][head.y] = true;
            for (int t = 0; t < 4; t++) {
                int nx = head.x + dx[t];
                int ny = head.y + dy[t];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] != 2) {
                    visited[nx][ny] = true;
                    d[nx][ny][k] = head.dist + 1;
                    if (grid[nx][ny] != 1) {
                        q.offer(new Node(nx, ny, d[nx][ny][k]));
                    }
                }
            }
        }
    }
}


/* 正向BFS(BFS from empty space) O(nm * mn) */
public class Solution {
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    private class Node {
        int x, y;
        int dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }


    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        if (grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        List<Node> house = new ArrayList<Node>();
        List<Node> space = new ArrayList<Node>();

        split(grid, house, space);

        if (house.size() == 0 || space.size() == 0) {
            return -1;
        }

        int best = Integer.MAX_VALUE;
        for (Node s : space) {
            int distance = search(grid, house, s);
            best = Math.min(best, distance);
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }


    private void split(int[][] grid, List<Node> house, List<Node> space) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    house.add(new Node(i, j, 0));
                }
                if (grid[i][j] == 0) {
                    space.add(new Node(i, j, 0));
                }
            }
        }
    }


    private int search(int[][] grid, List<Node> houses, Node curr) {
        int[][] d = new int[grid.length][grid[0].length];
        for (int[] row : d) {
            Arrays.fill(row, Integer.MAX_VALUE);      // For blocks
        }

        bfs(grid, d, curr);

        // Calculate the total distance from space to house
        int total = 0;
        for (Node house : houses) {
            int dist = d[house.x][house.y];
            if (dist == Integer.MAX_VALUE) {    // Avoid overflow (MAX + MAX)
                return Integer.MAX_VALUE;
            }
            total += dist;
        }
        return total;
    }




    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {-1, 1, 0, 0};

    private void bfs(int[][] grid, int[][] d, Node curr) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new LinkedList<Node>();
        q.offer(curr);

        while (!q.isEmpty()) {
            Node head = q.poll();
            visited[head.x][head.y] = true;
            for (int k = 0; k < 4; k++) {
                int nx = head.x + dx[k];
                int ny = head.y + dy[k];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] != 2) {
                    d[nx][ny] = head.dist + 1;
                    visited[nx][ny] = true;
                    if (grid[nx][ny] != 1) {
                        q.offer(new Node(nx, ny, d[nx][ny]));
                    }
                }
            }
        }
    }
}
