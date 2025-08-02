import java.util.Set;
public class DistinctIslands {
    private void dfs(int row, int col, int baseRow, int baseCol, char[][] grid, boolean[][] visited, StringBuilder path) {
        visited[row][col] = true;
        path.append(row - baseRow).append(',').append(col - baseCol).append(';'); // Record the relative position
        int[] delRow = {-1, 1, 0, 0}; // Directions for row movement
        int[] delCol = {0, 0, -1, 1}; // Directions for column movement
        for (int i = 0; i < 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length 
                && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                dfs(newRow, newCol, baseRow, baseCol, grid, visited, path); // Continue DFS
            }
        }
    }

    public int numDistinctIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Set<String> uniqueIslands = new java.util.HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, i, j, grid, visited, path); // Start DFS from the current cell
                    uniqueIslands.add(path.toString()); // Add the unique path to the set
                }
            }
        }
        return uniqueIslands.size(); // Return the number of distinct islands
    }
}