package cn.hwyee.algorithms.type.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName DP
 * @description 动态规划(Dynamic Programming)专场
 * @date 2024/3/16
 * @since JDK 1.8
 */
@Slf4j
public class DP {

    public static void main(String[] args) {

    }

    /**
     * 198. 打家劫舍:
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/16 15:40
     */
    class Solution_1 {
        /**
         * rob_1:
         * 做法一，超出时间限制
         * 因为在递归的过程中，某些分支计算了两次
         *
         * @param nums
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/16 15:43
         */
        public int rob_1(int[] nums) {
            return dfs1(nums, nums.length - 1);
        }

        public int dfs1(int[] nums, int i) {
            if (i < 0) {
                return 0;
            }
            return Math.max(dfs1(nums, i - 1), dfs1(nums, i - 2) + nums[i]);
        }

        int[] cache;
        Map<Integer, Integer> map;

        /**
         * rob_2:
         * 做法2：记忆化搜索
         * 执行耗时0ms,内存占用40M左右
         *
         * @param nums
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/16 15:45
         */
        public int rob_2(int[] nums) {
            //数组和map都行
            //1.用数组
//            cache = new int[nums.length];
//            //循环初始化数组
//            Arrays.fill(cache, -1);
            //2.用map
            map = new HashMap<>(nums.length);
            return dfs2_map(nums, nums.length - 1);
        }

        public int dfs2_array(int[] nums, int i) {
            if (i < 0) {
                return 0;
            }
            if (cache[i] == -1) {
                int max = Math.max(dfs2_array(nums, i - 1), dfs2_array(nums, i - 2) + nums[i]);
                cache[i] = max;
                return max;
            } else {
                return cache[i];
            }
        }

        public int dfs2_map(int[] nums, int i) {
            if (i < 0) {
                return 0;
            }
            if (map.get(i) == null) {
                int max = Math.max(dfs2_map(nums, i - 1), dfs2_map(nums, i - 2) + nums[i]);
                map.put(i, max);
                return max;
            } else {
                return map.get(i);
            }
        }

        /**
         * rob_3_1:
         * 做法3：递推1 -> 空间复杂度O(n)
         *
         * @param nums
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/16 16:07
         */
        public int rob_3_1(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            nums[1] = Math.max(nums[1], nums[0]);
            for (int i = 2; i < nums.length; i++) {

                nums[i] = Math.max(nums[i - 1], nums[i - 2] + nums[i]);
            }
            return nums[nums.length - 1];
        }

        /**
         * rob_3_2:
         * 做法3：递推1 -> 空间复杂度O(1)
         * 只需两个变量就行。
         *
         * @param nums
         * @return int
         * @author hui
         * @version 1.0
         * @date 2024/3/16 16:34
         */
        public int rob_3_2(int[] nums) {
            int pre = 0, res = 0;
            for (int num : nums) {
                int temp = num + pre;
                pre = res;
                res = Math.max(temp, res);
            }
            return res;
        }

    }

    /**
     * 494.目标和:<h1>背包问题：0-1背包 选或不选</h1>
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *
     * 假设负数总数为：n(negative)
     * 正数总数为：p(positive)
     * nums总数为：t(total)
     * 那么：p-n = tar
     * p = t-n
     * t - n - n = tar
     * 2n = t-tar
     * n = (t-tar)/2
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2024/3/17 23:07
     */
    class Solution_2 {
        public int findTargetSumWays(int[] nums, int target) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            int res = 0;
            int n = total - target;
            //n = (t-tar)/2 由此方程可知
            if (n % 2 != 0 || n < 0) {
                return res;
            }
            n /= 2;
            return dfs(nums, 0, n);
        }

        public int dfs(int[] nums, int i, int n) {
            if (i >= nums.length) {
                return n == 0 ? 1 : 0;
            }
            if (n < nums[i]) {
                return dfs(nums, i + 1, n);
            }
            return dfs(nums, i + 1, n) + dfs(nums, i + 1, n - nums[i]);
        }
    }

}
