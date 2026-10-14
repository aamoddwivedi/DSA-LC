class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Add as many 9s as possible
        int count = sum / 9;
        int rem = sum % 9;

        ans.append("9".repeat(count));

        // Add the remaining digit
        if (rem > 0) {
            ans.append(rem);
        }

        // Fill remaining positions with zeros
        ans.append("0".repeat(num - ans.length()));

        return ans.toString();
    }
}