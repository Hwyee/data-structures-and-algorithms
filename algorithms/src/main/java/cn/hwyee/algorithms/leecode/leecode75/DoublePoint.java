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
    public  void swap(int[] nums,int a,int b) {
        int t = nums[a];
        nums[a]=nums[b];
        nums[b]=t;
    }
}
