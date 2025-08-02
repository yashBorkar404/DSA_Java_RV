import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

class DisjointSet{
    List<Integer> parent, rank;
    public DisjointSet(int n) {
        parent = new ArrayList<>(n);
        rank = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            parent.add(i);
            rank.add(0);
        }
    }
    public int findUParent(int node) {
        if(parent.get(node) == node) {
            return node;
        }
        parent.set(node, findUParent(parent.get(node))); // Path compression
        return parent.get(node);
    }
    public void unionByRank(int u, int v) {
        int uParent = findUParent(u);
        int vParent = findUParent(v);
        if (uParent == vParent) return; // Already in the same set

        if (rank.get(uParent) < rank.get(vParent)) {
            parent.set(uParent, vParent);
        } else if (rank.get(uParent) > rank.get(vParent)) {
            parent.set(vParent, uParent);
        } else {
            parent.set(vParent, uParent);
            rank.set(uParent, rank.get(uParent) + 1);
        }
    }
}
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToId = new HashMap<>();
        int id = 0;
        DisjointSet ds = new DisjointSet(accounts.size());
        int n = accounts.size();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!emailToId.containsKey(account.get(i))) {
                    emailToId.put(account.get(i), id);
                }
                else{
                    int temp = emailToId.get(account.get(i));
                    ds.unionByRank(temp, id);
                }
            }
            id++;
        }
        ArrayList<String>[] mergedEmails = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergedEmails[i] = new ArrayList<>();
        }
        for(Map.Entry<String, Integer> entry : emailToId.entrySet()) {
            String email = entry.getKey();
            int node = ds.findUParent(entry.getValue());
            mergedEmails[node].add(email);
        }
        
        
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedEmails[i].size() > 0) {
                Collections.sort(mergedEmails[i]);
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(accounts.get(i).get(0)); // Add the name
                mergedAccount.addAll(mergedEmails[i]); // Add the sorted emails
                result.add(mergedAccount);
            }
        }

    
            return result;
  }
}

