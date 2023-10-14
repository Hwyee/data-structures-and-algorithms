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
     * <p>
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     * 时间
     * 7ms
     * 击败 12.99%使用 Java 的用户
     * 内存
     * 43.88MB
     * 击败 71.67%使用 Java 的用户
     *
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
                if (k == 0) {
                    maxRes = Math.max(maxRes, res);
                    res = 0;
                } else if (turnOverCount < k) {
                    indexList.add(i);
                    turnOverCount++;
                    res++;
                } else {
                    Integer first = indexList.poll();
                    Integer second = indexList.peek();
                    indexList.add(i);
                    maxRes = Math.max(maxRes, res);
                    res = res - second + first + 1;
                }
            } else {
                res++;
            }
        }
        return Math.max(maxRes, res);
    }

    /**
     * longestOnesGF1:
     * 时间
     * 33ms
     * 击败 6.72%使用 Java 的用户
     * 内存
     * 43.67MB
     * 击败 93.03%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums
     * @param k
     * @return int
     * @date 2023/10/14 23:46
     */
    public static int longestOnesGF1(int[] nums, int k) {
        int n = nums.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - nums[i - 1]);
        }

        int ans = 0;
        for (int right = 0; right < n; ++right) {
            //找left坐标，left值为P[right + 1] - k
            int left = binarySearch(P, P[right + 1] - k);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * longestOnesGF2:
     * 滑动窗口
     * 时间
     * 3ms
     * 击败 67.33%使用 Java 的用户
     * 内存
     * 44.01MB
     * 击败 42.54%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums
     * @param k
     * @return int
     * @date 2023/10/14 23:50
     */
    public static int longestOnesGF2(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * longestSubarray:
     * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
     *
     * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
     *
     * 如果不存在这样的子数组，请返回 0 。
     * 时间
     * 2ms
     * 击败 87.09%使用 Java 的用户
     * 内存
     * 53.01MB
     * 击败 81.82%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums  
     * @return int
     * @date 2023/10/15 0:00
     */
    public int longestSubarray(int[] nums) {
        int length =   nums.length;
        int ans=0,left=0,lsum=0,rsum=0;
        for (int i = 0; i < length; i++) {
            rsum += 1-nums[i];
            while (lsum<rsum-1){
                lsum += 1 - nums[left];
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        if (rsum==0){
            return length-1;
        }
        return ans-1;
    }

    /**
     * longestSubarray:
     * 递推
     * 时间
     * 4ms
     * 击败 12.01%使用 Java 的用户
     * 内存
     * 53.48MB
     * 击败 12.18%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums
     * @return int
     * @date 2023/10/15 0:19
     */
    public int longestSubarrayGF1(int[] nums) {
        int n = nums.length;

        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = nums[i] != 0 ? pre[i - 1] + 1 : 0;
        }

        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = nums[i] != 0 ? suf[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            //当i的值为0时，i-1和i+1就代表0左右的1的数量。
            int preSum = i == 0 ? 0 : pre[i - 1];
            int sufSum = i == n - 1 ? 0 : suf[i + 1];
            ans = Math.max(ans, preSum + sufSum);
        }

        return ans;
    }

    /**
     * longestSubarrayGF2:
     * 递推优化
     * 时间
     * 3ms
     * 击败 25.99%使用 Java 的用户
     * 内存
     * 53.23MB
     * 击败 53.86%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums
     * @return int
     * @date 2023/10/15 0:21
     */
    public int longestSubarrayGF2(int[] nums) {
        int ans = 0;
        int p0 = 0, p1 = 0;
        //p0就是前半段，p1就是整段
        for (int num : nums) {
            if (num == 0) {
                p1 = p0;
                p0 = 0;
            } else {
                ++p0;
                ++p1;
            }
            ans = Math.max(ans, p1);
        }
        if (ans == nums.length) {
            --ans;
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] arr = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int i = longestOnesGF1(arr, 3);
        System.out.println(i);
        String a = "中文";
//        char[] charArray = a.toCharArray();
//        for (int i = 0; i < charArray.length; i++) {
//            System.out.println(charArray[i]);
//        }

    }

}
