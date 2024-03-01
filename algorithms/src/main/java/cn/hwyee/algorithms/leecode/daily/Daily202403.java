package cn.hwyee.algorithms.leecode.daily;

import org.springframework.cglib.proxy.Factory;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202403
 * @description
 * @date 2024/3/1
 * @since JDK 1.8
 */
public class Daily202403 {

    public static void main(String[] args) {

    }

    /**
     * 2369. 检查数组是否存在有效划分:
     * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。
     * <p>
     * 如果获得的这些子数组中每个都能满足下述条件 之一 ，则可以称其为数组的一种 有效 划分：
     * <p>
     * 子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。
     * 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
     * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
     * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
     *
     * @param null
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/1 23:21
     */
    class Solution_01_01 {
        public boolean validPartition(int[] nums) {
            //传过来的数组应该是有序的
            int n = nums.length;
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 2; i <= n; i++) {
                if (i >= 2) {
                    dp[i] = dp[i - 2] && validTwo(nums[i - 1], nums[i - 2]);
                }
                if (i >= 3) {
                    dp[i] = dp[i] || (dp[i - 3] && validThree(nums[i - 3], nums[i - 2], nums[i - 1]));
                }

            }
            return dp[n];
        }

        public boolean validTwo(int i, int j) {
            return i == j;
        }

        public boolean validThree(int i, int j, int k) {
            return (i == j && i == k) || (j == i + 1 && k == j + 1);
        }
    }
}
