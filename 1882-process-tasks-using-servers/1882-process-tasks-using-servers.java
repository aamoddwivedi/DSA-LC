class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                if (a[1] != b[1]) return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        for (int i = 0; i < m; i++) {

            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                long[] server = busy.poll();

                available.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            if (available.isEmpty()) {

                long[] server = busy.poll();

                long finishTime = server[0] + tasks[i];

                ans[i] = (int) server[2];

                busy.offer(new long[]{
                    finishTime,
                    server[1],
                    server[2]
                });

            } else {

                int[] server = available.poll();

                ans[i] = server[1];

                busy.offer(new long[]{
                    i + (long) tasks[i],
                    server[0],
                    server[1]
                });
            }
        }

        return ans;
    }
}