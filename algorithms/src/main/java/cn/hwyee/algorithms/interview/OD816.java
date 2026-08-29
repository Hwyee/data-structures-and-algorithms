package cn.hwyee.algorithms.interview;

import ch.qos.logback.core.joran.conditional.IfAction;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName OD816
 * @description
 * @date 2024/8/17
 * @since JDK 1.8
 */
@Slf4j
public class OD816 {
    {
        "".charAt(1);
    }
    public static void main(String[] args) {
        pizza();
//        kingHonorTeamBalance();

//        reflectCount();

    }
    static int[] pizzaSize;
    static int[][] dppz;
    
    /**
     * pizza:
     * “吃货”和“馋嘴”两人到披萨店点了一份铁盘(圆形)披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。
     *
     * 但是粗心服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
     *
     * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法:从“吃货”开始，轮流取披萨。
     *
     * 除了第-块披萨可以任意选取以外，其他都必须从缺口开始选。 他俩选披萨的思路不同。
     *
     * “馋嘴”每次都会选最大块的拨萨，而且“吃货”知道“馋嘴”的想法。
     *
     * 已知披萨小块的数量以及每块的大小，求“吃货”能分得的最大的披萨大小的总和。
     * 第一行 披萨块的大小 N 3<=N <500
     * 2行-N+1行 每行一个数字表示第i块披萨的大小 [1,2147483647]。
     * 输入：
     * 5
     * 8
     * 2
     * 10
     * 5
     * 7
     *
     * 输出：
     * 19
     * @author hui
     * @version 1.0 
     * @return void
     * @date 2024/8/17 14:16
     */
    public static void pizza(){
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        pizzaSize = new int[i];
        for (int j = 0; j < i; j++) {
            pizzaSize[j] = scanner.nextInt();
        }
        int totalSize = 0;
        dppz = new int[i][i];
        for (int j = 0; j < i; j++) {
//            totalSize = Math.max(totalSize,maxPizza(j-1,j+1)+pizzaSize[j]);
            totalSize = Math.max(totalSize,pizza(j-1,j+1)+pizzaSize[j]);
        }
        System.out.println(totalSize);
    }
    static int tempPizza = 0;
    public static int maxPizza(int x,int y){
        x = shiftIndex(x);
        y = shiftIndex(y);
        if (shiftIndex(x-1) == y){
            return Math.min(pizzaSize[x], pizzaSize[y]);
        }
        if (dppz[x][y] != 0){
            return dppz[x][y];
        }
        if (pizzaSize[x] > pizzaSize[y]){
            dppz[x][y]  = Math.max(pizzaSize[y] + maxPizza(x - 1, y + 1), pizzaSize[shiftIndex(x - 1)] + maxPizza(x - 2, y));
        }else {
            dppz[x][y] = Math.max(pizzaSize[x] + maxPizza(x - 1, y + 1), pizzaSize[shiftIndex(y + 1)] + maxPizza(x, y + 2));
        }
        return dppz[x][y];
    }

    public static int shiftIndex(int i){
        if (i < 0){
            return i + pizzaSize.length;
        }else if(i >= pizzaSize.length){
            return i - pizzaSize.length;
        }
        return i;
    }
    public static int si(int i ){
        //Math.abs(i)
//        return (i + pizzaSize.length) % pizzaSize.length;
        return 1;

    }

    public static int pizza(int i,int j){
        i = shiftIndex(i);
        j = shiftIndex(j);
        if(dppz[i][j]>0){
            return dppz[i][j];
        }
        if(pizzaSize[i]>pizzaSize[j]){
            i = shiftIndex(i-1);
        }else {
            j = shiftIndex(j+1);
        }
        if (i == j){
            dppz[i][j] =  pizzaSize[i];
        }else{
            dppz[i][j] = Math.max(pizza(shiftIndex(i-1),j)+pizzaSize[i],pizza(i,shiftIndex(j+1))+pizzaSize[j]);
        }
        return dppz[i][j];
    }


