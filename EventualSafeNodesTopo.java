import java.util.*;
public class EventualSafeNodesTopo {
    
    public int[] topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }
        ArrayList<Integer> queue = new ArrayList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
        int[] topoOrder = new int[V];
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
        return topoOrder;
    }
    public int[] eventualSafeNodes(ArrayList<ArrayList<Integer>> graph) {
        int V = graph.size();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int neighbor : graph.get(i)) {
                adj.get(neighbor).add(i);
            }
        }
        int[] topoOrder = topologicalSort(adj);
        Arrays.sort(topoOrder);
        return topoOrder;
    }
}
