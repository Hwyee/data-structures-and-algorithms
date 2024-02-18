package cn.hwyee.datastructures.tree;

import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Node
 * @description
 * @date 2024/2/19
 * @since JDK 1.8
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
