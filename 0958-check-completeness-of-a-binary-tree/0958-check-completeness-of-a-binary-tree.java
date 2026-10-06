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
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode>q= new LinkedList<>();
        q.add(root);
        boolean foundNull=false;
        while(!q.isEmpty()){
            TreeNode current=q.poll();
            if(current==null){
                foundNull=true;
            }else{
                if(foundNull){
                    return false;
                }
                q.add(current.left);
                q.add(current.right);
            }
        }
        return true;
    }
}