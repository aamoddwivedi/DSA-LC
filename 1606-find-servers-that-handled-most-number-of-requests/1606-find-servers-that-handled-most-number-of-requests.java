class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release servers that have finished processing
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {

                int[] current = busy.poll();
                available.add(current[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int server = i % k;

            // Find the next available server
            Integer current = available.ceiling(server);

            if (current == null) {
                current = available.first();
            }

            // Assign request to server
            available.remove(current);

            count[current]++;

            busy.offer(new int[]{
                arrival[i] + load[i],
                current
            });
        }

        int max = 0;

        for (int requests : count) {
            max = Math.max(max, requests);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}