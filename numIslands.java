import java.util.Queue;

class Pair{
    int x;
    int y;
    
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
public class numIslands {
    private void bfs(int row, int col, char[][] grid, int[][] visited) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Pair> queue = new java.util.LinkedList<>();
        queue.add(new Pair(row, col));
        visited[row][col] = 1;
        while(!queue.isEmpty()) {
            Pair current = queue.poll();
            int x = current.x;
            int y = current.y;
            
            // Check all 4 directions
            int[] delRow = {-1, 1, 0, 0}; // Directions for row movement
            int[] delCol = {0, 0, -1, 1}; // Directions for column movement
            for (int i = 0; i < 4; i++) {
                int newRow = x + delRow[i];
                int newCol = y + delCol[i];
                
                // Check if the new position is within bounds and is part of an island
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m 
                    && grid[newRow][newCol] == '1' && visited[newRow][newCol] == 0) {
                    queue.add(new Pair(newRow, newCol));
                    visited[newRow][newCol] = 1; // Mark as visited
                }
            }
            }
        }
    public int numIsland(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int islandCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    bfs(i, j, grid, visited);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }
}
