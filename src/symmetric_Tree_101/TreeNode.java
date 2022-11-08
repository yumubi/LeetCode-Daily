package src.symmetric_Tree_101;

import java.util.*;

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
            * 不行，不会写
            * @param root
            * @return
            */
           public boolean isSymmetric(TreeNode root) {
               Deque deque =  traverse(root);
               while(deque.peek() != null) {
                      int left = (Integer) deque.pollFirst();
                      int right = (Integer) deque.pollLast();
                   if(left != right) return false;
               }
               return true;
           }

           public Deque<Integer> traverse(TreeNode root){
               Deque<Integer> res = new LinkedList<>();
                inorder(root, res);
                return res;
           }


           public void inorder(TreeNode root ,Deque res) {
               if(root == null) return;
               inorder(root.left, res);
               res.offerFirst(root.val);
               inorder(root.right, res);
           }


           /**
            * 递归
            如果同时满足下面的条件，两个树互为镜像：
            1.它们的两个根结点具有相同的值
            2.每个树的右子树都与另一个树的左子树镜像对称

            //
            时间复杂度：这里遍历了这棵树，渐进时间复杂度为 O(n)。
            空间复杂度：这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 n，故渐进空间复杂度为 O(n)
            * @param root
            * @return
            */

           public boolean isSymmetric1(TreeNode root) {
               return check1(root, root);
           }

           public boolean check1(TreeNode p, TreeNode q) {
               if(p == null && q == null) return true;
               if(p == null || q == null) return false;

               return p.val == q.val && check1(q.left, q.right) && check1(p.right, q.left);
           }


           /**
            * 迭代
            *
            //首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。初始化时我们把根节点入队两次。
            每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像）
            ，然后将两个结点的左右子结点按相反的顺序插入队列中。当队列为空时，
            或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。

            * @param root
            * @return
            */

           public boolean isSymmetric2(TreeNode root) {
               return check2(root, root);
           }

           public boolean check2(TreeNode u, TreeNode v) {
               Queue<TreeNode> q = new LinkedList<>();
               q.offer(u);
               q.offer(v);
               while(!q.isEmpty()) {
                   u = q.poll();
                   v = q.poll();
                   if(u == null && v == null) continue;

                   if( (u == null || v == null) || (u.val != v.val) ) return false;

                   q.offer(u.left);
                   q.offer(v.right);

                   q.offer(u.right);
                   q.offer(v.left);
               }
               return true;
           }





           /**
            * 错误的中序遍历
            * 用例[1,2,2,2,null,2]过不了
            */

           List<Integer> list=new ArrayList<>();
           public boolean isSymmetric3(TreeNode root) {
               orderTree(root);
               int i=0;
               int j=list.size()-1;
               while(i<j){
                   if(list.get(i)!=list.get(j))
                   {
                       return false;
                   }
                   i++; j--;
               }
               return true;
           }
           void orderTree(TreeNode root) {
               if(root==null){
                   return ;
               }
               orderTree(root.left);
               list.add(root.val);
               orderTree(root.right);
           }


           /**
            * 可以用中序遍历，记录下层级就行了
            */
           List<Integer> list1 = new ArrayList<>();
           public boolean isSymmetric4(TreeNode root) {
               dfs1(root,0);
               int left = 0;
               int right = list1.size()-1;
               while(left < right){
                   int i1 = list1.get(left);
                   int i2 = list1.get(right);
                   if(i1 != i2){
                       return false;
                   }
                   left++;
                   right--;
               }
               return true;
           }

           void dfs1(TreeNode node,int i){
               if(node == null){
                   list1.add(101+i);
                   return;
               }
               dfs1(node.left,i+1);
               list1.add(node.val);
               dfs1(node.right,i+1);

           }



       }

}





