import java.util.Queue;

class Pair{
    int row ,col, dist;
    Pair(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}
public class matrix0_1{
    public int[][] matrix(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        if(m == 0 || n == 0) return new int[0][0]; // Handle edge case for empty grid
        Queue<Pair> queue = new java.util.LinkedList<>();
        int[][] dist = new int[m][n];
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    queue.offer(new Pair(i, j, 0));
                    visited[i][j] = 1; 
                }
            }
        }
        int[] delrow = {-1, 1, 0, 0};
        int[] delcol = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            Pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;
            dist[row][col] = curr.dist;
            int steps = curr.dist;
            for(int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                if(nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && visited[nrow][ncol] == 0) {
                    visited[nrow][ncol] = 1;// Update distance in the grid
                    queue.offer(new Pair(nrow, ncol, steps + 1));
                }
            }
        }
        return dist;
    }
}
