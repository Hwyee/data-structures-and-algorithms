package cn.hwyee.algorithms.leecode.daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Daily202406
 * @description
 * @date 2024/6/1
 * @since JDK 1.8
 */
@Slf4j
public class Daily202406 {

    public static void main(String[] args) {
        Solution_6_1 solution61 = new Solution_6_1();
        solution61.minimumSteps("0111");
    }

    /**
     * 2928. 给小朋友们分糖果 I:
     * 给你两个正整数 n 和 limit 。
     *
     * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/2 13:20
     */
    class Solution_1_1 {
        public int distributeCandies(int n, int limit) {

            if (n <= limit){
                return 3*limit+1;
            }
            else {
                int mod = n%3;

            }
            return 0;
        }

        public int distributeCandiesLS(int n, int limit) {
            return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
        }

        private int c2(int n) {
            return n > 1 ? n * (n - 1) / 2 : 0;
        }
    }
    
    
    /**
     * 575. 分糖果:
     * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
     *
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     *
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/2 13:22
     */
    class Solution_2_1 {
        public int distributeCandies(int[] candyType) {
            Arrays.sort(candyType);
            int ans = 1;
            for (int i = 0; i < candyType.length-1; i++) {
                if (ans == candyType.length/2){
                    return ans;
                }
                if (candyType[i] != candyType[i+1]) {
                    ans++;
                }

            }
            return ans;
        }
    }

    /**
     * 1103. 分糖果 II:
     * 排排坐，分糖果。
     *
     * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
     *
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
     *
     * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
     *
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     *
     * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/3 21:26
     */
    class Solution_3_1 {
        public int[] distributeCandies(int candies, int num_people) {
            int i = 1;
            int sum = 1;
            int[] ans = new int[num_people];
            int j = 0;
            for(;sum < candies;i++,sum+=i,j++){
                ans[j%num_people] = ans[j%num_people] + i;
            }
            if(sum > candies ){
                i = i-(sum - candies);
            }
            ans[j%num_people] = ans[j%num_people] + i;
            return ans;
        }
    }

    /**
     * 3067. 在带权树网络中统计可连接服务器对数目:
     * 给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。同时给你一个数组 edges ，
     * 其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。再给你一个整数 signalSpeed 。
     *
     * 如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
     *
     * a < b ，a != c 且 b != c 。
     * 从 c 到 a 的距离是可以被 signalSpeed 整除的。
     * 从 c 到 b 的距离是可以被 signalSpeed 整除的。
     * 从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
     * 请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/5 9:25
     */
    class Solution_4_1 {
        public int[] countPairsOfConnectableServersGF(int[][] edges, int signalSpeed) {
            int n = edges.length + 1;
            List<int[]>[] graph = new ArrayList[n];
            Arrays.setAll(graph, i -> new ArrayList<>());

            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int w = e[2];
                graph[u].add(new int[]{v, w});
                graph[v].add(new int[]{u, w});
            }

            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                int pre = 0;
                for (int[] e : graph[i]) {
                    int cnt = dfs(e[0], i, e[1] % signalSpeed, signalSpeed, graph);
                    res[i] += pre * cnt;
                    pre += cnt;
                }
            }
            return res;
        }

        private int dfs(int p, int root, int curr, int signalSpeed, List<int[]>[] graph) {
            int res = 0;
            if (curr == 0) {
                res++;
            }
            for (int[] e : graph[p]) {
                int v = e[0];
                int cost = e[1];
                if (v != root) {
                    res += dfs(v, p, (curr + cost) % signalSpeed, signalSpeed, graph);
                }
            }
            return res;
        }
    }


    class BinaryIndexedTree {
        private int[] tree;

        public BinaryIndexedTree(int n) {
            tree = new int[n + 1];
        }

        public void add(int i) {
            while (i < tree.length) {
                tree[i]++;
                i += i & -i;
            }
        }

        public int get(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    
    /**
     * 3072. 将元素分配到两个数组中 II:
     * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
     *
     * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
     *
     * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。在第一次操作中，将 nums[1] 追加到 arr1 。在第二次操作中，将 nums[2] 追加到 arr2 。之后，在第 i 次操作中：
     *
     * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
     * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
     * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
     * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
     * 连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
     *
     * 返回整数数组 result 。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/5 23:42
     */
    class Solution_5_1 {
        public int[] resultArrayGF(int[] nums) {
            int n = nums.length;
            int[] sortedNums = Arrays.copyOf(nums, n);
            Arrays.sort(sortedNums);

            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < n; i++) {
                index.put(sortedNums[i], i + 1);
            }

            List<Integer> arr1 = new ArrayList<>(List.of(nums[0]));
            List<Integer> arr2 = new ArrayList<>(List.of(nums[1]));
            BinaryIndexedTree tree1 = new BinaryIndexedTree(n);
            BinaryIndexedTree tree2 = new BinaryIndexedTree(n);
            tree1.add(index.get(nums[0]));
            tree2.add(index.get(nums[1]));

            for (int i = 2; i < n; i++) {
                int count1 = arr1.size() - tree1.get(index.get(nums[i]));
                int count2 = arr2.size() - tree2.get(index.get(nums[i]));
                if (count1 > count2 || (count1 == count2 && arr1.size() <= arr2.size())) {
                    arr1.add(nums[i]);
                    tree1.add(index.get(nums[i]));
                } else {
                    arr2.add(nums[i]);
                    tree2.add(index.get(nums[i]));
                }
            }

            int i = 0;
            for (int a: arr1) {
                nums[i++] = a;
            }
            for (int a: arr2) {
                nums[i++] = a;
            }
            return nums;
        }
    }

    /**
     * 2938. 区分黑球与白球:
     * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
     *
     * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
     *
     * 在每一步中，你可以选择两个相邻的球并交换它们。
     *
     * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
     * @author hui
     * @version 1.0 
     * @return 
     * @date 2024/6/6 20:03
     */
    static class Solution_6_1 {
        public long minimumSteps(String s) {
            char[] charArray = s.toCharArray();

            long blackCount = 0;
            long blackIndex = 0;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == '1'){
                    blackCount++;blackIndex+=i;
                }

            }
            long realBlackIndex = (s.length() * 2L - blackCount - 1)  * blackCount / 2 ;
            return realBlackIndex - blackIndex;
//            while (flag){
//                int temp = ans;
//                firstBB = false;
//                for (int i = begin; i < charArray.length-1; i++) {
//                    if (charArray[i] == '1' && charArray[i+1] == '0'){
//                        charArray[i] = '0';
//                        charArray[i+1] = '1';
//                        ans++;
//                    }else if ( !firstBB && charArray[i] == '1' && charArray[i+1] == '1' ){
//                        begin=i;
//                        firstBB = true;
//                    }else if (firstBB && charArray[i] == '0' && charArray[i+1] == '0' ){
//                        i++;
//                    }
//                }
//                if (temp == ans){
//                    flag = false;
//                }
//            }
//            return ans;
        }

        public long minimumStepsGF(String s) {
            int n = s.length();
            long count = 0;
            int index = 0;
            for(int i = 0; i < n; i++){
                if(s.charAt(i) == '0'){
                    count += (i - index);
                    index++;
                }
            }
            return count;
        }
    }


}
