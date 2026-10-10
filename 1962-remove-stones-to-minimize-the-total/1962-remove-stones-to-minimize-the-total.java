class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int pile:piles){
            pq.add(pile);
        }
        while(k>0){
            int x=pq.poll();
            x=x-x/2;
            pq.add(x);
            k--;
        }
        int ans=0;
        while(!pq.isEmpty()){
            ans+=pq.poll();
        }
        return ans;
    }
}