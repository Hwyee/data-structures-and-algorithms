package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.Node;
import cn.hwyee.datastructures.tree.TreeNode;
import lombok.val;

import java.security.cert.TrustAnchor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
//        System.out.println((int)Math.sqrt(10));
        Daily202402 daily202402 = new Daily202402();
        Solution27_1 solution271 = new Solution27_1();
        solution271.countPaths(499979, null);
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
     * <p>
     * 21ms击败55.87%
     * 使用 Java 的用户
     * 消耗内存分布63.09MB击败65.36%
     * 使用 Java 的用户
     *
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
         *
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


    /**
     * 2476. 二叉搜索树最近节点查询:
     * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
     * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
     * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
     * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
     * 返回数组 answer 。
     * 也就是找到queries数组中最接近数组的最大最小值。
     * <p>
     * 双5%，我这个map太离谱了。。强行增加时间和空间。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/24 20:26
     */
    class Solution_24_1 {
        public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
            //1.先遍历出来中序遍历出来就是有序的List（因为是二叉搜索树）。这次用迭代法
            List<Integer> list = new ArrayList<Integer>();
            //用一个map存储所有节点的下标，便于寻找queries数组中最接近数组的最大最小值
            Map<Integer, Integer> map = new HashMap<>();
            int index = 0;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode temp = root;
            int lastVal = 1;
            while (temp != null || !stack.isEmpty()) {
                //遍历左节点
                while (temp != null) {
                    stack.add(temp);
                    temp = temp.left;
                }
                TreeNode pop = stack.pop();
                list.add(pop.val);
                temp = pop.right;
                for (int i = lastVal; i < pop.val; i++) {
                    map.put(i, index);
                }
                index++;
                lastVal = pop.val + 1;
                map.put(pop.val, -1);
            }
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            //2.遍历queries寻找答案
            int len = list.size() - 1;
            for (Integer query : queries) {
                ArrayList<Integer> res = new ArrayList<>();
                Integer integer = map.get(query);
                if (integer != null) {
                    if (integer == -1) {
                        result.add(Arrays.asList(query, query));
                    } else if (integer == 0) {
                        result.add(Arrays.asList(-1, list.get(0)));
                    } else {
                        result.add(Arrays.asList(list.get(integer - 1), list.get(integer)));
                    }
                } else {
                    result.add(Arrays.asList(list.get(len), -1));
                }
            }
            return result;
        }

        /**
         * closestNodes:
         * 官方二分查找
         *
         * @param root
         * @param queries
         * @return java.util.List<java.util.List < java.lang.Integer>>
         * @author hui
         * @version 1.0
         * @date 2024/2/24 22:23
         */
        public List<List<Integer>> closestNodesBS(TreeNode root, List<Integer> queries) {
            List<Integer> arr = new ArrayList<Integer>();
            dfs(root, arr);

            List<List<Integer>> res = new ArrayList<List<Integer>>();
            for (int val : queries) {
                int maxVal = -1, minVal = -1;
                int idx = binarySearch(arr, val);
                if (idx != arr.size()) {
                    maxVal = arr.get(idx);
                    if (arr.get(idx) == val) {
                        minVal = val;
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(minVal);
                        list.add(maxVal);
                        res.add(list);
                        continue;
                    }
                }
                if (idx > 0) {
                    minVal = arr.get(idx - 1);
                }
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(minVal);
                list2.add(maxVal);
                res.add(list2);
            }
            return res;
        }

        public void dfs(TreeNode root, List<Integer> arr) {
            if (root == null) {
                return;
            }
            dfs(root.left, arr);
            arr.add(root.val);
            dfs(root.right, arr);
        }

        public int binarySearch(List<Integer> arr, int target) {
            int low = 0, high = arr.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr.get(mid) >= target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }


    }

    /**
     * 235. 二叉搜索树的最近公共祖先:
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5] p = 2, q = 8
     * 输出: 6
     * <p>
     * 6ms击败75.22%使用 Java 的用户
     * 消耗内存分布43.72MB击败61.90%
     * 使用 Java 的用户
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/25 23:12
     */
    class Solution_25_1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> left = new ArrayList<>();
            List<TreeNode> right = new ArrayList<>();
            //1.查询所有的祖先,因为是二叉搜索树，所以遍历起来会比较容易
            findAncestor(root, p, left);
            findCommonAncestor(root, q, right, left);
            return right.get(right.size() - 1);
        }

        public void findAncestor(TreeNode root, TreeNode p, List<TreeNode> list) {
            int val = p.val;
            TreeNode temp = root;
            //temp!=null
            while (temp.val != val) {
                list.add(temp);
                if (temp.val < val) {
                    temp = temp.right;
                } else {
                    temp = temp.left;
                }
            }
            list.add(temp);
        }

        public void findCommonAncestor(TreeNode root, TreeNode p, List<TreeNode> list, List<TreeNode> ancestor) {
            int val = p.val;
            TreeNode temp = root;
            int index = 0;
            //temp!=null
            while (ancestor.size() > index) {
                TreeNode treeNode = ancestor.get(index++);
                if (temp.val != treeNode.val && temp.val == val) {
                    list.add(temp);
                    break;
                }
                if (temp.val != treeNode.val) {
                    break;
                }

                list.add(temp);
                if (temp.val < val) {
                    temp = temp.right;
                } else {
                    temp = temp.left;
                }
            }

        }

        /**
         * lowestCommonAncestor:
         * 官方解法 一次遍历
         *
         * @param root
         * @param p
         * @param q
         * @return cn.hwyee.datastructures.tree.TreeNode
         * @author hui
         * @version 1.0
         * @date 2024/2/26 0:00
         */
        public TreeNode lowestCommonAncestorGF(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) {
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) {
                    ancestor = ancestor.right;
                } else {
                    break;
                }
            }
            return ancestor;
        }
    }

    /**
     * 938. 二叉搜索树的范围和:
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/26 23:29
     */
    class Solution_26_1 {

        public int rangeSumBST(TreeNode root, int low, int high) {
            //1.二叉树中序遍历
            if (root == null) {
                return -1;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode temp = root;
            int res = 0;
            while (temp != null || !stack.isEmpty()) {
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
                TreeNode pop = stack.pop();
                if (pop.val >= low) {
                    res += pop.val;
                }
                if (pop.val >= high) {
                    break;
                }
                temp = pop.right;
            }
            return res;
        }

        public int rangeSumBSTGF(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }
            if (root.val > high) {
                return rangeSumBST(root.left, low, high);
            }
            if (root.val < low) {
                return rangeSumBST(root.right, low, high);
            }
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }


    }


    /**
     * 2867. 统计树中的合法路径数目: 
     *  给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
     * 请你返回树中的 合法路径数目 。
     * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
     *
     * 注意：
     *
     * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
     * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
     *
     * @author hui
     * @version 1.0
     * @param null  
     * @return 
     * @date 2024/2/27 22:57
     */
    static class Solution27_1 {
        public long countPaths(int n, int[][] edges) {
            //1.找出质数
            List<Integer> list = new ArrayList<>();
            for (int i = 2; i < n; i++) {
                double doubleVal  = Math.sqrt(i);
                int sqrt  = (int)(doubleVal);
                if (sqrt == doubleVal){
                    continue;
                }
                list.add(i);
                for (int j = 2; j <= sqrt; j++) {
                    if (i%j ==0 ){
                        list.remove(new Integer(i));
                        break;
                    }
                }
            }
            System.out.println(list);
            return 1;
        }
        // 埃氏筛
        private static final int N = 100001;
        private static boolean[] isPrime = new boolean[N];
        static {
            Arrays.fill(isPrime, true);
            isPrime[1] = false;
            for (int i = 2; i * i < N; i++) {
                if (isPrime[i]) {
                    //如果 xxx 是质数，那么大于 xxx 的 xxx 的倍数 2x,3x,…一定不是质数,直接从i*i开始遍历
                    //因为2i,3i....一定被之前的遍历过了。比如3*3，那么2*3肯定被遍历过了。
                    for (int j = i * i; j < N; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }

        public long countPathsGF(int n, int[][] edges) {
            List<Integer>[] G = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                G[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int i = edge[0], j = edge[1];
                G[i].add(j);
                G[j].add(i);
            }

            List<Integer> seen = new ArrayList<>();
            long res = 0;
            long[] count = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!isPrime[i]) {
                    continue;
                }
                long cur = 0;
                for (int j : G[i]) {
                    if (isPrime[j]) {
                        continue;
                    }
                    if (count[j] == 0) {
                        seen.clear();
                        dfs(G, seen, j, 0);
                        long cnt = seen.size();
                        for (int k : seen) {
                            count[k] = cnt;
                        }
                    }
                    res += count[j] * cur;
                    cur += count[j];
                }
                res += cur;
            }
            return res;
        }

        private void dfs(List<Integer>[] G, List<Integer> seen, int i, int pre) {
            seen.add(i);
            for (int j : G[i]) {
                if (j != pre && !isPrime[j]) {
                    dfs(G, seen, j, i);
                }
            }
        }


    }

    
    /**
     * 2673. 使二叉树所有路径值相等的最小代价: 
     * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
     *
     * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
     *
     * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
     *
     * 注意：
     *
     * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
     * 路径值 指的是路径上所有节点的值之和。
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/2/28 23:30
     */
    class Solution_28_1 {
        /**
         * 官方：贪心
         */
        public int minIncrements(int n, int[] cost) {
            int ans = 0;
            for (int i = n - 2; i > 0; i -= 2) {
                //操作数只和叶子结点有关
                ans += Math.abs(cost[i] - cost[i + 1]);
                //将叶子节点的数值加到父节点上，然后父节点和父节点的临节点就相当于是叶子节点了
                // 叶节点 i 和 i+1 的双亲节点下标为 i/2（整数除法）
                cost[i / 2] += Math.max(cost[i], cost[i + 1]);
            }
            return ans;
        }
    }


}
