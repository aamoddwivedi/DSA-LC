class Solution {
    public int scoreOfParentheses(String s) {
        int high=0;
        int ans=0;
     for(int i=0;i<s.length();i++){
        if(s.charAt(i)=='('){
            high++;
        }else{
            high--;
            if(s.charAt(i-1)=='('){
                ans +=1 << high;
            }
        }
     }        
     return ans;
    }
}