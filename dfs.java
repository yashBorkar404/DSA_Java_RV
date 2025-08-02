import java.util.ArrayList;
public class dfs {
   public static void dfsUtil(int node, boolean[] visited, ArrayList<Integer> dfs, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        dfs.add(node);
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited, dfs, adj);
            }
        }
    }

    public static ArrayList<Integer> DFS(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        // Start DFS from the first vertex (0)
        dfsUtil(0, visited, dfs, adj);
        
        return dfs;
    } 
    public static void main(String[] args) {
        // Example usage
        int V = 5; // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Adding edges
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(1).add(4);
        
        ArrayList<Integer> result = DFS(V, adj);
        System.out.println(result); // Output: [0, 1, 3, 4, 2]
    }
}
