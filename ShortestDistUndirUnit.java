import java.util.ArrayList;
public class ShortestDistUndirUnit{
    public int[] shortestDistance(ArrayList<ArrayList<Integer>> adj, int source) {
        int V = adj.size();
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        // BFS to find shortest distance in unweighted graph
        boolean[] visited = new boolean[V];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    dist[neighbor] = dist[node] + 1; // Update distance
                    queue.offer(neighbor);
                }
            }
        }
        return dist;
    }
}