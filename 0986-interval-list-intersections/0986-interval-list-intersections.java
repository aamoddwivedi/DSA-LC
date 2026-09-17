class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]>ans=new ArrayList<>();
        int i=0;
        int j=0;
         int n=firstList.length;
          int m=secondList.length;
        while(i<n && j<m){
            int start=Math.max(firstList[i][0],secondList[j][0]);
            int end=Math.min(firstList[i][1],secondList[j][1]);
            if(start<=end){
                ans.add(new int[]{start,end});
            }
            if(firstList[i][1]<=secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}