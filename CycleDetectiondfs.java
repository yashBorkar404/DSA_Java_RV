import java.util.ArrayList;
public class CycleDetectiondfs {
    private boolean checkForCycle(int src, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[src] = true; // Mark the current node as visited

        for (int neighbor : adj.get(src)) {
            if (!visited[neighbor]) {
                // If the neighbor is not visited, recursively check for cycle
                if (checkForCycle(neighbor, src, adj, visited)) {
                    return true; // Cycle detected in the recursive call
                }
            } else if (neighbor != parent) {
                // If the neighbor is visited and is not the parent of the current node
                return true; // Cycle detected
            }
        }
        return false; // No cycle detected
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // For every unvisited node, check for cycle
                if (checkForCycle(i, -1, adj, visited)) {
                    return true; // Cycle found
                }
            }
        }
        return false; // No cycle found
    }
}
