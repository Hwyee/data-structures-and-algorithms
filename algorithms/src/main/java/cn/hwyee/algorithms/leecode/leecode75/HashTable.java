package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hui
 * @version 1.0
 * @className HashTable
 * @description
 * @date 2023/10/16
 * @since JDK 1.8
 */
@Slf4j
public class HashTable {

    /**
     * findDifference:
     * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：
     *
     * answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
     * answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
     * 注意：列表中的整数可以按 任意 顺序返回。
     *
     * 时间
     * 9ms
     * 击败 91.92%使用 Java 的用户
     * 内存
     * 42.42MB
     * 击败 35.83%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param nums1
    * @param nums2
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @date 2023/10/16 16:57
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        int length1 = nums1.length;
        int length2 = nums2.length;
        for (int i : nums1) {
            map1.put(i,1);
        }
        for(int i : nums2){
            map2.put(i,1);
        }
        ArrayList<List<Integer>> arrayList = new ArrayList<>();
        ArrayList<Integer> num1List = new ArrayList<>();
        ArrayList<Integer> num2List = new ArrayList<>();
        Set<Integer> set1 = map1.keySet();
        Set<Integer> set2 = map2.keySet();
        for (Integer integer : set1) {
            if (!map2.containsKey(integer)) {
                num1List.add(integer);
            }
        }
        for (Integer integer : set2) {
            if (!map1.containsKey(integer)) {
                num1List.add(integer);
            }
        }
        arrayList.add(num1List);
        arrayList.add(num2List);
        return arrayList;

    }

    /**
     * uniqueOccurrences: 
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * 时间
     * 3ms
     * 击败 11.29%使用 Java 的用户
     * 内存
     * 38.61MB
     * 击败 37.59%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param arr  
     * @return boolean
     * @date 2023/10/16 19:54
     */
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
                map.put(i,map.getOrDefault(i,0)+1);

        }
        HashSet<Integer> set = new HashSet<>();
        //这个题用entrySet遍历更快
