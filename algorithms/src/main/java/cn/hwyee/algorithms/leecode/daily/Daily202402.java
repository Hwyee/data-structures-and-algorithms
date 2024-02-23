package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.Node;
import cn.hwyee.datastructures.tree.TreeNode;

import java.security.cert.TrustAnchor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202402
 * @description
 * @date 2024/2/19
 * @since JDK 1.8
 */
public class Daily202402 {
    public static void main(String[] args) {
        //priorityQueue，只能用继承了Comparable接口的类。否则报错。TreeNode cannot be cast to class java.lang.Comparable
//        PriorityQueue<TreeNode> priorityQueue = new PriorityQueue<>();
//        priorityQueue.add(new TreeNode(1));

    }

    /**
     * 589. N 叉树的前序遍历:
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * <p>
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     *
     * @param null
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/19 0:12
     */
    class solution19_1 {
        List<Integer> list = new ArrayList<Integer>();

        public List<Integer> preorder(Node root) {
            p(root);
            return list;
        }

        public void p(Node root) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            if (root.children == null) {
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
     * <p>
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * 执行用时分布2ms
     * 击败26.56%使用 Java 的用户
     * 消耗内存分布43.53MB
     * 击败55.68%使用 Java 的用户
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/19 0:14
     */
    class solution19_2 {
        List<Integer> list = new ArrayList<Integer>();

        public List<Integer> postorder(Node root) {
            p(root);
            return list;
        }

        public void p(Node root) {
            if (root == null) {
                return;
            }
            if (root.children == null) {
                return;
            }
            root.children.forEach(t -> {
                p(t);
            });
            list.add(root.val);
        }
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树:
     * 无重复元素
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/20 22:15
     */
    class solution20_1 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preLen = preorder.length;
            int inLen = inorder.length;
            ;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, map, 0, preLen - 1, 0, inLen - 1);
        }

        private TreeNode buildTree(int[] preorder, Map<Integer, Integer> map, int preLeft, int preRight, int inLeft, int inRight) {
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }
            int rootVal = preorder[preLeft];
            TreeNode root = new TreeNode(rootVal);
            int rootIndex = map.get(rootVal);
            root.left = buildTree(preorder, map, preLeft + 1, preLeft + (rootIndex - inLeft), inLeft, rootIndex - 1);
            root.right = buildTree(preorder, map, preLeft + (rootIndex - inLeft) + 1, preRight, rootIndex + 1, inRight);
            return root;
        }
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树:
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     *
     * @param null
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/21 23:43
     */
    class solution21_1 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int postLen = postorder.length;
            int inLen = inorder.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(postorder, map, 0, postLen - 1, 0, inLen - 1);
        }

        private TreeNode buildTree(int[] postorder, Map<Integer, Integer> map, int postLeft, int postRight, int inLeft, int inRight) {
            if (postLeft > postRight || inLeft > inRight) {
                return null;
            }
            int rootVal = postorder[postRight];
            TreeNode root = new TreeNode(rootVal);
            int rootIndex = map.get(rootVal);
            root.right = buildTree(postorder, map, postLeft + (rootIndex - inLeft), postRight - 1, rootIndex + 1, inRight);
            root.left = buildTree(postorder, map, postLeft, postLeft + (rootIndex - inLeft) - 1, inLeft, rootIndex - 1);
            return root;
        }
    }


    /**
     * 889. 根据前序和后序遍历构造二叉树 :
     * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     * 如果存在多个答案，您可以返回其中 任何 一个。
     * preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
     * 当二叉树只有左子树或右子树时，会有两个答案。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/22 23:47
     */
    class solution22_1 {
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            int n = preorder.length;
            Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                postMap.put(postorder[i], i);
            }
            return dfs(preorder, postorder, postMap, 0, n - 1, 0, n - 1);
        }

        public TreeNode dfs(int[] preorder, int[] postorder, Map<Integer, Integer> postMap, int preLeft, int preRight, int postLeft, int postRight) {
            if (preLeft > preRight) {
                return null;
            }
            int leftCount = 0;
            //preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
            //前序遍历根节点的下一个索引的值是后序遍历左节点的边缘值。比如1是根节点，那么2在后序遍历的索引下标值+1就是左子树的长度.长度为3.
            if (preLeft < preRight) {
                leftCount = postMap.get(preorder[preLeft + 1]) - postLeft + 1;
            }
            return new TreeNode(preorder[preLeft],//根节点
                    dfs(preorder, postorder, postMap, preLeft + 1, preLeft + leftCount, postLeft, postLeft + leftCount - 1),
                    dfs(preorder, postorder, postMap, preLeft + leftCount + 1, preRight, postLeft + leftCount, postRight - 1));
        }

    }

    /**
     * 2583. 二叉树中的第 K 大层和:
     * 给你一棵二叉树的根节点 root 和一个正整数 k 。
     * 树中的 层和 是指 同一层 上节点值的总和。
     * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
     * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
     * 我这个比官方快6倍，内存占用少了5倍
     *
     * 21ms击败55.87%
     * 使用 Java 的用户
     * 消耗内存分布63.09MB击败65.36%
     * 使用 Java 的用户
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/23 23:48
     */
    class Solution {

        public long kthLargestLevelSum(TreeNode root, int k) {
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>((x, y) -> (x > y) ? -1 : ((x == y) ? 0 : 1));
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int size = 1;
            Long sum = 0L;
            while (!queue.isEmpty()) {
                sum = 0L;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    sum += poll.val;
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                }
                priorityQueue.add(sum);
                size = queue.size();
            }
            if (priorityQueue.size() < k) {
                return -1;
            }
            for (int i = 0; i < k; i++) {
                if (i == k - 1) {
                    return priorityQueue.poll();
                }
                priorityQueue.poll();
            }
            return -1;
        }

        /**
         * kthLargestLevelSum:
         * 官方
         * 执行用时分布
         * 45ms击败14.52%使用 Java 的用户
         * 消耗内存分布
         * 68.68MB击败10.61%使用 Java 的用户
         * @param root
         * @param k
         * @return long
         * @author hui
         * @version 1.0
         * @date 2024/2/24 0:37
         */
        public long kthLargestLevelSumGF(TreeNode root, int k) {
            Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.offer(root);
            List<Long> levelSumArray = new ArrayList<Long>();
            while (!queue.isEmpty()) {
                List<TreeNode> levelNodes = new ArrayList<TreeNode>(queue);
                long levelSum = 0;
                queue.clear();
                for (TreeNode node : levelNodes) {
                    levelSum += node.val;
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                levelSumArray.add(levelSum);
            }
            if (levelSumArray.size() < k) {
                return -1;
            }
            Collections.sort(levelSumArray);
            return levelSumArray.get(levelSumArray.size() - k);
        }
    }

}
