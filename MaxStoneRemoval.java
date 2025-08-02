import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MaxStoneRemoval {
    public int maxStoneRemoval(int n, ArrayList<ArrayList<Integer>> stones) {
        DisjointSet ds = new DisjointSet(n * n);
        int maxRow = 0;
        int maxCol = 0;
        for (ArrayList<Integer> stone : stones) {
            maxRow = Math.max(maxRow, stone.get(0));
            maxCol = Math.max(maxCol, stone.get(1));
        }
        HashMap<Integer, Integer> stoneNodes = new HashMap<>();
        for(int i=0; i < stones.size(); i++) {
            int nodeRow = stones.get(i).get(0);
            int nodeCol = stones.get(i).get(1) + maxRow + 1;
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : stoneNodes.entrySet()){
            if(ds.parent.get(entry.getKey()) == entry.getKey()) {
                count++;
        }
    }
    return stones.size() - count;

    }
}