class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0],b[0]));
        List<int[]>ans=new ArrayList <>();
        int start= intervals[0][0];
        int end= intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            int nextStart=intervals[i][0];
            int nextEnd= intervals[i][1];
            if(nextStart <= end){
                end=Math.max(end,nextEnd);
            }else{
                ans.add(new int[]{start,end});
                start=nextStart;
                end=nextEnd;
            }
        }
        ans.add(new int[]{start,end});
        return ans.toArray(new int[ans.size()][]);
    }
}