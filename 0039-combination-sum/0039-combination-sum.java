class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>ans= new ArrayList<>();
        solve(candidates,target,0,new ArrayList<>(),ans);
        return ans;
    }
    private void solve(int [] candidates,int target,int index,List<Integer>current,List<List<Integer>>ans){
        if(target==0){
            ans.add(new ArrayList<>(current));
            return;
        }
        for(int i =index;i<candidates.length;i++){
            if(candidates[i]>target){
                continue;
            }
            current.add(candidates[i]);
            solve(candidates,target-candidates[i],i,current,ans);
            current.remove(current.size()-1);
        }
    }
}