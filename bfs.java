import java.util.ArrayList;

public class bfs{
    public ArrayList<Integer> BFS(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[V];
        ArrayList<Integer> queue = new ArrayList<>();
        
        // Start BFS from the first vertex (0)
        queue.add(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.remove(0);
            bfs.add(node);
            
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        return bfs;
    }
}