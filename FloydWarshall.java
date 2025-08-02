public class FloydWarshall {
    public int[][] floydWarshall(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        
        // Initialize distances
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE / 2; // Use a large value to avoid overflow
                }
            }
        }

        // Fill initial distances from edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            dist[u][v] = Math.min(dist[u][v], weight); // Handle multiple edges
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE / 2 && dist[k][j] != Integer.MAX_VALUE / 2) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }   
}
