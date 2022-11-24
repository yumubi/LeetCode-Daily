package src.merge_Two_Binary_Trees_617;

import java.util.LinkedList;
import java.util.Queue;

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
          // TODO: 23/11/2022 完全没思路，放弃了
          /**
           *深度优先搜索
           *
           * 时间复杂度：O(min(m,n))
           * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点个数。空间复杂度取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的最大高度，最坏情况下，二叉树的高度等于节点数。
           *
           *
           * 可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。
           * 两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。
           * 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
           * 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
           * 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和，此时需要显性合并两个节点。
           * 对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。
           * @param t1
           * @param t2
           * @return
           */
          public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
              if(t1 == null) return t2;
              if(t2 == null) return t1;
              TreeNode merged = new TreeNode(t1.val + t2.val);
              merged.left = mergeTrees(t1.left, t2.left);
              merged.right = mergeTrees(t1.right, t2.right);
              return merged;
          }


          /**
           * 广度优先搜索
           * 也可以使用广度优先搜索合并两个二叉树。首先判断两个二叉树是否为空，如果两个二叉树都为空，则合并后的二叉树也为空，
           * 如果只有一个二叉树为空，则合并后的二叉树为另一个非空的二叉树。
           * 如果两个二叉树都不为空，则首先计算合并后的根节点的值，然后从合并后的二叉树与两个原始二叉树的根节点开始广度优先搜索，
           * 从根节点开始同时遍历每个二叉树，并将对应的节点进行合并。
           * 使用三个队列分别存储合并后的二叉树的节点以及两个原始二叉树的节点。
           * 初始时将每个二叉树的根节点分别加入相应的队列。每次从每个队列中取出一个节点，判断两个原始二叉树的节点的左右子节点是否为空。
           * 如果两个原始二叉树的当前节点中至少有一个节点的左子节点不为空，则合并后的二叉树的对应节点的左子节点也不为空。对于右子节点同理。
           * 如果合并后的二叉树的左子节点不为空，则需要根据两个原始二叉树的左子节点计算合并后的二叉树的左子节点以及整个左子树。考虑以下两种情况：
           * 如果两个原始二叉树的左子节点都不为空，则合并后的二叉树的左子节点的值为两个原始二叉树的左子节点的值之和，
           * 在创建合并后的二叉树的左子节点之后，将每个二叉树中的左子节点都加入相应的队列；
           * 如果两个原始二叉树的左子节点有一个为空，即有一个原始二叉树的左子树为空，则合并后的二叉树的左子树即为另一个原始二叉树的左子树，
           * 此时也不需要对非空左子树继续遍历，因此不需要将左子节点加入队列。
           * 对于右子节点和右子树，处理方法与左子节点和左子树相同
           *
           * 时间复杂度：O(min(m,n))
           * 间复杂度：O(min(m,n))，其中 mm 和 nn 分别是两个二叉树的节点个数。空间复杂度取决于队列中的元素个数，队列中的元素个数不会超过较小的二叉树的节点数。
           * @param t1
           * @param t2
           * @return
           */
          public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
              if(t1 == null) return t2;
              if(t2 == null) return t1;
              TreeNode merged = new TreeNode(t1.val + t2.val);
              Queue<TreeNode> queue = new LinkedList<TreeNode>();
              Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
              Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
              queue1.offer(t1);
              queue2.offer(t2);
              while(!queue1.isEmpty() && !queue2.isEmpty()) {
                 TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
                 TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
                 if(left1 != null && left2 != null) {
                     if(left1 != null && left2 !=null) {
                         TreeNode left = new TreeNode(left1.val + left2.val);
                         node.left = left;
                         queue.offer(left);
                         queue1.offer(left1);
                         queue2.offer(left2);
                     } else if(left1 != null) {
                         node.left = left1;   //感觉精髓在这里，只要一个为空，另一个直接作为子树插入到合并后的二叉树中了，就没必要在入栈了
                         //节点数小的树搜索完之后后面就不需要搜索了，后面就直接是节点数多的树照搬过去就好了
                     } else if(left2 != null) {
                         node.left = left2;
                     }
                 }
                 
                 if(left1 != null && left2 != null) {
                     if(left1 != null && left2 !=null) {
                         TreeNode right = new TreeNode(left1.val + left2.val);
                         node.right = right;
                         queue.offer(right);
                         queue1.offer(left1);
                         queue2.offer(left2);
                     } else if(left1 != null) {
                         node.right = left1;
                     } else if(left2 != null) {
                         node.right = left2;
                     }
                 }


              }

              return merged;
          }



          public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
              ///如果 t1和t2中，只要有一个是null，函数就直接返回
              if(t1 == null || t2 == null) return t1 == null ? t2 : t1;
              Queue<TreeNode> queue = new LinkedList<>();
              queue.offer(t1);
              queue.offer(t2);
              while(queue.size() > 0) {
                  TreeNode r1 = queue.poll();
                  TreeNode r2 = queue.poll();
                  r1.val += r2.val;
                  //如果r1和r2的左子树都不为空，就放到队列中
                  //如果r1的左子树为空，就把r2的左子树挂到r1的左子树上
                  if(r1.left != null && r2.left != null) {
                      queue.offer(r1.left);
                      queue.offer(r2.left);
                  } else if(r1.left == null) r1.left = r2.left;

                  //对于右子树也是一样的
                 if(r1.right != null && r2.right != null) {
                      queue.offer(r1.right);
                      queue.offer(r2.right);
                  } else if(r1.right == null) r1.right = r2.right;
              }
              return t1;
          }
      }
}
