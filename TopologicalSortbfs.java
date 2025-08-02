import java.util.ArrayList;
public class TopologicalSortbfs {
    

    public int[] topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }
        ArrayList<Integer> queue = new ArrayList<>();

        int[] topoOrder = new int[V];
        for(int i=0;i<V;i++){
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
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
}
