/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>>res=new ArrayList<>();
      if(root == null){
        return res;
      }
      Queue<TreeNode>q= new LinkedList<>();
      q.add(root);
      boolean reverse= false;
      while(!q.isEmpty()){
      int lvlsize = q.size();
      ArrayList<Integer>temp= new ArrayList<>();
      while(lvlsize -->0){
        TreeNode t= q.poll();
        temp.add(t.val);
        if(t.left!=null){
            q.add(t.left);
        }
        if(t.right!=null){
            q.add(t.right);
        }
      }
      if(reverse){
        Collections.reverse(temp);
      } res.add(temp);
      reverse=!reverse;
    }
    return res;
}
}