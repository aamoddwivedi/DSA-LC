class Solution {
    public int kthSmallest(int[][] matrix, int k) {
      int n= matrix.length;
      int start= matrix[0][0];
      int end = matrix[n-1][n-1];
      while(start<end){
        int mid = start + (end-start)/2;
        int count=0;
        int row=n-1;
        int col=0;
        while( row >= 0 && col<n){
            if(matrix[row][col]<=mid){
               count +=row+1;
               col++;
            }else {
                row--;
            }
        }
        if(count<k){
            start= mid+1;
        }else{
            end= mid;
        }
      }  
      return start;
    }
}