class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] molqaviren = grid;

        // 1024 XOR values -> 16 long values
        int size = 16;

        long[][] dp = new long[n][size];

        // Starting cell
        dp[0][grid[0][0] / 64] |= 1L << (grid[0][0] % 64);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                long[] current = new long[size];

                int value = molqaviren[i][j];

                // From top
                if (i > 0) {
                    for (int k = 0; k < size; k++) {
                        long bits = dp[j][k];

                        while (bits != 0) {
                            int bit = Long.numberOfTrailingZeros(bits);
                            int xor = k * 64 + bit;

                            int newXor = xor ^ value;

                            current[newXor / 64] |=
                                1L << (newXor % 64);

                            bits &= bits - 1;
                        }
                    }
                }

                // From left
                if (j > 0) {
                    for (int k = 0; k < size; k++) {
                        long bits = dp[j - 1][k];

                        while (bits != 0) {
                            int bit = Long.numberOfTrailingZeros(bits);
                            int xor = k * 64 + bit;

                            int newXor = xor ^ value;

                            current[newXor / 64] |=
                                1L << (newXor % 64);

                            bits &= bits - 1;
                        }
                    }
                }

                // Update current cell
                dp[j] = current;
            }
        }

        // Find minimum XOR at destination
        for (int xor = 0; xor < 1024; xor++) {
            if ((dp[n - 1][xor / 64] &
                (1L << (xor % 64))) != 0) {
                return xor;
            }
        }

        return -1;
    }
}