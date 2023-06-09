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
     * 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树。在遍历左、右子树时，仍然先访问根结点，然后遍历左子树，最后遍历右子树。
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
}
