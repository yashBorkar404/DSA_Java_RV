import java.util.ArrayList;
public class Bipartitedfs {
     private boolean isBipartite(int src, int[] color, ArrayList<ArrayList<Integer>> adj){
        for(int adjacent: adj.get(src)){
            if(color[adjacent] == -1){
                color[adjacent] = color[src] - 1;
                if(!isBipartite(adjacent, color, adj)){
                    return false;
                }
            }else if(color[adjacent] == color[src]){
                return false;
            }
            
        }
        return true;
    }
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        java.util.Arrays.fill(color, -1); // Initialize all nodes as uncolored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) { // If the node is uncolored
                color[i] = 1; // Start coloring with 1
                if (!isBipartite(i, color, adj)) {
                    return false; // If any component is not bipartite
                }
            }
        }
        return true; // All components are bipartite
    }
}
