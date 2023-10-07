package cn.hwyee.algorithms.question;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Variable
 * @description Java for循环和定义变量值的问题
 * https://blog.csdn.net/i_am_handsome_/article/details/108790786#comments_28944398
 * @date 2023/9/26
 * @since JDK 1.8
 */
public class Variable {
    public static void main(String[] args) {
        forCycle();
        iconst();
    }

    public static void forCycle(){
        int sum ;
        sum=2;
        for (int i =1 ;i <= 100;i++){
            sum =1;
            if (i % 2 != 0){
                System.out.println(i);
                sum = i;
            }else{
                sum = i;
            }
        }
        System.out.println("result" + sum);
    }

    public static void ifCondition(){
        int sum;
        if (4 % 2 != 0){
            sum = 1;
        }else{
            sum = 2;
        }
        System.out.println(sum);
    }

    public static void iconst(){
        int a,b,c,d,e,f,g,h;
        a=1;b=1;c=1;d=1;
        System.out.println(a);
    }
}
