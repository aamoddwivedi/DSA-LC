class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer,Integer>freq = new HashMap<>();
        int left=0;
        int maxFruits=0;
        for(int right=0;right<fruits.length;right++){
            int currentFruit=fruits[right];
            freq.put(currentFruit,freq.getOrDefault(currentFruit,0)+1);
            while(freq.size()>2){
                int leftFruit=fruits[left];
                freq.put(leftFruit,freq.getOrDefault(leftFruit,0)-1);
                if(freq.get(leftFruit)==0){
                    freq.remove(leftFruit);
                } 
                left++;
            }
             maxFruits=Math.max(maxFruits,right-left+1);
        }
        return maxFruits;
    }
}