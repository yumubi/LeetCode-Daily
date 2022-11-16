package src.find_Mode_In_BST_501;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
         * 为了找到一个众数数组，也太麻烦了，这代码太丑了
         * @param root
         * @return
         */
          public int[] findMode(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                preorder(root, res);
              HashMap<Integer, Integer> map = new HashMap<>();
              for (Integer num: res) {
                 map.put(num, map.getOrDefault(num, 0)+1);
              }
              int max = 0;
              for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                  if(entry.getValue() > max) max = entry.getValue();
              }
              ArrayList<Integer> result = new ArrayList<>();
              for( Map.Entry<Integer, Integer> entry : map.entrySet()) {
                  if(entry.getValue() == max)  result.add(entry.getKey());
              }
                int[] a = result.stream().mapToInt(Integer :: intValue).toArray();
              return a;

          }

          public void preorder(TreeNode root, List<Integer> res) {
              if(root == null) return;
              res.add(root.val);
              preorder(root.left, res);
              preorder(root.right, res);
          }


//        public int[] findMode(TreeNode root) {
//            List<Integer>list=new LinkedList<>();
//            Map<Integer,Integer> map=new HashMap<>();
//            inOrderTraversal(root,map);
//            List<Integer>reslist=new LinkedList<>();
//            List<Map.Entry<Integer,Integer>> list1 = new ArrayList(map.entrySet());
//            Collections.sort(list1, (o1, o2) -> (o2.getValue() - o1.getValue()));
//            int maxValue=list1.get(0).getValue();
//            for(Map.Entry<Integer,Integer> entry:map.entrySet()){
//                if(entry.getValue()==maxValue){
//                    reslist.add(entry.getKey());
//                }
//            }
//            return reslist.stream().mapToInt(Integer::intValue).toArray();
//        }
//        public void inOrderTraversal(TreeNode root,Map<Integer,Integer>map){
//            if(root==null)return;
//            inOrderTraversal(root.left,map);
//            map.put(root.val,map.getOrDefault(root.val,0)+1);
//            inOrderTraversal(root.right,map);
//        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * 中序遍历
         *
         *一棵二叉搜索树的中序遍历序列是一个非递减的有序序列
         * 时间复杂度：O(n)。即遍历这棵树的复杂度。
         * 空间复杂度：O(n)。即递归的栈空间的空间代价。
         * 顺序扫描中序遍历
         * base记录当前的数字
         * count记录当前数字重复的次数
         * maxCount维护已经扫描过的数字当中出现最多的那个数字的出现次数
         * answer数组记录出现的众数
         *
         * 每次扫描到一个新元素
         *      首先更新base和count
         *          如果该元素和base相同，那么count自增1
         *          否则将base更新为当前数字，count复位为1
         *      然后更新maxCount
         *          如果count == maxCount 说明当前这个数字（base)出现的次数等于当前众数出现的次数，将base加入到answer数组
         *          如果count > maxCount,那么说明当前这个数字（base)出现的次数大于当前众数出现的次数，因此需要将maxCount更新为
         *          count,清空answer数组后将base加入到answer数组
         * 把这个过程写成一个update函数，这样在寻找出现次数最多的数字时就可以省去一个哈希表带来的空间消耗
         *
         * 然后考虑不存储这个中序遍历序列，在递归中进行中序遍历的过程中，访问了某个节点的时候直接使用update函数，可以省去中序遍历序列的空间
         */

        List<Integer> answer = new ArrayList<Integer>();
          int base, count, maxCount;

          public int[] findMode2(TreeNode root) {
                dfs(root);
                int[] mode = new int[answer.size()];
                for(int i = 0; i < answer.size(); ++i) mode[i] = answer.get(i);
                return mode;
          }

          public void dfs(TreeNode root) {
              if( root == null) return;
              dfs(root.left);
              update(root.val);
              dfs(root.right);
          }

          public void update(int x){
                if(x == base) ++count;
                else {
                    count = 1;
                    base = x;
                }
                if(count == maxCount) answer.add(base);
                if(count > maxCount) {
                    maxCount = count;
                    answer.clear();
                    answer.add(base);
                }
          }



            // 记录上一个节点值，初始值为null是为了判断当前节点是否是第一个节点
            private Integer pre = null;
            // 二叉树中节点最大出现次数
            private int maxCount2 = 0;
            // 当前节点的值的最大出现次数
            private int currCount = 0;
            // 众数的个数，也就是数组长度
            private int retCount = 0;
            // 存储众数的数组
            private int[] ret = null;

            public int[] findMode3(TreeNode root) {
                inOrder(root);
                pre = null;
                currCount = 0;
                ret = new int[retCount];
                retCount = 0;
                inOrder(root);
                return ret;
            }

            private void inOrder(TreeNode root) {
                if (root == null) {
                    return;
                }
                inOrder(root.left);
                // 中序遍历，搜索二叉树增序
                // 当前节点值等于前一个节点的值，这个值的出现次数+1
                if (pre != null && root.val == pre) {
                    currCount++;
                } else {
                    // 如果当前节点是第一个节点或者当前节点值不等于前一个节点，那么这个值的出现次数记为1
                    currCount = 1;
                }
                // 如果当前值的出现次数比最大出现次数还要大，那么说明当前值才是出现最多的众数，因此数组个数记为1
                if (currCount > maxCount2) {
                    maxCount = currCount;
                    retCount = 1;
                } else if (currCount == maxCount2) {
                    // 这一步是为了第二次调用时给数组赋值用的
                    // 第一次调用计算得出了最大出现次数，那么第二次调用时，发现当前值的最大出现次数==第一次得出的最大出现次数，那么这个值肯定是众数之一
                    if (ret != null) {
                        ret[retCount] = root.val;
                    }
                    // 如果当前值的出现次数等于最大出现次数，那么说明有多个众数，数组个数+1
                    retCount++;
                }
                // 更新pre值
                pre = root.val;
                inOrder(root.right);
            }
        }



        ///////////////////////////////////////

    /**
     * 非递归实现
     * 中序遍历具体步骤如下所示：
     * 初始化一个空栈。
     * 当【根节点不为空】或者【栈不为空】时，从根节点开始
     * 若当前节点有左子树，一直遍历左子树，每次将当前节点压入栈中。
     * 若当前节点无左子树，从栈中弹出该节点，尝试访问该节点的右子树
     * @param root
     * @return
     */
    public int[] findMode4(TreeNode root) {
          Stack<TreeNode> stack = new Stack<TreeNode>();
          TreeNode pre = null;//记录前一个节点
          int cnt = 0;//记录次数
          int maxCnt = 0;//记录最大次数
          List<Integer> res = new ArrayList<>();

          while(stack.size() > 0 || root != null) {
              //一直向左子树走，每次一将当前节点保存到栈中
                  if(root != null) {
                      stack.add(root);
                      root = root.left;
                  }
              //当前节点为空，走到了最左边，从栈中弹出节点
              //开始对右子树重复上述过程
              else {
                  TreeNode cur = stack.pop();
                  //第一个节点
                  if(pre == null) cnt = 1;
                  //如果和前一个节点的值相等
                  else if(pre.val == cur.val) cnt += 1;
                  else cnt = 1;
                  //如果和最大次数相同，将值放入res
                  if(cnt == maxCnt) res.add(cur.val);
                  //如果大于最大次数
                  else if(cnt > maxCnt) {
                      //更新最大次数
                      maxCnt = cnt;
                      //重新更新res
                      res.clear();
                      res.add(cur.val);
                  }
                  pre = cur;
                  root = cur.right;
              }
          }
          return res.stream().mapToInt(Integer :: intValue).toArray();
    }














































        // TODO: 2022/11/16 Mirrors中序遍历




          








    @Test
    public void test() {

    }
}
