package src.second_Minimum_Node_In_BinaryTree_671;
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
           public int findSecondMinimumValue(TreeNode root) {
               List<Integer> res = new ArrayList<>();
               inorder(root, res);
                int minimum = res.get(0);
               for (Integer num : res) {
                   if((int)num > minimum) return num;
               }
               return -1;
           }

           public void inorder(TreeNode root, List<Integer> res) {
                if(root == null) return;
                inorder(root.left, res);
                res.add(root.val);
                inorder(root.right, res);
           }


           /**
            * 深度优先搜索
            * 「如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个」，我们可以知道，对于二叉树中的任意节点 x，x 的值不大于其所有子节点的值，因此：
            * 对于二叉树中的任意节点 x，x的值不大于以 x 为根的子树中所有节点的值。
            * 令 x 为二叉树的根节点，此时我们可以得出结论：
            * 二叉树根节点的值即为所有节点中的最小值。
            * 因此，我们可以对整棵二叉树进行一次遍历。设根节点的值为 rootvalue，我们只需要通过遍历，找出严格大于 rootvalue 的最小值，即为「所有节点中的第二小的值」。
            * 如果第二小的值不存在的话，输出 -1，那么我们可以将 ans 的初始值置为 −1。
            * 在遍历的过程中，如果当前节点的值严格大于 rootvalue 的节点时，那么只要 ans 的值为 −1 或者当前节点的值严格小于 ans，我们就需要对 ans 进行更新。
            * 如果当前节点的值大于等于 ans，那么根据「思路」部分，以当前节点为根的子树中所有节点的值都大于等于 ans，
            * 我们就直接回溯，无需对该子树进行遍历。这样做可以省去不必要的遍历过程。
            */
           int ans;
           int rootvalue;
           public int findSecondMinimunValue2(TreeNode root) {
               ans = -1;
               rootvalue = root.val;
               dfs1(root);
               return ans;
           }

           public void dfs1(TreeNode node) {
               if(node == null) return;
               if(ans != -1 && node.val >= ans) return;
               if(node.val > rootvalue) ans = node.val;
               dfs(node.left);
               dfs(node.right);
           }


//           int ans2 = -1;
//           public int findSecondMinimumValue01(TreeNode root) {
//               dfs01(root, root.val);
//               return ans;
//           }
//           void dfs01(TreeNode root, int cur) {
//               if(root == null) return;
//               if(root.val != cur) {
//                   if(ans == -1) ans = root.val;
//                   else ans = Math.min(ans, root.val);
//                   return;
//               }
//               dfs01(root.left, cur);
//               dfs01(root.right, cur);
//           }





           Set<Integer> set = new HashSet<>();
           public int findSecondMinimumValue3(TreeNode root) {
               dfs(root);
               //bfs(root)
               if(set.size() < 2) return -1;
               //使用经典的两个变量 & 一次遍历的方式，找到次小值，复杂度为 O(n)。
               int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
               for(int i : set) {
                   if(i <= first) {
                       second = first;
                       first = i;
                   } else if(i <= second) {
                       second = i;
                   }
               }
               return second;
           }
           void dfs(TreeNode root) {
               if(root == null) return;
               set.add(root.val);
               dfs(root.left);
               dfs(root.right);
           }
           void bfs(TreeNode root) {
               Deque<TreeNode> d = new ArrayDeque<>();
               d.addLast(root);
               while(!d.isEmpty()) {
                   TreeNode poll = d.pollFirst();
                   set.add(poll.val);
                   if(poll.left != null) d.addLast(poll.left);
                   if(poll.right != null) d.addLast(poll.right);
               }
           }



           public int findSecondMinimumValue4(TreeNode root) {
                    return helper(root,root.val);
           }
           public int helper(TreeNode root, int minVal) {
               if(root == null) return -1;
//如果当前结点值>根节点，那么不用再遍历它的子节点，直接返回该值
               if(root.val > minVal) return root.val;
               //否则，即当前结点值==根节点,则需要在两棵子树找目标值结点
               int l = helper(root.left, minVal);
               int r = helper(root.right, minVal);
               //如果两棵子树均存在大于最小值的节点，那么返回较小的那一个
               if(l != -1 && r != -1) return Math.min(l, r);
               else return Math.max(l, r);//否则，其余情况均返回较大的那一个
           }





           public int findSecondMinimumValue5(TreeNode root) {
               Queue<TreeNode> queue = new LinkedList<>();
               queue.offer(root);
               int min = root.val, ans = Integer.MAX_VALUE;
               boolean ansf = false;
               while(!queue.isEmpty()){
                   int size = queue.size();
                   for (int i = 0; i < size; i++){
                       TreeNode node = queue.poll();
                       if (node.val > min && node.val <= ans) {
                           ans = node.val;
                           ansf = true;
                       }
                       if (node.left != null) {
                           queue.offer(node.left);
                       }
                       if (node.right != null) {
                           queue.offer(node.right);
                       }
                   }
               }
               return ansf ? ans : -1;
           }


           //维护两个值 BFS
           public int firstSecondMinimumValue6(TreeNode root) {
               Deque<TreeNode> deque = new LinkedList<>();
               deque.addLast(root);
               HashSet<Integer> ans = new HashSet<>();
               while(deque.size() > 0) {
                   TreeNode node = deque.pollFirst();
                   ans.add(node.val);
                   if(node.left != null) {
                       deque.addLast(node.left);
                       deque.addLast(node.right);
                   }
               }
               if(ans.size() < 2) return -1;
               List<Integer> res = new ArrayList<>(ans);
               Collections.sort(res);
               return res.get(1);
           }


           // TODO: 23/11/2022
           public int findSecondMinimumValue7(TreeNode root) {
               //必然不存在第二小的值的节点
               if(root == null || root.left == null) return -1;
               //第二小的值存在于左右子树不同于当前节点的最小值
               int left = root.val == root.left.val ? findSecondMinimumValue7(root.left) : root.left.val;
               int right = root.val == root.right.val ? findSecondMinimumValue7(root.right) : root.right.val;
               if(left == -1) return right;
               if(right == -1) return left;
               return Math.min(left, right);

           }












       }
}
