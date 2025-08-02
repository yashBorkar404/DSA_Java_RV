import java.util.Arrays;
import java.util.PriorityQueue; 
class Triple{
    int dist;
    int row;
    int col;
    Triple(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}
public class MinEffortPath {
    int minEffort(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<Triple> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Triple(0, 0, 0));
        int delCol[] = {1, -1, 0, 0}; // Directions for column movement
        int delRow[] = {0, 0, 1, -1}; // Directions for row movement    
        while (!pq.isEmpty()) {
            Triple curr = pq.poll();
            int currDist = curr.dist;
            int currRow = curr.row;
            int currCol = curr.col;

            if (currRow == n - 1 && currCol == m - 1) {
                return currDist; // Reached the destination
            }
            for(int i = 0;i<4;i++){
                int newRow = currRow + delRow[i];
                int newCol = currCol + delCol[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int newDist = Math.max(currDist, Math.abs(heights[newRow][newCol] - heights[currRow][currCol]));
                    if (newDist < dist[newRow][newCol]) {
                        dist[newRow][newCol] = newDist;
                        pq.offer(new Triple(newDist, newRow, newCol));
                    }
                }
            }
        }
        return -1; // If no path found
    }
}
