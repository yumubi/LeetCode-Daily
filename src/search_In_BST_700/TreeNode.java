package src.search_In_BST_700;

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
           TreeNode target;
           public TreeNode searchBST(TreeNode root, int val) {
                dfs(root, val);
                return target;
           }
           public void dfs(TreeNode root,int val) {
               if(root == null) return;
               if(root.val == val){
                   target = root;
                   return;
               }
               dfs(root.left, val);
               dfs(root.right, val);
           }


           /**
            * 递归
            * 二叉搜索树满足如下性质：
            * 左子树所有节点的元素值均小于根的元素值；
            * 右子树所有节点的元素值均大于根的元素值。
            *
            * 据此可以得到如下算法：
            * 若 root 为空则返回空节点；
            * 若 val=root.val，则返回 root；
            * 若 val<root.val，递归左子树；
            * 若 val>root.val，递归右子树。
            * @param root
            * @param val
            * @return
            */
           public TreeNode searchBST2(TreeNode root, int val) {
               if(root == null) return null;
               if(val == root.val) return root;
               return searchBST2(val < root.val ? root.left : root.right, val);

//               if(root == null || root.val == val) return root;
//               return root.val > val ? searchBST(root.right, val) : searchBST(root.left, val);
           }


           public TreeNode searchBST3(TreeNode root, int val) {
               while(root != null) {
                   if(val == root.val) return root;
                   root = val < root.val ? root.left : root.right;
               }
               return null;

//               while (root != null && root.val != val) {
//                   root = root.val < val ? root.right : root.left;
//               }
//               return root;

           }






       }
}
