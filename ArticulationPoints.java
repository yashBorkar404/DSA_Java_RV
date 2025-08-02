import java.util.ArrayList;
import java.util.List;

public class ArticulationPoints {
    int  timer = 1;
    private void dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj, int[] tin, int[] low, List<Integer> articulationPoints) {
        vis[node] = 1;
        tin[node] = low[node] = timer++;
        int children = 0;
        boolean isArticulation = false;

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;
            if (vis[neighbor] == 0) {
                children++;
                dfs(neighbor, node, vis, adj, tin, low, articulationPoints);
                low[node] = Math.min(low[node], low[neighbor]);
                if (low[neighbor] >= tin[node] && parent != -1) {
                    isArticulation = true;
                }
            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }

        if ((parent == -1 && children > 1) || (parent != -1 && isArticulation)) {
            articulationPoints.add(node);
        }
    }

    public List<Integer> findArticulationPoints(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<Integer> articulationPoints = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, vis, adj, tin, low, articulationPoints);
            }
        }
        
        return articulationPoints;
    }
}
