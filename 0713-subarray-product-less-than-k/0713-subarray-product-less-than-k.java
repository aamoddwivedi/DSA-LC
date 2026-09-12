class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i=0;
        int n = nums.length;
        int product =1;
        int ans=0;
        if(k<=1){
            return 0;
        }
            for(int j=0;j<n;j++){
                product *=nums[j];
            while(product>=k){
                product /=nums[i];
                i++;
            }
            ans += j - i  +1;    
      }
            return ans;
        }
}