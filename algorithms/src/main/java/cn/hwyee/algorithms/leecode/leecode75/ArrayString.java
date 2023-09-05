package cn.hwyee.algorithms.leecode.leecode75;

import lombok.extern.slf4j.Slf4j;

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
}
