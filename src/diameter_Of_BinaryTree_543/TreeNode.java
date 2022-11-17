package src.diameter_Of_BinaryTree_543;

import com.sun.source.tree.Tree;

public class TreeNode {
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

    class Solution {

        /**
         * 爆0了
         * @param root
         * @return
         */
        public int diameterOfBinaryTree(TreeNode root) {
                if(root == null) return 0;
                int max = 0;
                helper(root, max);
                return max;
            }
            public void helper(TreeNode root, int max) {
                if(root == null) return;
                int left = depth(root.left);
                int right = depth(root.right);
                int diameter = left + right;
                if(diameter > max) max = diameter;
                helper(root.left, max);
                helper(root.right, max);
            }


            public int depth(TreeNode root) {
                if(root == null) return 0;
                int leftDepth = depth(root.left);
                int rightDepth = depth(root.right);
                return Math.max(leftDepth, rightDepth) + 1;
            }


        /**
         * 深度优先搜索
         * 假设我们知道对于该节点的左儿子向下遍历经过最多的节点数 L （即以左儿子为根的子树的深度）
         * 和其右儿子向下遍历经过最多的节点数 R（即以右儿子为根的子树的深度），
         * 那么以该节点为起点的路径经过节点数的最大值即为 L+R+1 。
         * 我们记节点 node 为起点的路径经过节点数的最大值为 dnode那么二叉树的直径就是所有节点 dnode的最大值减一。
         */
        int ans;
        public int diameterOfBianryTree(TreeNode root) {
            ans = 1;
            depth2(root);
            return ans - 1;
        }
        public int depth2(TreeNode node) {
            if(node == null) return 0;
            int L = depth2(node.left);//左子树的深度
            int R = depth2(node.right);//右子树的深度
            ans = Math.max(ans, L + R + 1);//计算d_node即L+R+1并更新ans
            return Math.max(L, R) + 1;//返回该节点为根的子树的深度
        }


//         省去了+1，-1
//        int maxd=0;
//        public int diameterOfBinaryTree(TreeNode root) {
//            depth(root);
//            return maxd;
//        }
//        public int depth(TreeNode node){
//            if(node==null){
//                return 0;
//            }
//            int Left = depth(node.left);
//            int Right = depth(node.right);
//            maxd=Math.max(Left+Right,maxd);//将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
//            return Math.max(Left,Right)+1;//返回节点深度
//        }

    }
}
