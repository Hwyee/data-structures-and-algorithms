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
     * <p>
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     * <p>
     * 任何误差小于 10的-5次方 0.0001的答案都将被视为正确答案。
     *
     * @param nums
     * @param k
     * @return double
     * @author hui
     * @version 1.0
     * @date 2023/10/10 16:32
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        //连续子数组
        int res = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            res = Math.max(res, sum);
        }
        return 1.0 * res / k;
    }


    /**
     * maxVowels:
     * 给你字符串 s 和整数 k 。
     * <p>
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     * <p>
     * 英文中的 元音字母 为（a, e, i, o, u）。
     * 时间
     * 23ms
     * 击败 21.70%使用 Java 的用户
     * 内存
     * 42.13MB
     * 击败 9.11%使用 Java 的用户
     *
     * @param s
     * @param k
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/11 16:06
     */
    public int maxVowels(String s, int k) {
        char[] charArray = s.toCharArray();
        int res = 0;
        for (int i = 0; i < k; i++) {
            if ("aeiou".contains(String.valueOf(charArray[i]))) {
                res++;
            }
        }
        int maxRes = res;
        for (int i = k; i < charArray.length; i++) {
            if (res == k) {
                return k;
            }
            if (charArray[i - k] != charArray[i]) {
                boolean flag = "aeiou".contains(String.valueOf(charArray[i - k]));
                boolean newFlag = "aeiou".contains(String.valueOf(charArray[i]));
                if (flag && !newFlag) {
                    res--;
                    maxRes = Math.max(res, maxRes);
                } else if (!flag && newFlag) {
                    res++;
                    maxRes = Math.max(res, maxRes);
                }
            }
        }
        return maxRes;
    }


    /**
     * maxVowelsGF:
     * 官方解法，手写了一个判断元音字母的方法。
     * 很巧妙。
     * 时间
     * 9ms
     * 击败 94.98%使用 Java 的用户
     * 内存
     * 41.81MB
     * 击败 28.57%使用 Java 的用户
     *
     * @param s
     * @param k
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/11 22:54
     */
    public int maxVowelsGF(String s, int k) {
        char[] charArray = s.toCharArray();
        int res = 0;
        for (int i = 0; i < k; i++) {
            res += isVowel(charArray[i]);
        }
        int maxRes = res;
        for (int i = k; i < charArray.length; i++) {
            if (maxRes == k) {
                return k;
            }
            if (charArray[i - k] != charArray[i]) {
                //只有新的是元音字母，旧的不是元音字母才会+1.
                res += isVowel(charArray[i]) - isVowel(charArray[i - k]);
                maxRes = Math.max(res, maxRes);
            }
        }
        return maxRes;
    }

    /**
     * isVowel:
     * 判断是否是元音字母
     *
     * @param ch
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/11 23:00
     */
    public static int isVowel(char ch) {
        return 'a' == ch || 'e' == ch || 'i' == ch || 'o' == ch || 'u' == ch ? 1 : 0;
    }


    /**
     * longestOnes:
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     * 时间
     * 7ms
     * 击败 12.99%使用 Java 的用户
     * 内存
     * 43.88MB
     * 击败 71.67%使用 Java 的用户
     * @param nums
     * @param k
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/11 23:06
     */
    public static int longestOnes(int[] nums, int k) {
        int length = nums.length;
        int turnOverCount = 0;
        int res = 0;
        int maxRes = 0;
        LinkedList<Integer> indexList = new LinkedList<>();
        indexList.add(-1);
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                if (k==0) {
                    maxRes = Math.max(maxRes, res);
                    res=0;
                }else if (turnOverCount < k ) {
                    indexList.add(i);
                    turnOverCount++;
                    res++;
                } else {
                    Integer first = indexList.poll();
                    Integer second = indexList.peek();
                    indexList.add(i);
                    maxRes = Math.max(maxRes, res);
                    res=res - second + first +1;
                }
            } else {
                res++;
            }
        }
        return Math.max(maxRes, res);
    }

    public static void main(String[] args) {
        int[] arr = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int i = longestOnes(arr, 3);
        System.out.println(i);
    }

}
