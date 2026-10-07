class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s);
        visited.add(s);
        boolean found = false;

        while (!q.isEmpty()) {
            String current = q.poll();

            if (isValid(current)) {
                ans.add(current);
                found = true;
            }

            if (found) continue;

            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) != '(' && current.charAt(i) != ')') {
                    continue;
                }

                String next = current.substring(0, i)
                           + current.substring(i + 1);

                if (visited.add(next)) {
                    q.offer(next);
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }
}