//        map.forEach((k,v)->set.add(v));
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            set.add(integerIntegerEntry.getValue());
        }
        return set.size()==map.size();
    }

    /**
     * closeStrings:
     * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
     *
     * 操作 1：交换任意两个 现有 字符。
     * 例如，abcde -> aecdb
     * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
     * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
     * 你可以根据需要对任意一个字符串多次使用这两种操作。
     *
     * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
     *
     * 时间
     * 详情
     * 69ms
     * 击败 26.54%使用 Java 的用户
     * 内存
     * 详情
     * 43.48MB
     * 击败 5.05%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param word1
     * @param word2
     * @return boolean
     * @date 2023/10/16 21:09
     */
    public boolean closeStrings(String word1, String word2) {
        //首先，长度得相同
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 != length2) {return false;}
        //其次，两个字符串里面包含的字符种类和数量得相同
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < length1; i++) {
            map1.put(charArray1[i],map1.getOrDefault(charArray1[i],0)+1);
            map2.put(charArray2[i],map2.getOrDefault(charArray2[i],0)+1);
        }
        if (map1.size()!=map2.size()){
            return false;
        }
        Set<Map.Entry<Character, Integer>> entries1 = map1.entrySet();
        Set<Map.Entry<Character, Integer>> entries2 = map2.entrySet();
        HashMap<Integer, Integer> value1 = new HashMap<>();
        HashMap<Integer, Integer> value2 = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : entries1) {
            if (map2.containsKey(entry.getKey())){
                value1.put(entry.getValue(),value1.getOrDefault(entry.getValue(),0)+1);
                value2.put(map2.get(entry.getKey()),value2.getOrDefault(map2.get(entry.getKey()),0)+1);

                //这个包含不了所有的情况

//                if (!map2.values().contains(entry.getValue()) ){
//                    return false;
//                }

            } else {
                return false;
            }

        }
        if (value1.size()!=value2.size()) {return false;}
        for (Map.Entry<Integer, Integer> odd : value1.entrySet()) {
            if (value2.containsKey(odd.getKey())){
                if ((odd.getValue()+value2.get(odd.getKey()))%2!=0){
                    return false;
                }
            }else {
                return false;
            }

        }
        return true;
    }

    /**
     * closeStrings:
     * 大神题解
     * 时间
     * 9ms
     * 击败 98.15%使用 Java 的用户
     * 内存
     * 42.29MB
     * 击败 68.75%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param word1
     * @param word2
     * @return boolean
     * @date 2023/10/16 22:16
     */
    public boolean closeStringsTJ(String word1, String word2) {
        int[] c1 = new int[26], c2 = new int[26];
        for (char c : word1.toCharArray()) c1[c - 'a']++;
        for (char c : word2.toCharArray()) c2[c - 'a']++;
        //检查字符种类是否相同
        for (int i = 0; i < 26; i++) {
            if (c1[i] + c2[i] == 0) continue;
            if (c1[i] == 0 || c2[i] == 0) return false;
        }
        Arrays.sort(c1); Arrays.sort(c2);
        //检查字符频次是否相同
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;

    }

    /**
     * equalPairs: 2352. 相等行列对。
     * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
     *
     * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
     *
     * 可能字符串相加太耗时了。
     *
     * 时间
     * 174ms
     * 击败 5.10%使用 Java 的用户
     * 内存
     * 45.89MB
     * 击败 84.47%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param grid  
     * @return int
     * @date 2023/10/16 22:17
     */
    public static int equalPairs(int[][] grid) {
        int length = grid.length;
        String[] row = new String[length];
        String[] cow = new String[length];
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i <length; i++) {
            sb1.setLength(0);
            sb2.setLength(0);
            for (int j = 0; j < length; j++) {
                sb1.append(grid[i][j]).append(",");
                sb2.append(grid[j][i]).append(",");
            }
            row[i]=sb1.toString();
            cow[i]=sb2.toString();
        }
        int res=0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (row[i].equals(cow[j])) {
                    res++;
                }
            }
        }
        return res;
    }


    /**
     * equalPairsGFHSAH:
     * 官方hash。
     * 时间
     * 21ms
     * 击败 73.10%使用 Java 的用户
     * 内存
     * 50.09MB
     * 击败 20.73%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param grid
     * @return int
     * @date 2023/10/16 22:56
     */
    public int equalPairsGFHSAH(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> cnt = new HashMap<List<Integer>, Integer>();
        for (int[] row : grid) {
            List<Integer> arr = new ArrayList<Integer>();
            for (int num : row) {
                arr.add(num);
            }
            cnt.put(arr, cnt.getOrDefault(arr, 0) + 1);
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            List<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                arr.add(grid[i][j]);
            }
            if (cnt.containsKey(arr)) {
                res += cnt.get(arr);
            }
        }
        return res;

    }


    /**
     * equalPairsGFBAOLI:
     * 官方，模拟。对任意一行，将它与每一列都进行比较，如果相等，则对结果加一，最后返回总数。
     * 时间复杂度On3
     * 时间
     * 48ms
     * 击败 25.29%使用 Java 的用户
     * 内存
     * 46.26MB
     * 击败 65.88%使用 Java 的用户
     * @author hui
     * @version 1.0
     * @param grid
     * @return int
     * @date 2023/10/16 22:57
     */
    public int equalPairsGFBAOLI(int[][] grid) {
        int res = 0, n = grid.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (equal(row, col, n, grid)) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean equal(int row, int col, int n, int[][] grid) {
        for (int i = 0; i < n; i++) {
            if (grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] a = {1,2};
//        boolean b = uniqueOccurrences(a);
//        log.info("uniqueOccurrences -> " +String.valueOf(b));
        int[][] aa = {{3,2,1},{1,7,6},{2,7,7}};
        System.out.println(equalPairs(aa));

    }
}

