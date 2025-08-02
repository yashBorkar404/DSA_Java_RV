import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
class Triple{
    int first;
    int second;
    int third;

    Triple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
public class ShortestDistanceBinaryMaze {
    public int shortestPathBinaryMaze(int[][] grid, int[] start, int[] end) {
        int n = grid.length;
        int m = grid[0].length;
        if(start[0] == end[0] && start[1] == end[1]) return 0; // If start and end are the same

        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;

        Queue<Triple> pq = new LinkedList<>();
        pq.offer(new Triple(0, start[0], start[1]));

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!pq.isEmpty()) {
            Triple curr = pq.poll();
            int d = curr.first;
            int x = curr.second;
            int y = curr.third;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && d + 1 < dist[nx][ny]) {
                    dist[nx][ny] = d + 1;
                    if (nx == end[0] && ny == end[1]) return d + 1; // If we reach the end
                    pq.offer(new Triple(d + 1, nx, ny));
                }
            }
        }
        return -1;
    }
}
