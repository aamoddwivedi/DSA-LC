
class Solution {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.add(num);
        }

        while (k > 0) {
            int x = pq.poll();
            pq.add(x + 1);
            k--;
        }

        long ans = 1;
        int mod = 1000000007;

        while (!pq.isEmpty()) {
            ans = (ans * pq.poll()) % mod;
        }

        return (int) ans;
    }
}
