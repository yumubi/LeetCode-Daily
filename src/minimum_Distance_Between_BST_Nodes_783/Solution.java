package src.minimum_Distance_Between_BST_Nodes_783;

import java.util.Iterator;
import java.util.Stack;

//迭代器
public class Solution {
    class Iter implements Iterator<TreeNode> {
        TreeNode root;
        Stack<TreeNode> stack;

        Iter(TreeNode root) {
            this.root = root;
            stack = new Stack<TreeNode>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            while(true) {
                TreeNode curr = stack.pop();
                if(curr.right != null) {
                    stack.push(curr.right);
                }
                if(curr.left != null) {
                    stack.push(curr.left);
                }
                if(curr.right == null && curr.left == null) {
                    return curr;
                }
            }
        }

        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            Iter iter1 = new Iter(root1);
            Iter iter2 = new Iter(root2);
            while(iter1.hasNext()) {
                if(!iter2.hasNext() || (iter1.next().val != iter2.next().val)) return false;
            }
            if (iter2.hasNext()) return false;
            else return true;
        }
    }
}
