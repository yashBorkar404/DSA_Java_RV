class Pair{
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Os_Xs {
    public void dfs(char[][] grid, Pair start, boolean[][] visited) {
        int row = start.x;
        int col = start.y;
        int[] delrow = {-1, 1, 0, 0};
        int[] delcol = {0, 0, -1, 1};
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length && !visited[nrow][ncol] && grid[nrow][ncol] == 'O') {
                dfs(grid, new Pair(nrow, ncol), visited);
            }
        }
    }
    public char[][] fill(char[][] grid){
        int m  = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        // Mark all 'O's connected to the boundary as visited
        for (int i = 0;i<m;i++){
            if(grid[i][0] == 'O' && !visited[i][0]) {
                dfs(grid, new Pair(i, 0), visited);
            }
            if(grid[i][n-1] == 'O' && !visited[i][n-1]) {
                dfs(grid, new Pair(i, n-1), visited);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 'O' && !visited[0][j]) {
                dfs(grid, new Pair(0, j), visited);
            }
            if (grid[m - 1][j] == 'O' && !visited[m - 1][j]) {
                dfs(grid, new Pair(m - 1, j), visited);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'O' && !visited[i][j]) {
                    grid[i][j] = 'X'; // Fill unvisited 'O's with 'X'
                }
            }
        }
        return grid;
    }
}
    

