class Solution {
    public int maxProduct(int[] nums) {
    int bestEnding=nums[0];
    int badEnding=nums[0];
    int ans=nums[0];
    int n=nums.length;
    for(int i=1;i<nums.length;i++){
        int v1=nums[i]*bestEnding;
        int v2=nums[i]*badEnding;
        int v3=nums[i];
        bestEnding=Math.max(v1,Math.max(v2,v3));
        badEnding=Math.min(v1,Math.min(v2,v3));
        ans=Math.max(ans,bestEnding);
    }   
    return ans;
    }
}