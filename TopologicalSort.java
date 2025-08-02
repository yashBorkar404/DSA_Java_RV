import java.util.Stack;
public class TopologicalSort{
    public void dfs(int node, boolean[] visited, Stack<Integer> stack, int[][] adj) {
        visited[node] = true;
        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack, adj);
            }
        }
        stack.push(node);
    }
    public int[] topologicalSort(int[][] graph) {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, graph);
            }
        }
         
        int[] topoOrder = new int[V];
        for (int i = 0; i < V; i++) {
            topoOrder[i] = stack.pop();
        }
        return topoOrder;
    }
}