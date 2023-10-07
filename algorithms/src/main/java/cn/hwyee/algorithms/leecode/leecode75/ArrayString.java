package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ArrayString
 * @description
 * @date 2023/8/30
 * @since JDK 1.8
 */
@Slf4j
public class ArrayString {

    /**
     * mergeAlternately:
     * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
     * 返回 合并后的字符串 。
     * 输入：word1 = "abc", word2 = "pqr"
     * 输出："apbqcr"
     * 解释：字符串合并情况如下所示：
     * word1：  a   b   c
     * word2：    p   q   r
     * 合并后：  a p b q c r
     * @author hui
     * @version 1.0
     * @param word1
     * @param word2
     * @return java.lang.String
     * @date 2023/8/30 23:45
     */
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;

        StringBuilder ans = new StringBuilder();
        while (i < m || j < n) {
            if (i < m) {
                ans.append(word1.charAt(i));
                ++i;
            }
            if (j < n) {
                ans.append(word2.charAt(j));
                ++j;
            }
        }
        return ans.toString();
    }

    /**
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
     *
     * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。
     * 注意，允许有多个孩子同时拥有 最多 的糖果数目。:
     * 输入：candies = [2,3,5,1,3], extraCandies = 3
     * 输出：[true,true,true,false,true]
     * 解释：
     * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     *
     * @author hui
     * @version 1.0
     * @param null
     * @return
     * @date 2023/9/26 13:37
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        ArrayList<Boolean> results = new ArrayList<>();
        for (int candy : candies) {
            results.add(candy + extraCandies >= max);
        }
        return results;
    }

    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
     * 另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/9/26 13:45
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length==1&&flowerbed[0]==0){
            return n-1<=0;
        }
        int canPlanting = 0;
        int max  = flowerbed.length-1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0){
                canPlanting++;
            }else {
                canPlanting = 0;
            }
            if ((i == 1 || i== max) && canPlanting>=2){
                n--;
                canPlanting=1;
            }
            if (canPlanting==3){
                n--;
                canPlanting=1;
            }
        }
        return n<=0;
    }

    /**
     * :给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
     *
     * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
     * 示例 1：
     *
     * 输入：s = "hello"
     * 输出："holle"
     * 示例 2：
     *
     * 输入：s = "leetcode"
     * 输出："leotcede"
     *
     * @author hui
     * @version 1.0
     * @param null
     * @return
     * @date 2023/9/26 14:40
     */
    public String reverseVowels(String s) {

        char[] charArray = s.toCharArray();
        List<Character> vowel = new ArrayList(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        List<Integer> vowelIndexList = new ArrayList<>();
        int one =-1 ;
        int second = charArray.length;
        boolean change = false;
        while(one <=second){
            for (int i = one+1; i < second ; i++) {
                if (vowel.contains(charArray[i])){
                    one =i;
                    change = true;
                    break;
                }
            }
            if (!change) {
                break;
            }
            change = false;
            for (int i = second-1; i > one ; i--) {
                if (vowel.contains(charArray[i])){
                    second =i;
                    change = true;
                    break;
                }
            }
            if (change) {
               char temp = charArray[one];
               charArray[one] = charArray[second];
               charArray[second] = temp;
               change = false;
            }else {
                break;
            }
        }
//        for (int i = 0; i < charArray.length; i++) {
//            if (vowel.contains(charArray[i])){
//                vowelIndexList.add(i);
//            }
//        }
//        for (int i = 0; i < (vowelIndexList.size())/2; i++) {
//            Integer start = vowelIndexList.get(i);
//            Integer end = vowelIndexList.get(vowelIndexList.size()-i-1);
//            char temp = charArray[start];
//            charArray[start] = charArray[end];
//            charArray[end] = temp;
//        }
        return String.valueOf(charArray);
    }
    /**
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     *
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     *
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     *
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。: 
     *
     * 时间
     * 详情
     * 7ms
     * 击败 51.15%使用 Java 的用户
     * 内存
     * 41.47MB
     * 击败 16.20%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param s
     * @return String
     * @date 2023/10/7 9:32
     */
    public String reverseWords(String s) {
        String[] ss = s.trim().replaceAll("\\s+", " ").split(" ");
        StringBuilder sb =  new StringBuilder();
        for (int i = ss.length-1; i >=0; i--) {
            sb.append(ss[i]).append(" ");
        }
        return sb.substring(0,sb.length()-1);

    }

    /**
     * productExceptSelf: 
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     *
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     *
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * @author hui
     * @version 1.0
     * @param nums  
     * @return int[]
     * @date 2023/10/7 16:54
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int res[] = new int[length];
        //索引0的左边数组乘积是1.
        res[0]=1;
        //从索引1开始遍历。
        for (int i = 1; i < length; i++) {
            res[i] = nums[i-1] * res[i-1];
        }
        //索引的右边数组乘积,不用数组保存了，直接用一个结果保存,然后实时与左边的结果乘积合并。
        int R = 1;
        for (int i = length-1;i>=0 ;i--) {
            res[i]=res[i]*R;
            R *= nums[i];
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "hello";
//        ArrayString arrayString = new ArrayString();
//        System.out.println(arrayString.reverseVowels(s));
        int a = 1;
        int b = a = 2;
        System.out.println("a:"+ a +"\nb:"+b);
    }
}


/**
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 官方答案:
 *
 * @author hui
 * @version 1.0
 * @return 
 * @date 2023/10/7 9:31
 */
class reverseVowels{
    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}