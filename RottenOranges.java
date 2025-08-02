import java.util.Queue;
class Pair{
    int row;
    int col;
    int tm;

    Pair(int row, int col, int tm) {
        this.row = row;
        this.col = col;
        this.tm = tm;
    }
}

public class RottenOranges {
    private int bfs(int[][] grid, int n, int m) {
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new java.util.LinkedList<>();
        int freshCount = 0;
        int time = 0;

        // Initialize the queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = 0; // Initialize visited array
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 1;
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // Directions for adjacent cells
        int[] delrow = {-1, 1, 0, 0};
        int[] delcol = {0, 0, -1, 1};
        int t = 0;
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int row = current.row;
            int col = current.col;
            time = current.tm;
            t = Math.max(t, time); // Update maximum time

            // Check all four directions
            for (int i = 0; i < 4; i++) {
                int newRow = row + delrow[i];
                int newCol = col + delcol[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && 
                    grid[newRow][newCol] == 1 && visited[newRow][newCol] == 0) {
                    visited[newRow][newCol] = 1;
                    grid[newRow][newCol] = 2; // Mark as rotten
                    freshCount--; // Decrease fresh count
                    queue.add(new Pair(newRow, newCol, time + 1)); // Add to queue with incremented time
                }
            }
        }
 
        return freshCount == 0 ? t  : -1; // Return time if all fresh oranges are rotten, else -1
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        
        return bfs(grid, grid.length, grid[0].length);
    }
}