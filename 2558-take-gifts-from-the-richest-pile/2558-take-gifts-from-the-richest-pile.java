class Solution {
    public long pickGifts(int[] gifts, int k) {
     PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
     for(int gift:gifts){
        pq.add(gift);
     }   
     while(k>0){
        int x=pq.poll();
        int remaining=(int)Math.sqrt(x);
        pq.add(remaining);
        k--;
     }
     long ans=0;
    while(!pq.isEmpty()){
        ans+=pq.poll();
    }
    return ans;
    }
}