import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class BridgesTarjansAlgo {
       private int timer = 1;
       private void dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj, int[] tin, int[] low, List<List<Integer>> bridges) {
           vis[node] = 1;
           tin[node] = low[node] = timer++;
           for (int neighbor : adj.get(node)) {
               if (neighbor == parent) continue;
               if (vis[neighbor] == 0) {
                   dfs(neighbor, node, vis, adj, tin, low, bridges);
                   low[node] = Math.min(low[node], low[neighbor]);
                   if (low[neighbor] > tin[node]) {
                       bridges.add(Arrays.asList(node, neighbor));
                   }
               } else {
                   low[node] = Math.min(low[node], tin[neighbor]);
               }
           }
       }
       public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
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
              List<List<Integer>> bridges = new ArrayList<>();
              for (int i = 0; i < n; i++) {
                  if (vis[i] == 0) {
                      dfs(i, -1, vis, adj, tin, low, bridges);
                  }
              }
              return bridges;
    }
}
