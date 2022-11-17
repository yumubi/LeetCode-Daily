package src.binaryTree_Tilt_563;

import java.util.Deque;
import java.util.LinkedList;

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
            * 我错了
            * @param root
            * @return
            */
           public int findTilt(TreeNode root) {
               if(root.right == null && root.left == null) return 0;
               else if(root.left == null) return findTilt(root.right) + Math.abs(root.right.val);
               else if(root.right == null) return findTilt(root.left) + Math.abs(root.left.val);
               else return Math.abs(root.left.val - root.right.val) + findTilt(root.left) + findTilt(root.right);
           }


           /**
            * 三叶tql
            * @param root
            * @return
            */
               public int findTilt2(TreeNode root) {
                   if (root == null) return 0;
                   return findTilt2(root.left) + findTilt2(root.right) + Math.abs(getSum(root.left) - getSum(root.right));
               }
           // 求root为根的树节点总和
               int getSum(TreeNode root) {
                   if (root == null) return 0;
                   return getSum(root.left) + getSum(root.right) + root.val;
               }




           /**
            * 上述解法之所以为 O(n^2)的时间复杂度，是因为我们将「计算子树坡度」和「计算子树权值和」两个操作分开进行。
            *事实上，我们可以在计算子树权值和的时候将坡度进行累加，从而将复杂度降为 O(n)
            *
            *
            *
            * 根据题意，我们需要累计二叉树中所有结点的左子树结点之和与右子树结点之和的差的绝对值。因此，我们可以使用深度优先搜索，在
            * 遍历每个结点时，累加其左子树结点之和与右子树结点之和的差的绝对值，并返回以其为根结点的树的结点之和。
            * 具体地，我们实现算法如下：
            * 从根结点开始遍历，设当前遍历的结点为node；
            * 遍历node 的左子结点，得到左子树结点之和sum_left；遍历 node 的右子结点，得到右子树结点之和 sum_right；
            * 将左子树结点之和与右子树结点之和的差的绝对值累加到结果变量 ans；
            * 返回以 node 作为根结点的树的结点之和sum_left+sum_right+node.val。
            */
           int ans;
           public int findTilt3(TreeNode root) {
               dfs(root);
               return ans;
           }
           int dfs(TreeNode root) {
               if (root == null) return 0;
               int l = dfs(root.left), r = dfs(root.right);
               ans += Math.abs(l - r);
               return l + r + root.val;
           }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //三层递归
public int findTilt01(TreeNode root) {
    if(root==null) return 0;
    return dfs2(root)+findTilt01(root.left)+findTilt01(root.right);
}

           private int dfs2(TreeNode root){
               if(root==null) return 0;
               return Math.abs(sum(root.left)-sum(root.right));
           }

           private int sum(TreeNode root){
               if(root==null) return 0;
               return root.val+sum(root.left)+sum(root.right);

           }


           //优化到一层递归
           int res=0;

           public int findTilt02(TreeNode root) {
               if(root==null) return 0;
               dfs03(root);
               return res;
           }

           //计算整棵树所有节点之和，过程中顺便求坡度之和
           private int dfs03(TreeNode root){
               if(root==null) return 0;
               int l=dfs(root.left);
               int r=dfs(root.right);
               res+=Math.abs(l-r);
               return l+r+root.val;
           }


           /**
            * 后序遍历的迭代比较麻烦，主要在于如何记录树 left, right 孩子已处理完，加上退栈方式和前序，
            * 中序不大一样，当树的 leaf 或 left ,right child 都处理完，才退栈
            * 下面用 list 来记录压栈顺序，然后遍历 list 来处理节点和以及坡度计算
            * @param root
            * @return
            */
               public int findTilt03(TreeNode root) {

                   // 节点为空时，树坡度为 0
                   if (root == null) {
                       return 0;
                   }

                   // 定义迭代 stk
                   Deque<TreeNode> stk = new LinkedList<>();

                   // 记录整棵树入栈的顺序, 这里注意了
                   LinkedList<TreeNode> trees = new LinkedList<>();

                   // 先将 root 节点入栈
                   stk.push(root);

                   // 栈不为空，处理
                   // 将整棵树所有节点按 left, right 的顺序入栈
                   while (!stk.isEmpty()) {

                       // 首先从栈中取出当前的树节点
                       TreeNode treeNode = stk.pop();

                       // 将从栈中取出的树节点，放入到 trees 中，
                       // 后添加的放在首元素，后面的节点位置相应的后移
                       trees.addFirst(treeNode);

                       // 先将当前节点的子树 left -> right 入栈（后续迭代）
                       // left 不为空才有意义
                       if (treeNode.left != null) {
                           // left 压栈
                           stk.push(treeNode.left);
                       }

                       // right 不为空才有意义
                       if (treeNode.right != null) {

                           // right 入栈
                           stk.push(treeNode.right);

                       }
                   }

                   // 到这里为栈，栈功成身退，后面只需要处理每颗树的 val 即可， 从整棵树的底部到顶部依次进行处理

                   // 定义总坡度变量,也就是结果
                   int res = 0;
                   // 遍历 trees ,这时候的 treeNode，
                   for (TreeNode tree : trees) {
                       // 当前树的 left 树的 val, 为空时，默认 0， 不为空时，取其值
                       int left = tree.left == null ? 0 : tree.left.val;

                       // 当前树的 right 树的 val，为空时，默认 0， 不为空，取其值
                       int right = tree.right == null ? 0 : tree.right.val;

                       // 将当前树的节点 val, 赋值为当前数所有节点和
                       // 与递归方法中，LRM 方法返回值的用法一样的效果，没有区别，在 LRM 中也可以这样处理
                       // 当这里不能根递归方法中处理一样返回，这里没有回调这些返回值的地方
                       // 将节点值进行更新，称为后序遍历处理此问题的思路和方法
                       tree.val = left + right + tree.val;

                       // 回到题目，利用left, right 总值计算坡度值，并将当前树的坡度值添加到 res 中
                       res += Math.abs(left - right);

                   }

                   // 返回结果值即可
                   return res;
               }



       }
}
