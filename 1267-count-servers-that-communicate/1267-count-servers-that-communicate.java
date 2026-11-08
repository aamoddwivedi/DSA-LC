class Solution {
    public int countServers(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[] row = new int[n];
        int[] col = new int[m];

        // Count servers in each row and column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int ans = 0;

        // Count servers that can communicate
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (row[i] > 1 || col[j] > 1) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}