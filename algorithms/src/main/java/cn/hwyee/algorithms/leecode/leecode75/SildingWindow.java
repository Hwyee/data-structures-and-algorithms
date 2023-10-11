package cn.hwyee.algorithms.leecode.leecode75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author hui
 * @version 1.0
 * @className SildingWindow
 * @description 滑动窗口
 * @date 2023/10/10
 * @since JDK 1.8
 */
public class SildingWindow {

    /**
     * findMaxAverage:
     * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     *
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 任何误差小于 10的-5次方 0.0001的答案都将被视为正确答案。
     * @author hui
     * @version 1.0
     * @param nums
     * @param k
     * @return double
     * @date 2023/10/10 16:32
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum+=nums[i];
        }
        //连续子数组
        int res = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i-k]+nums[i];
            res = Math.max(res,sum);
        }
        return 1.0* res/k;
    }



    /**
     * maxVowels:
     * 给你字符串 s 和整数 k 。
     *
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     *
     * 英文中的 元音字母 为（a, e, i, o, u）。
     * @author hui
     * @version 1.0
     * @param s
     * @param k
     * @return int
     * @date 2023/10/11 16:06
     */
    public int maxVowels(String s, int k) {
        return 1;
    }


}
