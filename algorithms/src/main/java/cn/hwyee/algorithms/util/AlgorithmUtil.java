package cn.hwyee.algorithms.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName AlgorithmUtil
 * @description 算法工具类
 * @date 2024/3/8
 * @since JDK 1.8
 */
@Slf4j
public class AlgorithmUtil {
    public static void main(String[] args) {
        // test power
        // 底数
        long a = 2;
        // 指数
        long b = 10;
        long result = power(a, b);
        log.info(a + "^" + b + " = " + result);
    }
    /**
     * power:
     * 快速幂算法:
     * 快速幂算法的基本思想是通过将指数b 表示为二进制形式，并利用指数的二进制展开来降低计算复杂度。具体过程如下：
     * 1.将指数b 转换为二进制形式。
     * 2.将底数a 不断平方，同时根据指数的二进制位来决定是否进行乘法操作。
     * 3.重复上述步骤，直到指数的所有二进制位都被处理完毕。
     * @author hui
     * @version 1.0
     * @param a
     * @param b
     * @return long
     * @date 2024/3/8 0:09
     */
    public static long power(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result *= a;
            }
            a *= a;
            b >>= 1;
        }
        return result;
    }

}
