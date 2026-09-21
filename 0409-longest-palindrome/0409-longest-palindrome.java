import java.util.HashMap;

class Solution {
    public int longestPalindrome(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int length = 0;
        boolean odd = false;

        for (int freq : map.values()) {

            if (freq % 2 == 0) {
                length += freq;
            } else {
                length += freq - 1;
                odd = true;
            }
        }

        if (odd) {
            length++;
        }

        return length;
    }
}