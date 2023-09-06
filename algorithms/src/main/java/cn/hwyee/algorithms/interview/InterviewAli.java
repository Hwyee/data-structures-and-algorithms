package cn.hwyee.algorithms.interview;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Test
 * @description
 * 阿里数字马力面试
 * 问题1：
 * 实现个税计算
 * //1-5000 税率0
 * 5001-8000 3%
 * 8001-17000 10%
 * 17001-30000 20%
 * 30001-40000 25%
 * 40001-60000 30%
 * 60001-85000 35%
 * 85000- 45%
 * 要求
 * //1.逻辑正确，代码优雅
 * 2.可扩展性，考虑区间的变化，比如起征点从5000变成10000等等，或者说85000以上的征税50%
 * 这里举个例子，比如税前10000元，5000部分是不扣税，后面5000，3000扣税3%，2000%扣税10%
 * 3.可以用IDE，但是不能上网查资料
 * 4.笔试时间45分钟，代码贴到下面【这是必须的，网站到点会自动结束面试，要注意】
 * 5.笔试通过的话，会再打一次电话
 *
 * 2023年9月5日  浙江阿里来电 说是否还愿意进数字马力
 * 他说这个题还包含了数据库设计等，集合。。手机声音太小没仔细听。
 * 然后立即来了一个面试：
 * 1. arrayList & LinkedList
 * 2. hsahMap扩容  没答出来，主要是扩容后，key值如何再次映射
 * 3. java内存模型 没答出来 主内存和工作内存
 * @date 2023/8/18
 * @since JDK 1.8
 */
public class InterviewAli {

    public static void main(String[] args) {
        InterviewAli test = new InterviewAli();
        System.out.println(test.calc(8100));
        int b= 3;
        int a = b,n=1;
        System.out.println(a+"&&&"+n);
    }

    /**
     * calc:
     * 计算个税
     * @author hui
     * @version 1.0
     * @param amount
     * @return double
     * @date 2023/8/18 19:54
     */
    public double calc(double amount) {
        double result = 0;
        CalcEnum calcEnum = judgeCalc(amount);
        switch (calcEnum) {
            case EIGHT:
                result += (amount - CalcEnum.EIGHT.value) * CalcEnum.EIGHT.calc;
            case SEVEN:
                if (result != 0) {
                    result += 8750;
                } else {
                    result += (amount - CalcEnum.SEVEN.value) * CalcEnum.SEVEN.calc;
                }
            case SIX:
                if (result != 0) {
                    result += 6000;
                } else {
                    result += (amount - CalcEnum.SIX.value) * CalcEnum.SIX.calc;
                }
            case FIVE:
                if (result != 0) {
                    result += 2500;
                } else {
                    result += (amount - CalcEnum.FIVE.value) * CalcEnum.FIVE.calc;
                }
            case FOUR:
                if (result != 0) {
                    result += 2600;
                } else {
                    result += (amount - CalcEnum.FOUR.value) * CalcEnum.FOUR.calc;
                }
            case THREE:
                if (result != 0) {
                    result += 900;
                } else {
                    result += (amount - CalcEnum.THREE.value) * CalcEnum.THREE.calc;
                }
            case TWO:
                if (result != 0) {
                    result += 90;
                } else {
                    result += (amount - CalcEnum.TWO.value) * CalcEnum.TWO.calc;
                }
            case ONE:
                result += 0;
                break;
        }
        return result;
    }


    /**
     * judgeCalc:
     * 判断当前金额是个税的哪个等级
     * @author hui
     * @version 1.0
     * @param amount
     * @return cn.hwyee.common.util.CalcEnum
     * @date 2023/8/18 19:53
     */
    public CalcEnum judgeCalc(double amount) {
        if (amount > CalcEnum.EIGHT.value) {
            return CalcEnum.EIGHT;
        } else if (amount > CalcEnum.SEVEN.value) {
            return CalcEnum.SEVEN;
        } else if (amount > CalcEnum.SIX.value) {
            return CalcEnum.SIX;
        } else if (amount > CalcEnum.FIVE.value) {
            return CalcEnum.FIVE;
        } else if (amount > CalcEnum.FOUR.value) {
            return CalcEnum.FOUR;
        } else if (amount > CalcEnum.THREE.value) {
            return CalcEnum.THREE;
        } else if (amount > CalcEnum.TWO.value) {
            return CalcEnum.TWO;
        } else {
            return CalcEnum.ONE;
        }
    }


}

/**
 * 个税等级枚举:
 *
 * @author hui
 * @version 1.0
 * @return
 * @date 2023/8/18 19:53
 */
enum CalcEnum {
    ONE(0, 0),
    TWO(5000, 0.03),
    THREE(8000, 0.10),
    FOUR(17000, 0.20),
    FIVE(30000, 0.25),
    SIX(40000, 0.30),
    SEVEN(60000, 0.35),
    EIGHT(85000, 0.45);
    public final double value;
    public final double calc;

    CalcEnum(double value, double calc) {
        this.value = value;
        this.calc = calc;
    }

    public double getValue() {
        return this.value;
    }

    public double getCalc() {
        return this.calc;
    }
}
