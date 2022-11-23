package src.twoSum_Input_Is_a_BST_653;

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

      class Solution{

          public boolean findTarget(TreeNode root, int k) {
              List<Integer> res = new ArrayList<>();
              inorder(root, res);
              for (Integer num : res) {
                  if(k == 2 * num) return false;
                  if(res.contains(k-num)) return true;
              }
              return false;
          }
          public void inorder(TreeNode root, List<Integer> res) {
              if(root == null) return;
              inorder(root.left, res);
              res.add(root.val);
              inorder(root.right, res);
          }


          /**
           * 深度优先搜索+哈希
           * 我们可以使用深度优先搜索的方式遍历整棵树，用哈希表记录遍历过的节点的值。
           * 对于一个值为 x 的节点，我们检查哈希表中是否存在 k−x 即可。
           * 如果存在对应的元素，那么我们就可以在该树上找到两个节点的和为 k；否则，我们将 x 放入到哈希表中。
           * 如果遍历完整棵树都不存在对应的元素，那么该树上不存在两个和为 k的节点。
           */
          Set<Integer> set = new HashSet<Integer>();
          public boolean findTarget2(TreeNode root, int k) {
              if(root == null) return false;
              if(set.contains(k - root.val)) return true;
              set.add(root.val);
              return findTarget2(root.left, k) || findTarget(root.right, k);
          }


          /**
           * 广度优先搜索+哈希表
           * @param root
           * @param k
           * @return
           */
          public boolean findTarget3(TreeNode root, int k) {
              Set<Integer> set = new HashSet<>();
              Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
              queue.offer(root);
              while(!queue.isEmpty()) {
                  TreeNode node = queue.poll();
                  if(set.contains(k - node.val)) return true;
                  set.add(node.val);
                  if(node.left != null) queue.offer(node.left);
                  if(node.right != null) queue.offer(node.right);
              }
              return false;
          }


          /**
           * 中序遍历+双指针
           */
          List<Integer> list = new ArrayList<Integer>();

          public boolean findTarget4(TreeNode root, int k) {
              inorderTraversal(root);
              int left = 0, right = list.size() - 1;
              while(left < right) {
                  if(list.get(left) + list.get(right) == k) return true;
                  if(list.get(left) + list.get(right) < k) left++;
                  else right--;
              }
              return false;
          }

          public void inorderTraversal(TreeNode node) {
              if(node == null) return;
              inorderTraversal(node.left);
              list.add(node.val);
              inorderTraversal(node.right);
          }


          // TODO: 22/11/2022
          public boolean findTarget5(TreeNode root, int k) {
              TreeNode left = root, right = root;
              Deque<TreeNode> leftStack = new ArrayDeque<TreeNode>();
              Deque<TreeNode> rightStack = new ArrayDeque<TreeNode>();
              leftStack.push(left);
              while(left.left != null) {
                  leftStack.push(left.left);
                  left = left.left;
              }
              rightStack.push(right);
              while(right.right != null) {
                  rightStack.push(right.right);
                  right = right.right;
              }
              while(left != right) {
                  if(left.val + right.val == k) return true;
                  if(left.val + right.val < k) left = getLeft(leftStack);
                  else right = getRight(rightStack);
              }
                return false;
          }

          public TreeNode getRight(Deque<TreeNode> stack) {
              TreeNode root = stack.pop();
              TreeNode node = root.left;
              while(node != null) {
                  stack.push(node);
                  node = node.right;
              }
              return root;
          }

          public TreeNode getLeft(Deque<TreeNode> stack) {
              TreeNode root = stack.pop();
              TreeNode node = root.right;
              while(node != null) {
                  stack.push(node);
                  node = node.left;
              }
              return root;
          }


          /**
           * 双指针+中序遍历
           * 解法一中没有利用 BST 特性，利用 BST 中序遍历有序的特性，我们可以实现类似「双指针」的效果。
           * 起始先让 BST 的最左链和最右链完全入栈，此时栈顶元素为 BST 中的最小值和最大值，
           * 分别使用 l 和 r 充当指针代指，根据两指针指向的节点值之和与 k 的大小关系来指导如何让 l 和 r 移动，
           * l 的移动过程其实就是找下一个比 l.val 更大的值，而 r 的移动过程其实就是找下一个比 r.val 更小的值。

           * @param root
           * @param k
           * @return
           */
          public boolean findTargets6(TreeNode root, int k) {
              Deque<TreeNode> ld = new ArrayDeque<>(), rd = new ArrayDeque<>();
              TreeNode tmp = root;
              while(tmp != null) {
                  ld.addLast(tmp);
                  tmp = tmp.left;
              }
              tmp = root;
              while(tmp != null) {
                  rd.addLast(tmp);
                  tmp = tmp.right;
              }
              TreeNode l = ld.peekLast(), r = rd.peekLast();
              while(l.val < r.val) {
                  int t = l.val + r.val;
                  if(t == k) return true;
                  if(t < k) l = getNext(ld,true);
                  else r = getNext(rd, false);
              }
              return false;
          }

          TreeNode getNext(Deque<TreeNode> d, boolean isLeft) {
              TreeNode node = isLeft ? d.pollLast().right : d.pollLast().left;
              while(node != null) {
                  d.addLast(node);
                  node = isLeft ? node.left : node.right;//todo 这一点不太懂
              }
              return d.peekLast();
          }

      }
}
