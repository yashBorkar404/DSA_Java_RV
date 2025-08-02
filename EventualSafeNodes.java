/*The only criteria to classify a node as non safe  will be whether it is part of a cycle or not or whether it leads to a cycle as if not all nodes have to reach some or the other terminal point.
 */

import java.util.*;

public class EventualSafeNodes {
    private boolean dfsCheck(int node, int[] visited, boolean[] pathvisited, int[][] adj) {
        visited[node] = 1;
        pathvisited[node] = true;

        for (int neighbor : adj[node]) {
            if (visited[neighbor] == 0) {
                if (dfsCheck(neighbor, visited, pathvisited, adj)) {
                    return true;
                }
            } else if (pathvisited[neighbor]) {
                return true;
            }
        }
        pathvisited[node] = false;
        visited[node] = 2; 
        return false;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;
        boolean[] pathvisited = new boolean[V];
        int[] visited = new int[V]; // 0: unvisited, 1: visiting, 2: safe

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfsCheck(i, visited, pathvisited, graph);
            }
        }

        // Collect all safe nodes
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 2) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }
}