    /**
     * kingHonorTeamBalance:
     * 部门准备举办一场王者荣耀表演赛，有10名游戏爱好者参与，分5为两队，每队5人。每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，
     * 我们需要把10名参赛者分为实力尽量相近的两队。一队的实力可以表示为这一队5名队员的评分总和。现在给你10名参与者的游戏水平评分，请你根据上述要求分队最后输出这两组的实力差绝对值。
     * 例: 10名参赛者的评分分别为5 1 8 3 4 6 7 10 9 2，分组为 (135 8 10) (24 679)，两组实力差最小，差值为1。有多种分法，但实力差的绝对值最小为1。
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/8/17 10:45
     */
    public static void kingHonorTeamBalance(){
        Scanner scanner = new Scanner(System.in);
        int[] scores = new int[10];
        for (int i = 0; i < 10; i++) {
            scores[i] = scanner.nextInt();
        }
        int totalN = 0;
        for (int i = 0; i < 10; i++) {
            totalN += scores[i];
        }
        int target = totalN / 2;
        // 动态规划数组，dp[i][j] 表示前 i 个选手是否可以组成评分为 j 的组合
        boolean[][] dp = new boolean[6][target+1];
        dp[0][0] = true;  // 初始化：选择 0 个选手时，分数为 0 是可能的

        // 填充 dp 数组
        for (int i = 0; i < 10; i++) {
            for (int j = 5; j > 0; j--) { // 必须从后往前填充，以防止状态被重复使用
                for (int k = target; k >= scores[i]; k--) {
                    dp[j][k] = dp[j][k] || dp[j - 1][k - scores[i]];
                }
            }
        }

        // 寻找最接近 target 的可行子集和
        int bestSum = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[5][j]) {
                bestSum = j;
                break;
            }
        }

        // 输出两队的实力差
        int result = Math.abs(totalN - 2 * bestSum);
        System.out.println(result);
    }

    /**
     * reflectCount:反射计数
     * 给定一个包含 0 和 1 的二维矩阵，给定一个初始位置和速度，一个物体从给定的初始位置触发, 在给定的速度下进行移动, 遇到矩阵的边缘则发生镜面反射。
     * 无论物体经过 0 还是 1, 都不影响其速度
     * 请计算并给出经过 t 时间单位后, 物体经过 1 点的次数
     * 矩阵以左上角位置为[0, 0](列(x), 行(行))
     * 第一行为初始信息
     * w，h 为矩阵的宽和高
     * x，y 为起始位置
     * sx，sy 为初始速度 -1 ~ 1之间
     * t 为经过的时间
     * 第二行开始一共 h 行，为二维矩阵信息
     * 12 7 2 1 1 -1 13
     *  001000010000
     *  001000010000
     *  001000010000
     *  001000010000
     *  001000010000
     *  001000010000
     *  001000010000
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/8/18 21:11
     */
    public static void reflectCount(){
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int y = scanner.nextInt();//列
        int x = scanner.nextInt();
        int sy = scanner.nextInt();
        int sx = scanner.nextInt();
        int t = scanner.nextInt();
        char[][] grid = new char[h][w];
        for (int i = 0; i < h; i++) {
            grid[i] = scanner.next().toCharArray();
        }
        System.out.println(count(x,y,sx,sy,t,grid,0));
    }

    public static int count(int x, int y, int sx, int sy, int t, char[][] grid,int count) {
        if(grid[x][y] == '1'){
            count++;
        }
        if(t == 0){
            return count;
        }
        x+=sx;
        y+=sy;
        if(x<0 || x>=grid.length){
            sx=-sx;
            x+=2*sx;
        }
        if(y<0 || y>=grid[0].length){
            sy=-sy;
            y+=2*sy;
        }
        t--;
        return count(x,y,sx,sy,t,grid,count);
    }

}
