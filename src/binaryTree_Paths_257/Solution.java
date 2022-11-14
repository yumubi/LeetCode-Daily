package src.binaryTree_Paths_257;

import com.sun.source.tree.Tree;
import org.junit.Test;

import java.util.*;

public class Solution {
    /**
     * 层序遍历失败
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new LinkedList<String>();
            if(root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
             queue. offer(root);
            res.add(String.valueOf(root.val));
            while(! queue.isEmpty()) {
                TreeNode node =  queue. poll();
                if(!(node.left == null && node.right == null)) {
                    String father = res.remove(0);
                    if (node.left != null) {
                         queue. offer(node.left);
                        StringBuilder sb1 = new StringBuilder(father);
                        res.add(sb1.append("->" + node.left.val).toString());
                    }
                    if (node.right != null) {
                         queue. offer(node.right);
                        StringBuilder sb2 = new StringBuilder(father);
                        res.add(sb2.append("->" + node.right.val).toString());
                    }
                }
            }
            return res;
    }

    /**
     * 深度优先搜索
     * 如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节点。
     * 如果当前节点是叶子节点，则在当前路径末尾添加该节点后我们就得到了一条从根节点到叶子节点的路径，
     * 将该路径加入到答案即可。
     *
     * 时间复杂度：O(N^2)其中 N 表示节点数目。在深度优先搜索中每个节点会被访问一次且只会被访问一次，
     * 每一次会对 path 变量进行拷贝构造，时间代价为 O(N)，故时间复杂度为 O(N^2)
     *
     * 空间复杂度：O(N^2)

     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if(root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(Integer.toString(root.val));
            if(root.left == null && root.right == null) {//当前节点是叶子节点
                paths.add(pathSB.toString());//把路径添加到答案中
            } else {
                pathSB.append(  "->");//当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }


    /**
     * 广度优先搜索
     * 维护一个队列，存储节点以及根到该节点的路径。一开始这个队列里只有根节点。
     * 在每一步迭代中，我们取出队列中的首节点，如果它是叶子节点，则将它对应的路径加入到答案中。
     * 如果它不是叶子节点，则将它的所有孩子节点加入到队列的末尾。当队列为空时广度优先搜索结束，我们即能得到答案。
     *
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)
     * @param root
     * @return
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if(root == null) return paths;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if(node.left == null && node.right == null) paths.add(path);
            else {
                if(node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }
                if(node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }













    @Test
    public void test() {


    }
}
