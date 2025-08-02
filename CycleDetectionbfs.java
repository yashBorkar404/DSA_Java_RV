import java.util.ArrayList;

class Pair{
    int node;
    int parent;

    Pair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
public class CycleDetectionbfs {
    private boolean checkForCycle(int src,int V, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        ArrayList<Pair> queue = new ArrayList<>();
        queue.add(new Pair(src, -1)); // Start with the source node and no parent
        visited[src] = true;

        while (!queue.isEmpty()) {
            Pair current = queue.remove(0);
            int node = current.node;
            int parent = current.parent;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new Pair(neighbor, node));
                } else if (neighbor != parent) { // If the neighbor is visited and is not the parent
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle detected
    }
    //For every connected component, we will check if there is a cycle or not.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkForCycle(i, V, adj, visited)) {
                    return true; // Cycle found
                }
            }
        }
        return false; // No cycle found
    }
}
