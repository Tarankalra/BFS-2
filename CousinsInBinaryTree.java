import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree {

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

    // TC - O(n)
    // Space - O(n) - tho the number of elements in the queue are less, but say is n ==1, then O(n)
    public boolean isCousinsBFS(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList <>();
        q.add(root);
        boolean isX = false;
        boolean isY = false;
        int size;

        while(!q.isEmpty()) {
            size = q.size();
            for(int i =0; i< size; i++) {
                TreeNode temp = q.poll();
                // setting the flag as true when the value is matched with either X or Y
                if(temp.val == x) {
                    isX = true;
                }
                if(temp.val == y) {
                    isY = true;
                }

                // checking if x and y are not siblings
                if(temp.left !=null && temp.right!=null) {
                    if((temp.left.val == x && temp.right.val ==y) || (temp.left.val == y && temp.right.val ==x)) {
                        return false;
                    }
                }

                if(temp.left!=null) {
                    q.add(temp.left);
                }

                if(temp.right!=null) {
                    q.add(temp.right);
                }
                // if both X and Y are found in the same level, then return true
                if(isX && isY) {
                    return true;
                }
            }
            // if one of them is true, then return false
            // because that means they are not in same level
            if(isX || isY) {
                return false;
            }
        }
        return false;
    }

    // Simple DFS method
    // TC - O(n)
    // Space - O(h)
    static int level;
    static boolean isX;
    static boolean isY;
    static boolean isSiblings;
    static boolean isCousins;
    public static boolean isCousinsDFS(TreeNode root, int x, int y) {
        level = -1;
        isX = false;
        isY = false;
        isSiblings = false;
        isCousins = false;

        isCousinsDFSHelper(root, x, y, 0);
        return !isSiblings && isCousins;
    }

    private static void isCousinsDFSHelper(TreeNode root, int x, int y, int localLevel) {
        // if the nodes are already known to be siblings or cousins, we need not to go further
        if(root == null || isSiblings || isCousins) {
            return;
        }

        // checking if the nodes are siblings or not,
        // if yes then we are returning
        if((root.left!=null && root.right!=null) &&
                ((root.left.val == x || root.right.val == y) || (root.left.val == y || root.right.val == x))) {
            isSiblings = true;
            return;
        }

        // if root's value is X
        // we are checking that isY true or not, if yes, then we are checking their levels are same of not
        if(root.val == x) {
            isX = true;
            if(isY) {
                isCousins = level == localLevel;
                return;
            }
            level = localLevel; // setting the level of either x or y in global variable
            return;
        }else if (root.val == y) {
            isY = true;
            if(isX) {
                isCousins = level == localLevel;
                return;
            }
            level = localLevel; // setting the level of either x or y in global variable
            return;
        }

        isCousinsDFSHelper(root.left, x, y, localLevel+1);
        isCousinsDFSHelper(root.right, x, y, localLevel+1);
    }

    // Simple DFS method
    // TC - O(n)
    // Space - O(h)
    TreeNode xParent;
    TreeNode yParent;
    int xLevel;
    int yLevel;
    public boolean isCousinsDFS2(TreeNode root, int x, int y) {
        if(root == null) {
            return false;
        }
        xLevel = -1;
        yLevel = -1;
        xParent = null;
        yParent = null;


        isCousinsDFSHelper2(root, x, y, 0, root);
        return xParent != yParent && xLevel == yLevel;
    }

    private void isCousinsDFSHelper2(TreeNode root, int x, int y, int depth, TreeNode parent) {
        if(root == null) {
            return;
        }
        if(root.val == x) {
            xLevel = depth;
            xParent = parent;
        } else if(root.val == y) {
            yLevel = depth;
            yParent = parent;
        }
        isCousinsDFSHelper2(root.left, x, y, level+1, root);
        isCousinsDFSHelper2(root.right, x, y, level+1, root);
    }
}

