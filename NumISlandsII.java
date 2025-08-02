import java.util.ArrayList;
import java.util.List;

class DisjointSet{
    List<Integer> parent, rank;
    public DisjointSet(int n) {
        parent = new ArrayList<>(n);
        rank = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            parent.add(i);
            rank.add(0);
        }
    }
    public int findUParent(int node) {
        if(parent.get(node) == node) {
            return node;
        }
        parent.set(node, findUParent(parent.get(node))); // Path compression
        return parent.get(node);
    }
    public void unionByRank(int u, int v) {
        int uParent = findUParent(u);
        int vParent = findUParent(v);
        if (uParent == vParent) return; // Already in the same set

        if (rank.get(uParent) < rank.get(vParent)) {
            parent.set(uParent, vParent);
        } else if (rank.get(uParent) > rank.get(vParent)) {
            parent.set(vParent, uParent);
        } else {
            parent.set(vParent, uParent);
            rank.set(uParent, rank.get(uParent) + 1);
        }
    }
}
public class NumISlandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        DisjointSet ds = new DisjointSet(m * n);
        boolean[][] grid = new boolean[m][n];
        int count = 0;

        for (int[] pos : positions) {
            int x = pos[0], y = pos[1];
            if (grid[x][y]) {
                result.add(count); // Already occupied
                continue;
            }
            grid[x][y] = true;
            count++;

            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny]) {
                    int currentId = x * n + y;
                    int neighborId = nx * n + ny;
                    if (ds.findUParent(currentId) != ds.findUParent(neighborId)) {
                        ds.unionByRank(currentId, neighborId);
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}
