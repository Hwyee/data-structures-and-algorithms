package cn.hwyee.algorithms.leecode.leecode75;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ada
 * @description
 * @date 2024/8/13
 * @since JDK 1.8
 */
public abstract class Ada {
    int a;


    public Ada(int a) {
        this.a = a;
    }

}

interface bad extends dd {
    public static final int A = 0;

    public abstract void a();

    public default void aa() {
        System.out.println("aa");
    }

    public static void aaa() {
        System.out.println("aaa");
    }

}

interface dd {

}

class Solution implements bad, dd {
    public static void main(String[] args) {
        Ada ada = new Ada(1) {
        };
        bad bad = new bad() {
            public void a() {
                System.out.println("a");
            }
        };
    }

    @Override
    public void a() {

    }
}

class s1 extends Ada {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.a();
        solution.aa();
//        solution.aaa 只能接口调用
        bad.aaa();

    }

    public s1(int a) {
        //抽象类没有无参构造函数，需要显示调用。 这也是抽象类的一个特点，可以让子类调用抽象类的构造函数，统一模板。
        super(a);

    }
}

