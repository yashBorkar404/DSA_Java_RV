import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
        

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

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
public class Kruskalls {
    public int kruskals(int n, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (ArrayList<Integer> neighbor : adj.get(i)) {
                int nextNode = neighbor.get(0);
                int edgeWeight = neighbor.get(1);
                edges.add(new Edge(i, nextNode, edgeWeight));
            }
        }

        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(n);
        int totalCost = 0;
        for (Edge edge : edges) {
            int uParent = ds.findUParent(edge.src);
            int vParent = ds.findUParent(edge.dest);
            if (uParent != vParent) {
                ds.unionByRank(uParent, vParent);
                totalCost += edge.weight;
            }
        }
        return totalCost;

    }


}
