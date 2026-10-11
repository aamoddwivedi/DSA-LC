class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        int i=0;
        int day=1;
        int res=0;
        int n=events.length;
        while(i<n || !pq.isEmpty()){
            if(pq.isEmpty()){
                day=Math.max(day,events[i][0]);
            }
            while(i<n&&events[i][0]<=day){
                pq.add(events[i][1]);
                i++;
            }
            while(!pq.isEmpty()&&pq.peek()<day){
                pq.poll();
            }if(!pq.isEmpty()){
                pq.poll();
                res++;
                day++;
            }
        }
        return res;
    }
}