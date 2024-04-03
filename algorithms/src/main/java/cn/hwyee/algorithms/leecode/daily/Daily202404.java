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

}
