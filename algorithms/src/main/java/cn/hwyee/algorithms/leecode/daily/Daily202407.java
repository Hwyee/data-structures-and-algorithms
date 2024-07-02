package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202407
 * @description
 * @date 2024/7/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202407 {
    public static void main(String[] args) {
        
    }
    
    /**
     * 2065. 最大化一张图中的路径价值:
     * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。
     *
     * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
     *
     * 请你返回一条合法路径的 最大 价值。
     *
     * 注意：每个节点 至多 有 四条 边与之相连。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/1 16:01
     */
    class Solution_1_1 {

        public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
            int n = values.length;
            List<int[]>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                //无向图 x -> y的时间
                int x = e[0];
                int y = e[1];
                int t = e[2];
                g[x].add(new int[]{y, t});
                g[y].add(new int[]{x, t});
            }

            boolean[] vis = new boolean[n];
            vis[0] = true;
            return dfs(0, 0, values[0], vis, g, values, maxTime);
        }

        private int dfs(int x, int sumTime, int sumValue, boolean[] vis, List<int[]>[] g, int[] values, int maxTime) {
            int res = x == 0 ? sumValue : 0;
            for (int[] e : g[x]) {
                int y = e[0];
                int t = e[1];
                if (sumTime + t > maxTime) {
                    continue;
                }
                if (vis[y]) {
                    res = Math.max(res, dfs(y, sumTime + t, sumValue, vis, g, values, maxTime));
                } else {
                    vis[y] = true;
                    // 每个节点的价值至多算入价值总和中一次
                    res = Math.max(res, dfs(y, sumTime + t, sumValue + values[y], vis, g, values, maxTime));
                    vis[y] = false; // 恢复现场
                }
            }
            return res;
        }


    }

    /**
     * 3115. 质数的最大距离:
     * 给你一个整数数组 nums。
     *
     * 返回两个（不一定不同的）质数在 nums 中 下标 的 最大距离。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/7/2 11:12
     */
    class Solutiond2q1 {
        public int maximumPrimeDifference(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                if (isPrime(nums[left]) && isPrime(nums[right])) {
                    return right - left;
                }
                if (!isPrime(nums[left])) {
                    left++;
                }
                if (!isPrime(nums[right])) {
                    right--;
                }
            }
            return 0;
        }
        private boolean isPrime(int num) {
            if (num < 2) {
                return false;
            }
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
