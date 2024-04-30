package cn.hwyee.algorithms.leecode.week;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Week_394
 * @description 394周赛
 * @date 2024/4/21
 * @since JDK 1.8
 */
public class Week_394 {
    public static void main(String[] args) {
        System.out.println('A' - 'a');
        System.out.println('B' - 'b');
    }

    /**
     * 100294. 统计特殊字母的数量 I:
     * 给你一个字符串 word。如果 word 中同时存在某个字母的小写形式和大写形式，则称这个字母为 特殊字母 。
     * <p>
     * 返回 word 中 特殊字母 的数量。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/21 10:44
     */
    class Solution_1 {

        public int numberOfSpecialChars(String word) {
            int[] ans = new int[26];
            char[] charArray = word.toCharArray();
            int res = 0;
            for (char c : charArray) {
                if (c < 'a') {
                    if (ans[c - 'A'] == -1) {
                        continue;
                    }
                    if (ans[c - 'A'] >= 'a') {
                        res++;
                        ans[c - 'A'] = -1;
                    } else {
                        ans[c - 'A'] = c;
                    }
                } else {
                    if (ans[c - 'a'] == -1) {
                        continue;
                    }
                    if (ans[c - 'a'] < 'a' && ans[c - 'a'] >= 'A') {
                        res++;
                        ans[c - 'a'] = -1;
                    } else {
                        ans[c - 'a'] = c;
                    }
                }
            }
            return res;
        }
    }


    /**
     * 100291. 统计特殊字母的数量 II:
     * 给你一个字符串 word。如果 word 中同时出现某个字母 c 的小写形式和大写形式，并且 每个 小写形式的 c 都出现在第一个大写形式的 c 之前，则称字母 c 是一个 特殊字母 。
     * <p>
     * 返回 word 中 特殊字母 的数量。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/4/21 11:02
     */
    class Solution_2 {

        public int numberOfSpecialChars(String word) {
            int[] ans = new int[26];
            char[] charArray = word.toCharArray();
            int res = 0;
            for (char c : charArray) {
                if (c < 'a') {
                    if (ans[c - 'A'] <= -1) {
                        continue;
                    }
                    if (ans[c - 'A'] >= 'a') {
                        res++;
                        ans[c - 'A'] = -1;
                    }else {
                        ans[c - 'A'] = -2;
                    }
                } else {
                    if (ans[c - 'a'] <= -1) {
                        if (ans[c - 'a'] == -1) {
                            res--;
                            ans[c - 'a'] = -2;
                        }
                        continue;
                    }
                    ans[c - 'a'] = c;
                }
            }
            return res;
        }
    }

    /**
     * 100290. 使矩阵满足条件的最少操作次数:
     * 给你一个大小为 m x n 的二维矩形 grid 。每次 操作 中，你可以将 任一 格子的值修改为 任意 非负整数。
     * 完成所有操作后，你需要确保每个格子 grid[i][j] 的值满足：
     *
     * 如果下面相邻格子存在的话，它们的值相等，也就是 grid[i][j] == grid[i + 1][j]（如果存在）。
     * 如果右边相邻格子存在的话，它们的值不相等，也就是 grid[i][j] != grid[i][j + 1]（如果存在）。
     * 请你返回需要的 最少 操作数目。
     * TODO
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/4/21 11:12
     */
    class Solution_3 {
        public int minimumOperations(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] fir = null;
            int[][] sec = new int[2][2];
            for (int i = 0; i < grid[0].length; i++) {//列
                int[] t = new int[10];
                for (int j = 0; j < grid.length; j++) {//行
                    int temp = grid[j][i];
                    t[temp]++;
                }
            }
            return 1;
        }
    }

    /**
     * 100276. 最短路径中的边:
     * 给你一个 n 个节点的无向带权图，节点编号为 0 到 n - 1 。图中总共有 m 条边，用二维数组 edges 表示，其中 edges[i] = [ai, bi, wi] 表示节点 ai 和 bi 之间有一条边权为 wi 的边。
     *
     * 对于节点 0 为出发点，节点 n - 1 为结束点的所有最短路，你需要返回一个长度为 m 的 boolean 数组 answer ，如果 edges[i] 至少 在其中一条最短路上，那么 answer[i] 为 true ，否则 answer[i] 为 false 。
     *
     * 请你返回数组 answer 。
     *
     * 注意，图可能不连通。
     *
     * TODO
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/4/21 14:46
     */
    class Solution_4 {
        public boolean[] findAnswer(int n, int[][] edges) {
            return null;
        }
    }
    
}
