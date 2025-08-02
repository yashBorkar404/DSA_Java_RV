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
public class OpsToMakeNEtworkCOnnected {
   int makeConnected(int n, ArrayList<ArrayList<Integer>> edges) {
        
        int extraEdges = 0; 
        DisjointSet ds = new DisjointSet(n);
        for (ArrayList<Integer> conn : edges) {
            if(ds.findUParent(conn.get(0)) == ds.findUParent(conn.get(1))) 
                extraEdges++;
            else
                ds.unionByRank(conn.get(0), conn.get(1));
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) components++;
        }
        if (extraEdges < components - 1) return -1; // Not enough edges to connect all components
        return components - 1;
    }
}
