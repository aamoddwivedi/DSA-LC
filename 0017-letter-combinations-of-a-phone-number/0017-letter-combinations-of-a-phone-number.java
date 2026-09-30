class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans= new ArrayList<>();
        if(digits.length()==0){
            return ans;
        }
        String [] map= {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"} ;
        solve(0,digits,"",map,ans);
          return ans;
    }
    private void solve(int index,String digits,String current, String[]map,List<String>ans){
        if (index==digits.length()){
            ans.add(current);
            return;
        }
        String letters = map[digits.charAt(index)-'0'];
        for(char ch : letters.toCharArray()){
            solve(index+1,digits,current + ch,map,ans);
        }
    }
}