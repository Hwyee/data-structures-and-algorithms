package cn.hwyee.datastructures.tree;

import lombok.NoArgsConstructor;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName TreeNode
 * @description
 * @date 2023/6/9
 * @since JDK 1.8
 */
@NoArgsConstructor
public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
