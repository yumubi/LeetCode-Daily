package src.cousins_In_Binary_Tree_993;

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
             * 错误
             * @param root
             * @param x
             * @param y
             * @return
             */
            public boolean isCousins(TreeNode root, int x, int y) {
                int depthX = -1;
                TreeNode parentX = new TreeNode(-1);
                TreeNode parentY = new TreeNode(-1);
                int depthY = -1;
                    dfs(root, 0, x, depthX, parentX);
                    dfs(root, 0, x, depthY, parentY);
                    if(depthX == depthY && parentX.val != parentY.val) return true;
                    return false;
            }

            public void dfs(TreeNode root, int depth, int val, int ans, TreeNode parent) {
                if(root == null) return;
                if(root.val == val) {
                    ans = depth;
                    return;
                }
                parent = root;
                dfs(root.left, depth + 1, val, ans, parent);
                dfs(root.right, depth + 1,val, ans, parent);
            }

            //要想判断两个节点 x和 y 是否为堂兄弟节点，我们就需要求出这两个节点分别的「深度」以及「父节点」。
            //因此，我们可以从根节点开始，对树进行一次遍历，在遍历的过程中维护「深度」以及「父节点」这两个信息。
            // 当我们遍历到 x或 y节点时，就将信息记录下来；当这两个节点都遍历完成了以后，我们就可以退出遍历的过程，判断它们是否为堂兄弟节点了。


            /**
             * dfs
             * @param root
             * @param x
             * @param y
             * @return
             */
            int x;
            TreeNode xParent;
            int xDepth;
            boolean xFound = false;
            int y;
            TreeNode yParent;
            int yDepth;
            boolean yFound = false;
            public boolean isCousins1(TreeNode root, int x, int y) {
                this.x = x;
                this.y = y;
                dfs1(root, 0, null);
                return xDepth == yDepth && xParent != yParent;
            }
            public void dfs1(TreeNode node, int depth, TreeNode parent) {
                if(node == null) return;
                if(node.val == x) {
                    xParent = parent;
                    xDepth = depth;
                    xFound = true;
                } else if(node.val == y) {
                    yParent = parent;
                    yDepth = depth;
                    yFound = true;
                }
                if(xFound && yFound) return;
                dfs1(node.left, depth + 1, node);
                if(xFound && yFound) return;
                dfs1(node.right, depth + 1, node);
            }


            /**
             //广度优先搜索的过程中，每当我们从队首取出一个节点，它就会作为「父节点」，将最多两个子节点放入队尾。
             // 因此，除了节点以外，我们只需要在队列中额外存储「深度」的信息即可。
             * @param root
             * @param x
             * @param y
             * @return
             */
            public boolean isCousins2(TreeNode root, int x, int y) {
                    this.x = x;
                    this.y = y;

                Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
                Queue<Integer> depthQueue = new LinkedList<Integer>();
                nodeQueue.offer(root);
                depthQueue.offer(0);
                update(root, null, 0);

                while(!nodeQueue.isEmpty()) {
                    TreeNode node = nodeQueue.poll();
                    int depth = depthQueue.poll();
                    if(node.left != null) {
                        nodeQueue.offer(node.left);
                        depthQueue.offer(depth + 1);
                        update(node.left, node, depth + 1);
                    }
                    if(node.right != null) {
                        nodeQueue.offer(node.right);
                        depthQueue.offer(depth + 1);
                        update(node.right, node, depth + 1);
                    }
                    if(xFound && yFound) break;
                }
                return xDepth == yDepth && xParent != yParent;
            }


            public void update(TreeNode node, TreeNode parent, int depth) {
                if(node.val == x) {
                    xParent = parent;
                    xDepth = depth;
                    xFound = true;
                } else if(node.val == y) {
                    yParent = parent;
                    yDepth = depth;
                    yFound = true;
                }
            }

            //需要注意的时，我们需要区分出「搜索不到」和「搜索对象为 root（没有 fa 父节点）」两种情况。
            //我们约定使用 −1 代指没有找到目标值 t，使用 0 代表找到了目标值 t，但其不存在父节点。
            public boolean isCousins3(TreeNode root,int x, int y) {
                int[] xi = dfs3(root, null, 0, x);
                int[] yi = dfs3(root, null, 0, y);
                return xi[1] == yi[1] && xi[0] != yi[0];
            }
            int[] dfs3(TreeNode root, TreeNode fa, int depth, int t) {
                if(root == null) return new int[] {-1, -1};//使用-1代表搜索不到t
                if(root.val == t) return new int[] {fa != null ? fa.val : 1, depth};//使用1代表搜索值t为root
                int[] l = dfs3(root.left, root, depth + 1, t);
                if(l[0] != -1) return l;
                return dfs3(root.right, root, depth + 1, t);
            }



            public boolean isCousins4(TreeNode root, int x, int y) {
                int[] xi = bfs(root, x);
                int[] yi = bfs(root, y);
                return xi[1] == yi[1] && xi[0] != yi[0];
            }
            int[] bfs(TreeNode root, int t) {
                Deque<Object[]> d = new ArrayDeque<>();
                d.addLast(new Object[]{root, null, 0});
                while(!d.isEmpty()) {
                    int size = d.size();
                    while(size-- > 0) {
                        Object[] poll = d.pollFirst();
                        TreeNode cur = (TreeNode) poll[0], fa = (TreeNode) poll[1];
                        int depth = (Integer)poll[2];
                        if(cur.val == t) return new int[]{fa != null ? fa.val : 0, depth};
                        if(cur.left != null) d.addLast(new Object[]{cur.left, cur, depth + 1});
                        if(cur.right != null) d.addLast(new Object[]{cur.right, cur, depth + 1});
                    }
                }
                return new int[]{-1, -1};
            }


            public boolean isCousins5(TreeNode root, int x ,int y) {
                Queue<TreeNode> queue = new LinkedList<>();
                Queue<Integer> value = new LinkedList<>();
                queue.add(root);
                value.add(root.val);

                while(!queue.isEmpty()) {
                    int levelSize = queue.size();
                    for(int i = 0; i < levelSize; i++) {
                        TreeNode poll = queue.poll();
                        value.poll();
                        if(poll.left != null && poll.right != null) {
                            if( (poll.left.val == x && poll.right.val == y) ||
                                    (poll.left.val == y && poll.right.val == x) ) return false;
                        }
                        if(poll.left != null) {
                            queue.offer(poll.left);
                            value.offer(poll.left.val);
                        }
                        if(poll.right != null) {
                            queue.offer(poll.right);
                            value.offer(poll.right.val);
                        }
                    }
                    if(value.contains(x) && value.contains(y)) return true;
                }
                return false;
            }




            //扯个别的，堆排序大家可熟？堆，又叫二叉堆，因为排序场景下可以把二叉树看做完全二叉树，故可以用数组建堆，
            // 序号（index）即为节点标号（注意，不是root.val）——根节点序号为1；某节点序号为k，则左右子节点序号分别为2k和2k+1。
            // 这有什么好处呢？每个子节点可以快速回溯到父节点（index除以2即可），只需要logN次操作就能回到根上，这也就是为何堆排序的复杂度是O(NlogN)。
            //但是“序号标记”并不需要完全二叉树，任意的二叉树都可以给节点标号，不存在的那些节点缺省即是。回到本题，
            // 如何描述“堂兄弟”的数学关系？如果nodex的序号为ix，nodey的为iy：
            //不同的父亲：ix / 2 != iy / 2
            //相同的代际（层数一样）：high_bit(ix) = high_bit(iy)，即ix的最高比特位和iy的最高比特位相同。
            // 例如，ix = 0b10010和iy = 0b10111即具有相同的high_bit（0b10000 = 16）。

            Map<Integer, Integer> seen = new HashMap<>();

            public boolean isCousins6(TreeNode root, int x, int y) {
                traversal(root, 1);
                if (!seen.containsKey(x) || !seen.containsKey(y)) {
                    return false;
                }
                int ix = seen.get(x), iy = seen.get(y);
                return ix / 2 != iy / 2 && Integer.toBinaryString(ix).length() == Integer.toBinaryString(iy).length();
            }

            private void traversal(TreeNode root, int index) {
                if (root == null) return;
                seen.put(root.val, index);
                traversal(root.left, index * 2);
                traversal(root.right, index * 2 + 1);
            }










































        }
}
