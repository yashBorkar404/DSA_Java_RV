import java.util.List;

public class BellmanFOrd {
     int[] bellmanFord(int n, List<List<Integer>> edges, int start) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        // Relax edges up to n-1 times
        for (int i = 0; i < n - 1; i++) {
            for (List<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int weight = edge.get(2);
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int weight = edge.get(2);
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                int[] temp = new int[n];
                temp[0] = -1; // Indicating a negative cycle
                return temp; // Return an array indicating a negative cycle
            }
        }

        return dist;
     }
}
