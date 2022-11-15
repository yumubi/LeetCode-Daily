package src.sum_Of_LeftLeaves_404;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * tmd，不会写
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if(root.left == null && root.right == null)
        sum = sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        return sum;
    }

    /**
     * 深度优先搜索
     * 一个节点为「左叶子」节点，当且仅当它是某个节点的左子节点，并且它是一个叶子结点
     * 因此我们可以考虑对整棵树进行遍历，当我们遍历到节点 node 时，
     * 如果它的左子节点是一个叶子结点，那么就将它的左子节点的值累加计入答案。
     * @param root
     * @return
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if(node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if(node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * 广度优先搜索
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves3(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                if(isLeafNode(node.left)) ans += node.left.val;
                else queue.offer(node.left);
            }
            if(node.right != null) {
                if(!isLeafNode(node.left)) queue.offer(node.right);
            }
        }
        return ans;
    }


    /**
     * 递归
     * @param root
     * @return
     */
    public int sumOfLeftLeaves4(TreeNode root) {


//        if(root == null) return 0;
//        int res = 0;
//        //判断节点是否是左叶子节点，如果是则将它的和累计起来
//        if(root.left != null && root.left.left == null && root.left.right == null){
//            res += root.left.val;
//        }
//        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
//    }


        if (root == null) {
            return 0;
        }
        // 取出左节点进行判断
        TreeNode leftNode = root.left;
        // 如果左节点是叶子结点，取出左节点的val和右子树的递归结果相加，作为最终结果返回
        if (leftNode != null && leftNode.left == null && leftNode.right == null) {
            return leftNode.val + sumOfLeftLeaves(root.right);
        }
        // 如果左节点不是叶子结点，分别在左子树和右子树上面递归调用，返回两个子树的递归结果之和
        int leftSum = sumOfLeftLeaves4(root.left);
        int rightSum = sumOfLeftLeaves(root.right);
        return leftSum + rightSum;
    }


    /**
     * bfs练习用
     * @param root
     * @return
     */
    public int sumOfLeftLeaves01(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.addLast(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.removeFirst();
                len--;
                if(node.left != null) {
                    if(node.left.left == null && node.left.right == null) ans += node.left.val;
                    else queue.addLast(node.left);
                }
                if(node.right != null) queue.addLast(node.right);
            }
        }
        return ans;
    }


    /**
     * dfs练习用
     * @param root
     * @return
     */
    public int sumOfLeftLeaves02(TreeNode root) {
        if(root == null) return 0;
        return dfs02(root);
    }
    public int dfs02(TreeNode root) {
        int sum = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) sum += root.left.val;
            else sum += dfs(root.left);
        }
        if(root.right != null) sum += dfs(root.right);
        return sum;
    }


    /**
     * 简化递归练习
     */
    private int ans03 = 0;
    public int sumOfLeftLeaves03(TreeNode root) {
        if(root == null) return 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) ans03 += root.left.val;
        }
        sumOfLeftLeaves03(root.left);
        sumOfLeftLeaves03(root.right);
        return ans03;
    }
}

