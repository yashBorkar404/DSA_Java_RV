import java.util.ArrayList;

public class CycleDetectionDirectedDfs {
    private boolean dfsCheck(int node, boolean[] visited, boolean[] pathvisited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        pathvisited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfsCheck(neighbor, visited, pathvisited, adj)) {
                    return true;
                }
            } else if (pathvisited[neighbor]) {
                return true;
            }
        }
        pathvisited[node] = false;
        return false;
    }
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        boolean[] pathvisited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, visited, pathvisited, adj)) {
                    return true;
                }
            }
        }
        return false;

    }
}
