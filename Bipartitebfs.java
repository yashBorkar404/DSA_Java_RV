import java.util.ArrayList;
public class Bipartitebfs {
    private boolean isBipartite(int src, int[] color, ArrayList<ArrayList<Integer>> adj) {
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(src);
        color[src] = 1; // Start coloring with 1

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (color[neighbor] == -1) { // If the neighbor is uncolored
                    color[neighbor] = 1 - color[node]; // Color with opposite color
                    queue.offer(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false; // If the neighbor has the same color, not bipartite
                }
            }
        }
        return true; // All nodes processed without conflicts
    }

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        java.util.Arrays.fill(color, -1); // Initialize all nodes as uncolored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) { // If the node is uncolored
                if (!isBipartite(i, color, adj)) {
                    return false; // If any component is not bipartite
                }
            }
        }
        return true; // All components are bipartite
    }
}
