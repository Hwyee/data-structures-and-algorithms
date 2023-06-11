package cn.hwyee.algorithms.util;

import cn.hwyee.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName TreeUtil
 * @description
 * @date 2023/6/9
 * @since JDK 1.8
 */
public class TreeUtil {

    /**
     * 二叉树的前序遍历
     * 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树。
     * 在遍历左、右子树时，仍然先访问根结点，然后遍历左子树，最后遍历右子树。
     *
     * @param root TreeNode类
     * @return int整型一维数组
     */
    public int[] preorderTraversal (TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        traverse(list,root);
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i).val;
        }
        return ints;
    }

    public void traverse(List<TreeNode> list,TreeNode node){
        if (node == null) {
            return ;
        }
        list.add(node);
        if (node.left != null) {
            traverse(list,node.left);
        }
        if (node.right != null) {
            traverse(list,node.right);
        }
    }


    public void inorder(List<Integer> list, TreeNode root){
        //遇到空节点则返回
        if(root == null) {
            return;
        }
        //先去左子树
        inorder(list, root.left);
        //再访问根节点
        list.add(root.val);
        //最后去右子树
        inorder(list, root.right);
    }

    /**
     * inorderTraversal:
     *  二叉树的中序遍历
     *  中序遍历就是从最左边开始，把每个节点垂直投影到同一直线上，然后从左往右读值
     * @author hui
     * @version 1.0
     * @param root  
     * @return int[]
     * @date 2023/6/11 0:21
     */
    public int[] inorderTraversal (TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归中序遍历
        inorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    /**
     * 二叉树的后序遍历
     * 什么是二叉树的后续遍历，简单来说就是“左右根”，
     * 展开来说就是优先访问根节点的左子树的全部节点，然后再访问根节点的右子树的全部节点，最后再访问根节点。
     * 对于每棵子树的访问也按照这个逻辑，因此叫做“左右根”的顺序。
     *
     * @param root TreeNode类
     * @return int整型一维数组
     */
    public int[] postorderTraversal (TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        //递归中序遍历
        inorder(list, root);
        //返回的结果
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void postorder(List<Integer> list, TreeNode root) {
        //遇到空节点则返回
        if (root == null) {
            return;
        }
        //先去左子树
        inorder(list, root.left);
        //最后去右子树
        inorder(list, root.right);
        //再访问根节点
        list.add(root.val);
    }
}
