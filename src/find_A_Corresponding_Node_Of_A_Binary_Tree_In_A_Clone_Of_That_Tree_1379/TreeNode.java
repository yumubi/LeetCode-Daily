package src.find_A_Corresponding_Node_Of_A_Binary_Tree_In_A_Clone_Of_That_Tree_1379;
import java.util.*;

public class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x;
      }
      class Solution {
          /**
           *
           * @param original
           * @param cloned
           * @param target
           * @return
           */
          // TODO: 27/11/2022 这一题没懂，java的引用传递应该是直接把引用的地址复制过去了，所以直接 root == target 直接比较地址不就行了嘛
          public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, TreeNode target) {
              TreeNode res = new TreeNode(-1);
              dfs(cloned, res, target);
              return res;
          }
          public void dfs(TreeNode root, TreeNode res, TreeNode target) {
                if(root == null) return;
                if(root ==  target) {
                    res = root;
                    return;
                }
                dfs(root.left, res, target);
                dfs(root.right, res, target);
          }


          /**
           * original树和cloned树的结构，以及所有节点对应的值都是一模一样。基本可以理解成两个"相同"的树（但是内存地址不同，本质上并不一样）。
           * 要在original树中来寻找target，自然想到的就是遍历original树。那么正常的前中后序遍历，应该选择哪一个呢？
           * 因为只要original和target相同，则表示对应的cloned就是我们想要的节点。这个遍历过程，和左右子树并没有关系。所以我们选择前序遍历。
           *
           * 前序遍历递归
           * @param original
           * @param cloned
           * @param target
           * @return
           */
          public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, TreeNode target) {
              if(original == null) return null;
              if(original == target) return cloned;

              TreeNode res = getTargetCopy(original.left, cloned.left, target);
              if(res != null) return res;
              res = getTargetCopy(original.right, cloned.right, target);
              if(res != null) return res;
              return null;
          }

          /**
           * 前序非递归
           *
           * @param original
           * @param cloned
           * @param target
           * @return
           */
          public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, TreeNode target) {
              Deque<TreeNode> stack = new LinkedList<>();
              Deque<TreeNode> clonedStack = new LinkedList<>();
              TreeNode node = original;
              TreeNode cloneTarget = cloned;
              while(node != null || !stack.isEmpty()) {
                 if(node != null) {
                     if(node == target) return cloneTarget;//找到目标，返回
//节点不为空，走左子树
                     stack.push(node);
                     node = node.left;
                     clonedStack.push(cloneTarget);
                     cloneTarget = cloneTarget.left;
                 } else {
                  //节点空了，进入右子树
                     node = stack.pop();
                     node = node.right;
                     cloneTarget = clonedStack.pop();
                     cloneTarget = cloneTarget.right;
                 }
              }
              return null;
          }


          /**
           * 层序遍历
           * @param original
           * @param cloned
           * @param target
           * @return
           */
          public final TreeNode findCopy3(final TreeNode original, final TreeNode cloned, final TreeNode target) {
              Queue<TreeNode> queue = new LinkedList<>();
              Queue<TreeNode> cloneQueue = new LinkedList<>();
              queue.offer(original);
              cloneQueue.offer(cloned);
              TreeNode node1;
              TreeNode node2;
              while(!queue.isEmpty()) {
                  node1 = queue.poll();
                  node2 = cloneQueue.poll();
                  if(target == node1) return node2;
                  if(node1.left != null) {
                      queue.offer(node1.left);
                      cloneQueue.offer(node2.left);
                  }
                  if(node1.right != null) {
                      queue.offer(node1.right);
                      cloneQueue.offer(node2.right);
                  }
              }
              return null;
          }


          /**
           * 双栈同步先序遍历，当在源树找到目标节点时，此时在克隆树中遍历到的节点即为所求。
           * @param original
           * @param cloned
           * @param target
           * @return
           */
          public final TreeNode findCopy4(final TreeNode original, final TreeNode cloned, final TreeNode target) {

              Deque<TreeNode> stack1 = new LinkedList<>();
              Deque<TreeNode> stack2 = new LinkedList<>();
              stack1.push(original);
              stack2.push(cloned);
              TreeNode node1 = new TreeNode(-1);
              TreeNode node2 = new TreeNode(-1);

              while(!stack1.isEmpty()) {
                  node1 = stack1.pop();
                  node2 = stack2.pop();
                  if(node1 == target) break;

                  if(node1.right != null) {
                      stack1.push(node1.right);
                      stack2.push(node2.right);
                  }

                  if(node1.left != null) {
                      stack1.push(node1.left);
                      stack2.push(node2.left);
                  }
              }
                return node2;
          }


          /**
           * 两棵树 分别遍历 存到list 然后判断原树相等的时候的下标 克隆的下标对应的节点就是结果
           */
          ArrayList<TreeNode> origList = new ArrayList<>();
          ArrayList<TreeNode> cpList = new ArrayList<>();
          public final TreeNode getCopy5(final TreeNode original, final TreeNode cloned, final TreeNode target) {
              preOrderOrig(original);
              preOrderCopy(cloned);
              for(int i = 0; i < origList.size(); i++) {
                  if(target == origList.get(i)) return cpList.get(i);
              }
              return null;
          }

          public void preOrderOrig(TreeNode node) {
              if(node == null) return;
              origList.add(node);
              preOrderOrig(node.left);
              preOrderOrig(node.right);
          }

          public void preOrderCopy(TreeNode node) {
              if(node == null) return;
              cpList.add(node);
              preOrderCopy(node.left);
              preOrderCopy(node.right);
          }


          // TODO: 27/11/2022 不懂
          //先遍历一遍原二叉树找到路径，再在相似的路径上寻找克隆的二叉树上寻找指定点
          int p = 0;
          boolean found = false;
          int dir[] = new int[10005];
          public final TreeNode getCopy6(final TreeNode original, final TreeNode cloned, final TreeNode target) {
                TreeNode ans = new TreeNode(-1);
                ans.left = cloned;
                findNode(true, original, target);
                for(int i = 0; i < p; i++) {
                    ans = dir[i] == 1 ? ans.left : ans.right;
              }
                return ans;
          }
          void findNode(boolean isLeft, TreeNode t, TreeNode target) {
              if(found || t == null) return;
              dir[p] = isLeft ? 1 : 0;
              p++;
              if(target == t) {
                  found = true;
                  return;
              }
              findNode(true, t.left, target);
              if(found) return;
              findNode(false, t.right, target);
              if(found) return;
              p--;
          }



      }
}
