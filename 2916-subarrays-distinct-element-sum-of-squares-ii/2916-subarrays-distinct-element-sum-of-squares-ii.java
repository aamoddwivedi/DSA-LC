
class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new int[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        long currentSum = 0;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]] - 1;

            long oldSum = queryAndUpdate(1, 0, n - 1, prev + 1, i);

            currentSum = (currentSum + 2 * oldSum + (i - prev)) % MOD;
            ans = (ans + currentSum) % MOD;

            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }

    void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = (start + end) / 2;
            updateLazy(node * 2, start, mid, lazy[node]);
            updateLazy(node * 2 + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }

    void updateLazy(int node, int start, int end, int val) {
        sum[node] = (int) ((sum[node] + (long) (end - start + 1) * val) % MOD);
        lazy[node] = (lazy[node] + val) % MOD;
    }

    long queryAndUpdate(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            long oldSum = sum[node];
            updateLazy(node, start, end, 1);
            return oldSum;
        }

        push(node, start, end);

        int mid = (start + end) / 2;

        long oldSum = queryAndUpdate(node * 2, start, mid, left, right);
        oldSum += queryAndUpdate(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (int) ((sum[node * 2] + (long) sum[node * 2 + 1]) % MOD);

        return oldSum % MOD;
    }
}
