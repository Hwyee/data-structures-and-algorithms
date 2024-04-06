package cn.hwyee.algorithms.leecode.daily;

import cn.hwyee.datastructures.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202404
 * @description leetcode 每日
 * @date 2024/4/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202404 {
    public static void main(String[] args) {
        Solution_1_1 solution11 = new Solution_1_1();
        solution11.finalString("poiinter");
        System.out.println("中文");
    }

    /**
     * 2810. 故障键盘:
     * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
     * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
     * 返回最终笔记本屏幕上输出的字符串。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/1 23:39
     */
    static class Solution_1_1 {
        public String finalString(String s) {
            char[] charArray = s.toCharArray();
            int j = -1;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] =='i'){
                    s = reverse(s,i+j);
                    j--;
                }
            }
            return s;
        }

        public String reverse(String s, int end) {
            char[] charArray = s.toCharArray();
            String substring = s.substring(end + 2);
            for (int i = end; i > end/2; i--) {
                char temp = charArray[i];
                charArray[i] = charArray[end-i];
                charArray[end-i] = temp;
            }
            String s1 = new String(charArray, 0, end + 1);
            return s1+substring;
        }
    }

    public String finalStringGF(String s) {
        Deque<Character> q = new ArrayDeque<Character>();
        boolean head = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != 'i') {
                if (head) {
                    q.offerFirst(ch);
                } else {
                    q.offerLast(ch);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder ans = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                ans.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                ans.append(q.pollFirst());
            }
        }
        return ans.toString();

    }

    /**
     * 894. 所有可能的真二叉树:
     * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
     * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
     * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
     * TODO
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/3 23:45
     */
    class Solution2_1 {

        private  final List<TreeNode>[] f = new ArrayList[11];

         {
            Arrays.setAll(f, i -> new ArrayList<>());
            f[1].add(new TreeNode());
            for (int i = 2; i < f.length; i++) { // 计算 f[i]
                for (int j = 1; j < i; j++) { // 枚举左子树叶子数
                    for (TreeNode left : f[j]) { // 枚举左子树
                        for (TreeNode right : f[i - j]) { // 枚举右子树
                            f[i].add(new TreeNode(0, left, right));
                        }
                    }
                }
            }
        }

        public List<TreeNode> allPossibleFBT(int n) {
            return f[n % 2 > 0 ? (n + 1) / 2 : 0];
        }

    }

    /**
     * 1379. 找出克隆二叉树中的相同节点:
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
     *
     * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/3 23:41
     */
    class Solution_3_1 {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null || original == target) {
                //因为题目要返回cloned节点
                return cloned;
            }
            //左子树
            TreeNode targetCopy = getTargetCopy(original.left, cloned.left, target);
            if (targetCopy != null) {
                //左子树找到了值直接返回
                return targetCopy;
            }
            return getTargetCopy(original.right, cloned.right, target);
        }
    }

    /**
     * 2192. 有向无环图中一个节点的所有祖先:
     * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。
     * 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。
     * 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。
     * 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。
     * TODO
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/4 23:55
     */
    class Solution_4_4 {
        public List<List<Integer>> getAncestorsLS(int n, int[][] edges) {
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                g[e[1]].add(e[0]); // 反向建图
            }

            List<Integer>[] ans = new ArrayList[n];
            Arrays.setAll(ans, i -> new ArrayList<>());
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(vis, false);
                dfs(i, g, vis); // 从 i 开始 DFS
                vis[i] = false; // ans[i] 不含 i
                for (int j = 0; j < n; j++) {
                    if (vis[j]) {
                        ans[i].add(j);
                    }
                }
            }
            return Arrays.asList(ans);
        }

        private void dfs(int x, List<Integer>[] g, boolean[] vis) {
            vis[x] = true; // 避免重复访问
            for (int y : g[x]) {
                if (!vis[y]) {
                    dfs(y, g, vis); // 只递归没有访问过的点
                }
            }
        }

    }

    /**
     * 1026. 节点与其祖先之间的最大差值:
     * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
     * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
     * TODO
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/6 20:42
     */
    class Solution_5_1 {
        private int ans;

        public int maxAncestorDiffLS(TreeNode root) {
            dfs(root);
            return ans;
        }

        private int[] dfs(TreeNode node) {
            if (node == null) { // 需要保证空节点不影响 mn 和 mx
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
            }
            int[] p = dfs(node.left);
            int[] q = dfs(node.right);
            int mn = Math.min(node.val, Math.min(p[0], q[0]));
            int mx = Math.max(node.val, Math.max(p[1], q[1]));
            ans = Math.max(ans, Math.max(node.val - mn, mx - node.val));
            return new int[]{mn, mx};
        }
    }

    /**
     * 1483. 树节点的第 K 个祖先:
     * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
     *
     * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
     *
     * 实现 TreeAncestor 类：
     *
     * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
     * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
     * TODO
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/6 20:44
     */
    class TreeAncestor {

        private int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
            pa = new int[n][m];
            for (int i = 0; i < n; i++)
                pa[i][0] = parent[i];
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[x][i];
                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                    node = pa[node][i];
                    if (node < 0) break;
                }
            }
            return node;
        }

        // 另一种写法，不断去掉 k 的最低位的 1
        public int getKthAncestor2(int node, int k) {
            for (; k > 0 && node != -1; k &= k - 1) {
                node = pa[node][Integer.numberOfTrailingZeros(k)];
            }
            return node;
        }
    }


}
