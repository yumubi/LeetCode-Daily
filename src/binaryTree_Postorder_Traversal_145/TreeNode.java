package src.binaryTree_Postorder_Traversal_145;


import org.junit.Test;

import java.util.*;

public class TreeNode {
    int val;
   TreeNode left;
   TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val,TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    class Solution {

        /**
         * ac了
         * @param root
         * @return
         */
        public List<Integer> postorderTravsersal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res) {
            if(root == null) return;
            helper(root.left, res);
            helper(root.right, res);
            res.add(root.val);
        }

        // TODO: 2022/11/13 菜鸡没看懂
        public List<Integer> postorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if(root == null) return res;
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode prev= null;//上一个访问节点
            //主要思想
            //由于再某棵子树访问完成以后，接着就要回溯到其父节点，因此可以使用prev来记录访问历史
            //在回溯到父节点时，可以由此来判断上一个访问的节点是否为右子树
            while(root != null || !stack.isEmpty()) {
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }
                //从栈中弹出来的元素，左子树一定是访问完了的
                root = stack.pop();
                //现在需要确定的是是否有右子树或者右子树是否访问过
                //如果没有右子树或者右子树已经访问玩了，也就是上一个访问的节点是右子节点时
                //说明可以访问当前节点
                if(root.right == null || root.right == prev) {
                    res.add(root.val);//访问
                    //更新历史访问记录，这样回溯的时候父节点可以由此判断右子树是否访问完成
                    prev = root;
                    root =null;//置空返回父节点
                } else {
                    //右子树还没访问，没访问到根的时候，再次入栈，先访问右子树
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }


        // TODO: 2022/11/13 Morris遍历





//利用双向链表的addFirst，对外部次序和内含次序进行同时翻转，同样会得到一种非常”优雅”的解法，结构简单明晰，甚至还有点好背（狗头）。但是，它并没有真正实现“遍历”——仔细看会发现，
// 该算法其实在使用一种异构的前序遍历：“中->右->左”，而非传统意义上的“中->左->右”，而这种异构也正是他的第一次反转。而第二次反转就在输出序列上。
//所以本质上，这是一个“前序遍历”，而不是所谓的“后序遍历”。只有当你的各个节点以“左->右->中”的次序依次出现在迭代的loop当中时，它才是真正的后序遍历

    public List<Integer> postorderTravsersal3(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> s = new Stack<>();
            Set<TreeNode> seen = new HashSet<>();
            while(root != null || !s.isEmpty()) {
                if(root == null && seen.contains(s.peek())) {
                    ans.add(s.pop().val);
                } else if(root == null) {
                    seen.add(s.peek());
                    root = s.peek().right;
                } else {
                    s.push(root);
                    root = root.left;
                }
            }
            return ans;
        }



        //////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * 统一一下
         * @param root
         * @return
         */
        //前序
        public static List<Integer> preOrder(TreeNode root){
            List<Integer> list = new ArrayList();
            Stack<TreeNode> stack = new Stack();
            TreeNode cur = root;
            while(cur!=null || !stack.isEmpty()){
                //一直往左压入栈
                while(cur!=null){
                    list.add(cur.val);
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                cur = cur.right;
            }
            return list;
        }

        //中序
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root == null){
                return new ArrayList();
            }
            List<Integer> list = new ArrayList();
            Stack<TreeNode> stack = new Stack();
            TreeNode cur = root;
            while(cur != null || !stack.isEmpty()){
                while(cur!=null){
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
            return list;
        }


        //后序遍历，非递归
        public static List<Integer> postOrder(TreeNode root){
            Stack<TreeNode> stack = new Stack<>();
            List<Integer> list = new ArrayList<>();
            TreeNode cur = root;
            TreeNode p = null;//用来记录上一节点
            while(!stack.isEmpty() || cur != null){
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.peek();
//            后序遍历的过程中在遍历完左子树跟右子树cur都会回到根结点。所以当前不管是从左子树还是右子树回到根结点都不应该再操作了，应该退回上层。
//            如果是从右边再返回根结点，应该回到上层。
                //主要就是判断出来的是不是右子树，是的话就可以把根节点=加入到list了
                if(cur.right == null || cur.right == p){
                    list.add(cur.val);
                    stack.pop();
                    p = cur;
                    cur = null;
                }else{
                    cur = cur.right;
                }

            }
            return list;
        }












        ////////////////////////////////////////////////////////////////////

        /**
         * 双栈解法
         * @param root
         * @return
         */
        public List<Integer> postorderTravsersal6(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if(root == null) return res;
            LinkedList<TreeNode> s1 = new LinkedList<>();
            LinkedList<TreeNode> s2 = new LinkedList<>();
            s1.push(root);
            while(!s1.isEmpty()) {
                TreeNode node = s1.pop();
                s2.push(node);
                if(node.left != null) s1.push(node.left);
                if(node.right != null) s1.push(node.right);
            }
            while(!s2.isEmpty()) {
                res.add(s1.pop().val);
            }
            return res;
        }




        /**
         * .直接按先序的来，再进行反转
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal7(TreeNode root) {
            List<Integer> res=new ArrayList<Integer>();
            if(root==null) return res;
            LinkedList<TreeNode> s=new LinkedList<>();
            s.push(root);
            while(!s.isEmpty()){
                TreeNode node=s.pop();
                res.add(node.val);
                if(node.left!=null){
                    s.push(node.left);
                }
                if(node.right!=null){
                    s.push(node.right);
                }
            }

            Collections.reverse(res);
            return res;
        }


        /**
         * 套用模板，然后反转
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal8(TreeNode root) {
            List<Integer> res=new ArrayList<Integer>();
            if(root==null) return res;
            ArrayDeque<TreeNode> s=new ArrayDeque<>();
            TreeNode node=root;
            while(!s.isEmpty()||node!=null){
                if(node!=null){
                    s.push(node);
                    res.add(node.val);
                    node=node.right;
                }
                else{
                    node=s.pop();
                    node=node.left;
                }
            }
            Collections.reverse(res);
            return res;
        }


        /**
         * 炫技版迭代，只用一个栈，主要思路是用一个节点记录上次添加到list的节点，
         * 如果当前节点的左右子节点都都没有加，按照后序遍历，应该先加左，
         * 如果左加了，右没加，接着加右，最后加当前节点
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal10(TreeNode root) {
            List<Integer> res=new ArrayList<Integer>();
            if(root==null) return res;
            LinkedList<TreeNode> s=new LinkedList<>();
            s.push(root);
            TreeNode p=root;
            while(!s.isEmpty()){
                TreeNode node=s.peek();
                if(node.left!=null&&p!=node.left&&p!=node.right){
                    s.push(node.left);
                }
                else if(node.right!=null&&p!=node.right){
                    s.push(node.right);
                }
                else{
                    res.add(s.pop().val);
                    p=node;
                }
            }
            return res;
        }


    }



}
