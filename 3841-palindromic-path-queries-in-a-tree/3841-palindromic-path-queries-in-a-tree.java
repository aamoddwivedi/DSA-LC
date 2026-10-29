
import java.util.*;

class Solution {

    int n, LOG;

    int[][] adj, up;

    int[] depth, tin, tout;
    int[] bit, charMask, order;

    int timer;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        // Build adjacency list
        int[] degree = new int[n];

        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        adj = new int[n][];

        for (int i = 0; i < n; i++) {
            adj[i] = new int[degree[i]];
        }

        int[] index = new int[n];

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            adj[u][index[u]++] = v;
            adj[v][index[v]++] = u;
        }

        up = new int[LOG][n];

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        bit = new int[n + 2];

        charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
        }

        buildTree();

        // Initialize root-to-node XOR masks
        int[] prefix = new int[n];

        for (int i = 0; i < n; i++) {

            int node = order[i];

            if (node == 0) {
                prefix[node] = charMask[node];
            } else {
                prefix[node] =
                    prefix[up[0][node]] ^ charMask[node];
            }

            // Initialize this node's prefix mask
            rangeUpdate(tin[node], tin[node], prefix[node]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {

            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {

                int node = Integer.parseInt(parts[1]);

                int newMask =
                    1 << (parts[2].charAt(0) - 'a');

                int change = charMask[node] ^ newMask;

                if (change != 0) {

                    // Update every root-prefix mask in this subtree
                    rangeUpdate(tin[node], tout[node], change);

                    charMask[node] = newMask;
                }

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = findLCA(u, v);

                int maskU = getMask(tin[u]);
                int maskV = getMask(tin[v]);

                int pathMask =
                    maskU ^ maskV ^ charMask[lca];

                // At most one odd-frequency character
                answer.add(
                    (pathMask & (pathMask - 1)) == 0
                );
            }
        }

        return answer;
    }

    void buildTree() {

        timer = 0;

        order = new int[n];

        int[] parent = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);

        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        // Iterative DFS
        while (top > 0) {

            int node = stack[--top];

            tin[node] = timer;
            order[timer++] = node;

            up[0][node] = parent[node];

            for (int k = 1; k < LOG; k++) {

                up[k][node] =
                    up[k - 1][up[k - 1][node]];
            }

            for (int next : adj[node]) {

                if (next == parent[node]) {
                    continue;
                }

                parent[next] = node;

                depth[next] = depth[node] + 1;

                stack[top++] = next;
            }
        }

        // Calculate subtree sizes
        int[] size = new int[n];

        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {

            int node = order[i];

            size[parent[node]] += size[node];
        }

        // Subtree Euler intervals
        for (int node = 0; node < n; node++) {

            tout[node] =
                tin[node] + size[node] - 1;
        }
    }

    int findLCA(int u, int v) {

        if (depth[u] < depth[v]) {

            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {

            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {

            if (up[k][u] != up[k][v]) {

                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    // XOR range update
    void rangeUpdate(int left, int right, int value) {

        add(left, value);
        add(right + 1, value);
    }

    // Fenwick Tree update
    void add(int index, int value) {

        index++;

        while (index < bit.length) {

            bit[index] ^= value;

            index += index & -index;
        }
    }

    // Get XOR mask at Euler position
    int getMask(int index) {

        int mask = 0;

        index++;

        while (index > 0) {

            mask ^= bit[index];

            index -= index & -index;
        }

        return mask;
    }
}
