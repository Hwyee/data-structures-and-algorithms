package cn.hwyee.algorithms.leecode.leecode75;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName DoublePoint
 * @description 双指针
 * @date 2023/10/7
 * @since JDK 1.8
 */
public class DoublePoint {
    
    /**
     * moveZeroes:
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 快慢指针
     *
     * 时间
     * 32ms
     * 击败 11.28%使用 Java 的用户
     * 内存
     * 42.70MB
     * 击败 92.15%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums  
     * @return void
     * @date 2023/10/7 23:42
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        int length = nums.length;
        while( fast<length-1){
            while (slow<length-1 &&nums[slow]!=0){
                slow++;
            }
            fast=slow;
            while (fast<length-1 && nums[fast]==0){
                fast++;
            }
            swap(nums,slow,fast);
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
     * @author hui
     * @version 1.0
     * @param nums
     * @return void
     * @date 2023/10/8 0:15
     */
    public void moveZeroesGF(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }

    }

    /**
     * isSubSequence:
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * 进阶：
     *
     * 如果有大量输入的 S，称作 S1, S2, ... ,Sk 其中 k >= 10亿，
     * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * @author hui
     * @version 1.0
     * @param s
     * @param t
     * @return boolean
     * @date 2023/10/8 11:14
     */
    public boolean isSubSequence(String s,String t){
        int i=0,j=0;
        char[] sub = s.toCharArray();
        char[] ori = t.toCharArray();
        while (j<t.length()){//i<s.length() && j<t.length()
            if(i==s.length()){
                return true;
            }
            if (sub[i]==ori[j]){
                i++;
            }
            j++;
        }
        return i==s.length();
    }

    /**
     * isSubSequence:
     * 动态规划
     * 用一个二维数组保存字符串t的所有状态。
     * arr[i][j] 表示从字符串t索引i开始查找，字符j第一次所在的位置。
     * 此方法执行效率很低，但是如果有大量输入的 S，称作 S1, S2, ... ,Sk 其中 k >= 10亿，
     *   你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     *   就快了。
     *
     *   状态方程：
     *   f[i][j]={  i,              t[i]=j
     *           {   f[i+1][j],     t[i]!=j
     * @author hui
     * @version 1.0
     * @param s
     * @param t
     * @return boolean
     * @date 2023/10/8 15:48
     */
    public boolean isSubSequenceDP(String s,String t){
        int n=s.length(),m=t.length();
        int[][] f = new int[m+1][26];
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
    public  void swap(int[] nums,int a,int b) {
        int t = nums[a];
        nums[a]=nums[b];
        nums[b]=t;
    }
}
