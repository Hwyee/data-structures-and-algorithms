package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
}

