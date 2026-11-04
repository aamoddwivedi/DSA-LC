import java.util.*;

class Solution {

    class WindowCost {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void add(int num) {
            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                leftSum += num;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                rightSum += num;
            }

            balance();
        }

        void remove(int num) {
            if (left.containsKey(num)) {
                removeValue(left, num);
                leftSize--;
                leftSum -= num;
            } else {
                removeValue(right, num);
                rightSize--;
                rightSum -= num;
            }

            balance();
        }

        void removeValue(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        void balance() {

            while (leftSize > rightSize + 1) {
                int num = left.lastKey();

                removeValue(left, num);
                leftSize--;
                leftSum -= num;

                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                rightSum += num;
            }

            while (leftSize < rightSize) {
                int num = right.firstKey();

                removeValue(right, num);
                rightSize--;
                rightSum -= num;

                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                leftSum += num;
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                    + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window
        long[] cost = new long[n - x + 1];

        WindowCost window = new WindowCost();

        for (int i = 0; i < n; i++) {

            window.add(nums[i]);

            if (i >= x) {
                window.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // Step 2: Dynamic Programming
        long INF = Long.MAX_VALUE / 2;

        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Choice 1: Skip current element
                dp[i][j] = dp[i - 1][j];

                // Choice 2: Select a window of size x
                if (j > 0 && i >= x) {

                    long prev = dp[i - x][j - 1];

                    if (prev != INF) {
                        dp[i][j] = Math.min(
                            dp[i][j],
                            prev + cost[i - x]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }
}