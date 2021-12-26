// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Time - O(n)
// Space - O(n) for the queue

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List <Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList <>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList <>();
        q.add(root);

        TreeNode temp;
        // We are doing a BFS and putting right child first then the left child
        while(!q.isEmpty()) {
            int size = q.size();
            // we are running through each level and the element present at the 0th index is the rightmost element and
            // thus should be added in our result
            for(int i =0; i < size; i++) {
                temp = q.poll();
                if(i ==0) {
                    result.add(temp.val);
                }
                if(temp.right!=null) {
                    q.add(temp.right);
                }
                if(temp.left!=null) {
                    q.add(temp.left);
                }
            }
        }
        return result;
    }

    // TC - O(n)
    // SC - O(h) - where h is height of tree
    public List <Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList <>();
        if(root == null) {
            return result;
        }

        rightSideViewDFS(root, 0, result);
        return result;
    }

    private void rightSideViewDFS(TreeNode root, int level, List<Integer> result) {
        if(root == null) {
            return;
        }

        // that means that the level is new
        // for the same level, the list size might be greater than the level
        // and hence the left view will be ignored
        if(result.size() == level) {
            result.add(root.val);
        }
        //going to right first, to capture the right side view
        rightSideViewDFS(root.right, level+1, result);
        rightSideViewDFS(root.left, level+1, result);
    }

}
