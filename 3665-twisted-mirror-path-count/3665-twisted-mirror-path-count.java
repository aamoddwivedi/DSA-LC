class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int mod = 1_000_000_007;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            int current = 0;

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    // Mirror: swap right and down paths
                    int temp = current;
                    current = dp[j];
                    dp[j] = temp;
                } else {
                    // Normal cell: paths merge and split
                    int total = (current + dp[j]) % mod;

                    current = total;
                    dp[j] = total;
                }
            }
        }

        return dp[n - 1];
    }
}