package src.conStruct_String_From_Binary_Tree_606;

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
          public String tree2str1(TreeNode root) {
              StringBuilder sb =new StringBuilder();
              preOrder(root, sb);
              return sb.toString().replace("()", "");
          }


          public void preOrder(TreeNode root, StringBuilder sb) {
              if(root == null) {
                  sb.append(")()(");
                  return;
              };
              sb.append(root.val + "(");
              preOrder(root.left, sb);
              preOrder(root.right, sb);
          }




          public String tree2str(TreeNode root) {
              if(root == null) return "";
              if(root.left == null && root.right == null) return Integer.toString(root.val);
              if(root.right == null) return new StringBuffer().append(root.val).append("(")
                      .append(tree2str(root.left)).append(")").toString();
              return new StringBuffer().append(root.val)
          }





      }
}
