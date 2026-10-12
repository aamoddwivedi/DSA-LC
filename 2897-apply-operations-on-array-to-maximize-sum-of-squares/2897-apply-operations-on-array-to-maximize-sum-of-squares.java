
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int mod = 1_000_000_007;
        int[] count = new int[31];

        // Count set bits at every position
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long ans = 0;

        // Build k numbers with as many available set bits as possible
        for (int i = 0; i < k; i++) {
            long current = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    current |= (1L << bit);
                    count[bit]--;
                }
            }

            ans = (ans + current * current) % mod;
        }

        return (int) ans;
    }
}