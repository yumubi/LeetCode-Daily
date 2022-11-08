package src.same_Tree_100;


import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    class Solution {


        /**
         * my answer,wrong
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == q) return true;
            List<Integer> list1 = inorderTraversal(p);
            List<Integer> list2 = inorderTraversal(q);
            return list1 == list2;
        }


        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res  = new ArrayList<>();
            inorder(root, res);
            return res;
        }

        public void inorder(TreeNode root, List<Integer> res) {
            if(root == null) return;
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }


        /**
         * recursion
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTree2(TreeNode p, TreeNode q) {

            if(p == null && q == null) return true;
            if(p == null) return false;
            if(q == null) return false;
            if(p.val == q.val)
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else return false;
        }


        /**
         * 非递归先序遍历
         * @param p
         * @param q
         * @return
         */

        public boolean isSameTreePre(TreeNode p, TreeNode q) {
            if(p == null && q == null) return false;
            if(p == null) return false;
            if(q == null) return false;

            Stack<TreeNode> stackP = new Stack<>();
            stackP.add(p);
            Stack<TreeNode> stackQ = new Stack<>();
            stackQ.add(q);

            while(!stackP.isEmpty() || !stackQ.isEmpty()) {
                TreeNode pNode = stackP.pop();
                TreeNode qNode = stackQ.pop();

                if(pNode == null && qNode == null) continue;
                if(pNode == null) return false;
                if(qNode == null) return false;
                if(pNode.val != qNode.val) return false;
                else {
                    stackP.add(pNode.right);
                    stackQ.add(pNode.right);
                    stackP.add(pNode.left);
                    stackQ.add(pNode.left);
                }
            }
            return true;
        }









        /**
         * 非递归中序遍历判断两棵二叉树是否相等
         * 左根右
         *
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTreeMid(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null) return false;
            if (q == null) return false;
            Stack<TreeNode> stackP = new Stack<>();
            Stack<TreeNode> stackQ = new Stack<>();
            while (p != null || q != null || !stackP.isEmpty() || !stackQ.isEmpty()) {
                if (p != null && q != null) {
                    stackP.add(p);
                    stackQ.add(q);
                    p = p.left;
                    q = q.left;
                } else if (q == null && p == null) {
                    p = stackP.pop();
                    q = stackQ.pop();
                    if (p.val != q.val) {
                        return false;
                    }
                    p = p.right;
                    q = q.right;
                } else {
                    return false;
                }
            }
            return true;
        }


        // TODO: 2022/11/8 待研究
        /**
         * 二叉树后序遍历判断两颗树相等
         * 左右根
         * 双栈解法
         *
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTreeEnd1(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null) return false;
            if (q == null) return false;
            Stack<TreeNode> stackP = new Stack<>();
            Stack<TreeNode> stackPTmp = new Stack<>();
            Stack<TreeNode> stackQ = new Stack<>();
            Stack<TreeNode> stackQTmp = new Stack<>();
            while (p != null || q != null || !stackPTmp.isEmpty() || !stackQTmp.isEmpty()) {
                if (p != null && q != null) {
                    stackP.add(p);
                    stackPTmp.add(p);
                    stackQ.add(q);
                    stackQTmp.add(p);
                    p = p.right;
                    q = q.right;
                } else if (p == null && q == null) {
                    p = stackPTmp.pop();
                    p = p.left;
                    q = stackQTmp.pop();
                    q = q.left;
                } else {
                    return false;
                }
            }

            while (!stackP.isEmpty() && !stackQ.isEmpty()) {
                if (stackP.pop().val != stackQ.pop().val) {
                    return false;
                }
            }

            return true;
        }


        // TODO: 2022/11/8 待研究

        /**
         * 二叉树后序遍历单栈实现对比判断
         *
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTreeEnd2(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null) return false;
            if (q == null) return false;
            Stack<TreeNode> stackP = new Stack<>();
            stackP.add(p);
            TreeNode pPre = null;
            Stack<TreeNode> stackQ = new Stack<>();
            stackQ.add(q);
            TreeNode qPre = null;
            while (!stackP.isEmpty() || !stackQ.isEmpty()) {
                p = stackP.peek();
                q = stackQ.peek();
                if (((pPre != null && (p.left == pPre || p.right == pPre)) || (p.left == null && p.right == null)) &&
                        ((qPre != null && (q.left == qPre || q.right == qPre)) || (q.left == null && q.right == null))) {
                    if (p.val != q.val) {
                        return false;
                    }
                    pPre = p;
                    qPre = q;
                    stackP.pop();
                    stackQ.pop();
                } else {
                    if (p.right != null && q.right != null) {
                        stackP.add(p.right);
                        stackQ.add(q.right);
                    } else if (p.right != null || q.right != null) {
                        return false;
                    }

                    if (p.left != null && q.left != null) {
                        stackP.add(p.left);
                        stackQ.add(q.left);
                    } else if (p.left != null || q.left != null) {
                        return false;
                    }
                }
            }
            return true;
        }


        /**
         * 深度优先搜索
         * @param p
         * @param q
         * @return
         */



        //时间复杂度：O(min(m,n))，其中 mm 和 nn 分别是两个二叉树的节点数。对两个二叉树同时进行深度优先搜索，
        // 只有当两个二叉树中的对应节点都不为空时才会访问到该节点，因此被访问到的节点数不会超过较小的二叉树的节点数。
        //
        //空间复杂度：O(min(m,n))，其中 mm 和 nn 分别是两个二叉树的节点数。空间复杂度取决于递归调用的层数，
        // 递归调用的层数不会超过较小的二叉树的最大高度，最坏情况下，二叉树的高度等于节点数。

        public boolean isSameTree01(TreeNode p, TreeNode q) {

            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }

        }


        /**
         * 广度优先搜索
         */
        public boolean isSameTree02(TreeNode p, TreeNode q){
            if(p == null && q == null) return true;
            else if(p == null || q == null) return false;

            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            queue1.offer(p);
            queue2.offer(q);

            while(!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();
                if(node1.val != node2.val) return false;
                TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;

                if(left1 == null ^ left2 == null) return false;
                if(right1 == null ^ right2 == null) return false;
                if(left1 != null) queue1.offer(left1);
                if(right1 != null) queue1.offer(right1);
                if(left2 != null) queue1.offer(left2);
                if(right2 != null) queue1.offer(right2);
            }

            return queue1.isEmpty() && queue2.isEmpty();
        }



        public boolean isSameTree03(TreeNode p, TreeNode q) {
            Queue<TreeNode> tmpQueue = new LinkedList<TreeNode>();
            tmpQueue.offer(p);
            tmpQueue.offer(q);
            while(!tmpQueue.isEmpty()) {
                p = tmpQueue.poll();
                q = tmpQueue.poll();
                if(p == null && q == null) continue;
                if(p == null || q == null || p.val != q.val) return false;
                tmpQueue.offer(p.left);
                tmpQueue.offer(q.left);

                tmpQueue.offer(p.right);
                tmpQueue.offer(q.right);
            }
            return true;
        }





    }
}
