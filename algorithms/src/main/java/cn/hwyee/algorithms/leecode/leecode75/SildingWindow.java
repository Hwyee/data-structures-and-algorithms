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
        int[] integers = new int[k];
        int min= Integer.MAX_VALUE;
        int minIndex = -1;
        int kNum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(2);
        for (int i = 0; i < k; i++) {
            if (min>nums[i]){
                minIndex = i;
            }
            integers[i] = nums[i];
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i]>nums[minIndex]){
                nums[minIndex]=nums[i];
            }
        }
        int sum = Arrays.stream(integers).sum();
        return sum/k;
    }

}
