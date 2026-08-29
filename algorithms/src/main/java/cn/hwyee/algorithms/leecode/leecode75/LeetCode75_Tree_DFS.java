package cn.hwyee.algorithms.leecode.leecode75;

import cn.hwyee.datastructures.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName LeetCode75_Tree_DFS
 * @description
 * @date 2024/7/26
 * @since JDK 1.8
 */
@Slf4j
public class LeetCode75_Tree_DFS {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     *
     * 给定一个二叉树 root ，返回其最大深度。
     *
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     */
    class Solution1 {
        public int maxDepth(TreeNode root) {
            int ans = 0;
            if (root == null) {
                return ans;
            }
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
     *
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     *
     * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
     *
     *
     */
    class Solution2 {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            return true;
        }
    }

}


class Solution213 {
    public static void main(String[] args) {
        lengthOfLongestSubsequence(Arrays.asList(1,2,3,4,5),9);

    }
    public static int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int ans = 0;
        int[] dp = new int[nums.size()];
        Arrays.fill(dp,-1);
        dp[0] = -1;
        int max = -1;
        dfs(nums, target, 0,0, ans, dp);
        return dp[0];
    }

    public static void dfs(List<Integer> nums,int target,int l,int r,int ans,int[] dp){
        if(r>=nums.size()){
            return ;
        }
        ans +=nums.get(r);
        while(ans > target && r>l){
            l++;
            ans-=nums.get(l);
        }
        if(ans==target){
            dp[0]=Math.max(r-l+1,dp[0]);
        }
        r++;
        dfs(nums, target, l, r, ans, dp);
    }


    int[][] dp;

    public int lengthOfLongestSubsequence4(List<Integer> nums, int target) {
        int ans = 0;
        dp = new int[nums.size()][target+1];

        dfs(nums,target,nums.size()-1) ;
        for(int i = nums.size();i>=0;i--){
            if(dp[i-1][target]==1){
                return i;
            }
        }
        return -1 ;
    }

    public int dfs(List<Integer> nums,int target,int i){
        if(dp[i][target]>0){
            return dp[i][target];
        }
        if(i<0 ){
            if(target ==0){
                return 1;
            }else {
                return Integer.MIN_VALUE;
            }
        }
        dp[i][target]=Math.max(dfs(nums,target-nums.get(i),i-1),dfs(nums,target,i-1));
        return dp[i][target];
    }
}