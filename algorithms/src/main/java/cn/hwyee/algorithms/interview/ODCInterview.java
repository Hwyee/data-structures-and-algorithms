package cn.hwyee.algorithms.interview;

import java.util.HashMap;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Interview
 * @description ODC面试，寻找第一个不重复的str
 * @date 2023/10/13
 * @since JDK 1.8
 */
public class ODCInterview {
    public String findFirstChar(String input) {
        // write code here
        char[] charArray = input.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < charArray.length; i++) {
            if (map.get(charArray[i])==null){
                map.put(charArray[i],1);
            }else {
                map.put(charArray[i],map.get(charArray[i])+1);
            }
        }
        for (int i = 0; i < charArray.length; i++) {
            if (map.get(charArray[i])==1){
                return String.valueOf(charArray[i]);
            }
        }
        return "";
    }
}
