package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202402
 * @description
 * @date 2024/2/19
 * @since JDK 1.8
 */
public class Daily202402 {
    
    /**
     * 589. N 叉树的前序遍历: 
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     *
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * @author hui
     * @version 1.0
     * @param null  
     * @return 
     * @date 2024/2/19 0:12
     */
    class solution19_1{
        List<Integer> list = new ArrayList<Integer>();
        public List<Integer> preorder(Node root) {
            p(root);
            return list;
        }
        public void p(Node root){
            if(root==null){
                return;
            }
            list.add(root.val);
            if(root.children == null){
                return;
            }
            root.children.forEach(t -> {
                p(t);
            });
            return;
        }
    }

    
    /**
     * 590. N 叉树的后序遍历:
     * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
     *
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * 执行用时分布2ms
     * 击败26.56%使用 Java 的用户
     * 消耗内存分布43.53MB
     * 击败55.68%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/19 0:14
     */
    class solution19_2{
        List<Integer> list = new ArrayList<Integer>();
        public List<Integer> postorder(Node root) {
            p(root);
            return list;
        }
        public void p(Node root){
            if(root==null){
                return;
            }
            if(root.children == null){
                return;
            }
            root.children.forEach(t -> {
                p(t);
            });
            list.add(root.val);
        }
    }
    
}
