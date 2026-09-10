class Solution {
    public boolean isHappy(int n) {
        int slow=squareSum(n);
        int fast=squareSum(squareSum(n));
        while(slow!=fast){
             slow=squareSum(slow);
             fast=squareSum(squareSum(fast));
        }
        return slow==1;
    }
    private int squareSum(int n){
        int sum =0;
        while(n>0){
            int digit=n%10;
            sum+=digit*digit;
            n/=10;
        }
    return sum;
    }
}