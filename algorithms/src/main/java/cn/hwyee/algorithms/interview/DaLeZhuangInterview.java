package cn.hwyee.algorithms.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ODSolution
 * @description RaoZhangWen r00801205
 * @date 2023/9/9
 * @since JDK 1.8
 */

class DaLeZhuangInterview {
/**
 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

 示例 1：
 输入：root = [3,9,20,null,null,15,7]
 输出：[[3],[9,20],[15,7]]
 示例 2：
 输入：root = [1]
 输出：[[1]]
 示例 3：
 输入：root = []
 输出：[]

 提示：
 树中节点数目在范围 [0, 2000] 内
 -1000 <= Node.val <= 1000
 */
    /**
     * Definition for a binary tree node.
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * }
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String treeString = input.substring(1, input.length() - 1);
        String[] treeArr = treeString.split(",");
        int level = 0;
        if (treeArr.length==0){
            System.out.println("[]");
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int max = 0;
            while (max < treeArr.length){
                sb.append("[");
                for (; max < Math.pow(2,level); max++) {
                    if (max >= treeArr.length){
                        break;
                    }
                    sb.append(treeArr[max]).append(",");
                }
                sb.append("]");
                level++;
            }
            sb.append("]");
            System.out.println(sb);
        }
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> arrayLists = new ArrayList<>();
        if (root == null){
            return arrayLists;
        }
        ArrayList<Integer> rootV = new ArrayList<>();
        rootV.add(root.val);
        arrayLists.add(rootV);
        order(root.left);

        return arrayLists;
    }

    public static List<Integer> order(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root!=null){
            list.add(root.left.val);
            list.add(root.right.val);
        }
        return list;
    }
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

