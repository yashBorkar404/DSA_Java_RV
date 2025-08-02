import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class DisjointSet{
    List<Integer> parent, rank,size;
    public DisjointSet(int n) {
        parent = new ArrayList<>(n);
        rank = new ArrayList<>(n);
        size = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            parent.add(i);
            rank.add(0);
            size.add(1);
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
    public void unionBySize(int u, int v) {
        int uParent = findUParent(u);
        int vParent = findUParent(v);
        if (uParent == vParent) return; // Already in the same set

        if (size.get(uParent) < size.get(vParent)) {
            parent.set(uParent, vParent);
            size.set(vParent, size.get(vParent) + size.get(uParent));
        } else {
            parent.set(vParent, uParent);
            size.set(uParent, size.get(uParent) + size.get(vParent));
        }
    }
}
public class MaxSizeIsland {
     public int maxSizeIsland(int n, ArrayList<ArrayList<Integer>> grid) {
        DisjointSet ds = new DisjointSet(n * n);
        int maxSize = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    int currentId = i * n + j;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d], nj = j + dy[d];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid.get(ni).get(nj) == 1) {
                            int neighborId = ni * n + nj;
                            ds.unionBySize(currentId, neighborId);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i).get(j) == 0){
                    Set<Integer> uniqueParents = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d], nj = j + dy[d];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid.get(ni).get(nj) == 1) {
                            int neighborId = ni * n + nj;
                            uniqueParents.add(ds.findUParent(neighborId));
                        }
                    }
                    int size = 1; // Count the current cell
                    for (int parent : uniqueParents) {
                        size += ds.size.get(parent);
                    }
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        for(int i = 0; i < n * n; i++) {
            maxSize = Math.max(maxSize, ds.size.get(ds.findUParent(i)));
        }
        return maxSize ;// If no land, return n*n
    }
}
