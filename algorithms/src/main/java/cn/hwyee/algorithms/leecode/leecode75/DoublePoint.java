package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName DoublePoint
 * @description 双指针
 * @date 2023/10/7
 * @since JDK 1.8
 */
@Slf4j
public class DoublePoint {

    /**
     * moveZeroes:
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 快慢指针
     * <p>
     * 时间
     * 32ms
     * 击败 11.28%使用 Java 的用户
     * 内存
     * 42.70MB
     * 击败 92.15%使用 Java 的用户
     *
     * @param nums
     * @return void
     * @author hui
     * @version 1.0
     * @date 2023/10/7 23:42
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        int length = nums.length;
        while (fast < length - 1) {
            while (slow < length - 1 && nums[slow] != 0) {
                slow++;
            }
            fast = slow;
            while (fast < length - 1 && nums[fast] == 0) {
                fast++;
            }
            swap(nums, slow, fast);
        }
    }

    /**
     * moveZeroesGF:
     * 快排思想，选一个基准点0.
     * 时间
     * 1ms
     * 击败 99.99%使用 Java 的用户
     * 内存
     * 42.97MB
     * 击败 51.11%使用 Java 的用户
     *
     * @param nums
     * @return void
     * @author hui
     * @version 1.0
     * @date 2023/10/8 0:15
     */
    public void moveZeroesGF(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }

    }

    /**
     * isSubSequence:
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 进阶：
     * <p>
     * 如果有大量输入的 S，称作 S1, S2, ... ,Sk 其中 k >= 10亿，
     * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     *
     * @param s
     * @param t
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/10/8 11:14
     */
    public boolean isSubSequence(String s, String t) {
        int i = 0, j = 0;
        char[] sub = s.toCharArray();
        char[] ori = t.toCharArray();
        while (j < t.length()) {//i<s.length() && j<t.length()
            if (i == s.length()) {
                return true;
            }
            if (sub[i] == ori[j]) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * isSubSequence:
     * 动态规划
     * 用一个二维数组保存字符串t的所有状态。
     * arr[i][j] 表示从字符串t索引i开始查找，字符j第一次所在的位置。
     * 此方法执行效率很低，但是如果有大量输入的 S，称作 S1, S2, ... ,Sk 其中 k >= 10亿，
     * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * 就快了。
     * <p>
     * 状态方程：
     * f[i][j]={  i,              t[i]=j
     * {   f[i+1][j],     t[i]!=j
     *
     * @param s
     * @param t
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/10/8 15:48
     */
    public boolean isSubSequenceDP(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] f = new int[m + 1][26];
        //t的索引最大值是m-1，如果j出现的位置是m，则说明没找到对应子序列的字符。
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a') {
                    f[i][j] = i;
                } else {
                    //没找到就将索引+1，这样最终会找到f[m][j]=m;这也是倒叙动态规划的原因。
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            //更新要找的索引位置。
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;

    }

    /**
     * maxArea:
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * <p>
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 返回容器可以储存的最大水量。
     * <p>
     * 说明：你不能倾斜容器。
     * <p>
     * 只要向中间移动，长度就一定会变小，想要面积变大，高度就一定要变高才行.所以将短板向中间移动
     * 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 −1-1−1​ 变短：
     * <p>
     * 若向内 移动短板 ，水槽的短板 min(h[i],h[j])min(h[i], h[j])min(h[i],h[j]) 可能变大，因此下个水槽的面积 可能增大 。
     * 若向内 移动长板 ，水槽的短板 min(h[i],h[j])min(h[i], h[j])min(h[i],h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
     *
     * @param height
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/8 19:18
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        //x是横坐标，容器的长
        int x = 0;
        int res = 0;
        while (left < right) {
            res =
                    height[left] < height[right] ?
                            Math.max(res, (right - left) * height[left++]) :
                            height[left] == height[right] ?
                                    Math.max(res, (right - left++) * height[right--]) :
                                    Math.max(res, (right - left) * height[right--]);


        }
        return res;
    }

    /**
     * maxOperations:
     * 给你一个整数数组 nums 和一个整数 k 。
     * <p>
     * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
     * <p>
     * 返回你可以对数组执行的最大操作数。
     *
     * @param nums
     * @param k
     * @return int
     * @author hui
     * @version 1.0
     * @date 2023/10/8 23:45
     */
    public int maxOperations(int[] nums, int k) {
        quickSort(nums,0,nums.length-1);
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        int res=0;
        if(i<=j){
            while (nums[j--]==nums[i]){
                res++;
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 8, 0, 5, 111, 0, 2, 88, 7};
        //1. 冒泡
//        bubbleSort(a);
        //2. 快排
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
//        Arrays.stream(a).forEach(t -> log.info(String.valueOf(t)));
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int i = quickSortSwap(nums, start, end);
            quickSort(nums, start, i - 1);
            quickSort(nums, i + 1, end);
        }
    }

    /**
     * quickSortSwap:
     * 快排三数取中。
     *
     * @param nums
     * @param start
     * @param end
     * @return void
     * @author hui
     * @version 1.0
     * @date 2023/10/9 0:14
     */
    public static int quickSortSwap(int[] nums, int start, int end) {
        int mid = start + ((end - start) >> 1);
        if (nums[mid] > nums[end]) {
            swap(nums, mid, end);
        }
        if (nums[start] > nums[end]) {
            swap(nums, start, end);
        }
        if (nums[mid] > nums[start]) {
            swap(nums, mid, start);
        }
        //此时，nums[mid] <= nums[start] <= nums[end]
        int base = nums[start];
        int i = start, j = end + 1;
        while (true) {
            while (  nums[++i] < base && i < end) {
            }
            while ( nums[--j] > base) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        nums[start] = nums[j];
        nums[j] = base;
//        for (int i = start + 1; i <= end; i++) {
//            if (nums[i] < base) {
//                swap(nums, i, ++baseIndex);
//            }
//        }
//        swap(nums, start,baseIndex);
        return j;
    }

    public static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
