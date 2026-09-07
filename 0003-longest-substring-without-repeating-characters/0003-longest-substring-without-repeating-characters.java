class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer>lastIndex=new HashMap<>();
        int left=0;
        int maxLen=0;
        int n = s.length();
        for( int right=0;right<n;right++){
            char cc=s.charAt(right);
            if(lastIndex.containsKey(cc)){
                left=Math.max(left,lastIndex.get(cc)+1);
            }
            lastIndex.put(cc,right);
        maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
  }
}