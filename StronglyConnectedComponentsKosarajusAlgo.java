import java.util.ArrayList;
import java.util.Stack;
public class StronglyConnectedComponentsKosarajusAlgo {
     int numStronglyConnectedComponents(int n, ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> timeWiseSortedIndex = new Stack<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i, adj, visited, timeWiseSortedIndex);
            }
        }
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            visited[i] = false; // Reset visited for second DFS
            for (int neighbor : adj.get(i)) {

                transpose.get(neighbor).add(i);
            }
        }
        int count = 0;
        while (!timeWiseSortedIndex.isEmpty()) {
            int node = timeWiseSortedIndex.pop();
            if (!visited[node]) {
                dfs(node, transpose, visited);
                count++;
            }
        }
        return count;
    }
    void dfs1(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> timeWiseSortedIndex) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, adj, visited, timeWiseSortedIndex);
            }
        }
        timeWiseSortedIndex.push(node);
    }
    void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }
}
