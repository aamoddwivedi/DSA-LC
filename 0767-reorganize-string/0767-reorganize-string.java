class Solution {
    public String reorganizeString(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        PriorityQueue<Character> maxHeap =
            new PriorityQueue<>((a, b) -> freq[b - 'a'] - freq[a - 'a']);

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (freq[ch - 'a'] > 0) {
                maxHeap.offer(ch);
            }
        }

        StringBuilder result = new StringBuilder();

        while (maxHeap.size() >= 2) {

            char first = maxHeap.poll();
            char second = maxHeap.poll();

            result.append(first);
            result.append(second);

            freq[first - 'a']--;
            freq[second - 'a']--;

            if (freq[first - 'a'] > 0) {
                maxHeap.offer(first);
            }

            if (freq[second - 'a'] > 0) {
                maxHeap.offer(second);
            }
        }
        if (!maxHeap.isEmpty()) {

            char last = maxHeap.poll();

            if (freq[last - 'a'] > 1) {
                return "";
            }

            result.append(last);
        }

        return result.toString();
    }
}