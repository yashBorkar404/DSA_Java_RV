import java.util.ArrayList;
public class AlienDict {
    public String findOrder(String[] words, int N, int K) {
        // Step 1: Create the adjacency list and in-degree array
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[K];

        // Step 2: Build the graph based on the given words
        for (int i = 0; i < N - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLength = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLength; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    int u = word1.charAt(j) - 'a';
                    int v = word2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    inDegree[v]++;
                    break;
                }
            }
        }

        // Step 3: Perform topological sort using BFS
        return topologicalSort(adj, inDegree, K);
    }

    private String topologicalSort(ArrayList<ArrayList<Integer>> adj, int[] inDegree, int K) {
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] topoOrder = new int[K];
        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.remove(0);
            topoOrder[index++] = node;

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        String ans = "";
        for (int i = 0; i < K; i++) {
            ans += (char) (topoOrder[i] + 'a');
        }
        return ans;
    }
}
