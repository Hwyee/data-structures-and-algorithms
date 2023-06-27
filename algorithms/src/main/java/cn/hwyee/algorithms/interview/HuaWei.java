package cn.hwyee.algorithms.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName HuaWei
 * @description 华为机试
 * @date 2023/6/26
 * @since JDK 1.8
 */
public class HuaWei {

    /**
     * getLastStringLength:
     * 字符串最后一个单词的长度
     * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
     * @author hui
     * @version 1.0
     * @return void
     * @date 2023/6/26 17:45
     */
    public void getLastStringLength(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String s = in.nextLine();

        String t = s.trim();
        int a = t.lastIndexOf(' ');
        System.out.println(t.length() - a - 1);
    }

    /**
     * calcCharCount:
     * 计算某字符出现次数
     * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
     * @author hui
     * @version 1.0
     * @return void
     * @date 2023/6/26 17:51
     */
    public void calcCharCount(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String s = in.nextLine();
        String c = in.nextLine();
        String s1 = s.toLowerCase().replaceAll(c.toLowerCase(),"");
//        System.out.println((s.length()-s1.length())/c.length());
        //因为题目是一个字符，所以不用除以长度了。
        System.out.println(s.length()-s1.length());

    }

    /**
     * mingmingsRandomNum:
     * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，
     * 然后再把这些数从小到大排序，按照排好的顺序输出。
     * 数据范围 1<n<1000,输入的数据大小 1<val<500
     * @author hui
     * @version 1.0
     * @date 2023/6/27 0:41
     */
    public  void mingmingsRandomNum() {
        Scanner scanner = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            //创建501的数组也可以。
            int[] arr = new int[1001];
            for (int i = 0; i < num; i++) {
                int n = scanner.nextInt();
                arr[n] = 1;
            }
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == 1) {
                    System.out.println(i);
                }
            }
        }
    }


    /**
     * stringSplit8:
     * 字符串分隔
     * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * @author hui
     * @version 1.0
     * @date 2023/6/27 0:53
     */
    public void stringSplit8() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String s = input.nextLine();
            split(s);
        }
    }

    public static void split(String s) {
        while (s.length() >= 8) {
            System.out.println(s.substring(0, 8));
            s = s.substring(8);
        }
        if (s.length() < 8 && s.length() > 0) {
            s += "00000000";
            System.out.println(s.substring(0, 8));
        }
    }

    /**
     * Hexadecimal2Ten:
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     * @author hui
     * @version 1.0
     * @param args  
     * @return void
     * @date 2023/6/27 1:11
     */
    public void Hexadecimal2Ten(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            in.nextInt();
            String s = in.nextLine();
            int count = 0;
            for (int i = 0; i < s.length() - 2; i++) {
                //由于前面两位是ox,所以隔过去
                char tc = s.charAt(i + 2);
                //记录字母转化为数值
                int t = 0;
                //将字母转换为数值
                if (tc >= '0' && tc <= '9') {
                    t = tc - '0';
                }
                //字母'A'/'a'~'F'/'f'对应数字10~15
                else if (tc >= 'A' && tc <= 'F') {
                    t = tc - 'A' + 10;
                } else if (tc >= 'a' && tc <= 'f') {
                    t = tc - 'a' + 10;
                }
                //计算加和,16进制，每一进位是16
                count += t * Math.pow(16, s.length() - i - 3);
            }
            System.out.println(count);
        }
    }

    /**
     * primeFactor1: 
     * 质数因子
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
     * 此算法超时了，12个例子通过了11个。
     * @author hui
     * @version 1.0
     * @param args  
     * @return void
     * @date 2023/6/27 23:41
     */
    public static void primeFactor1(String[] args){
        Scanner scan = new Scanner(System.in);
        long num = Long.parseLong(scan.next());
        getPrimer(num);
    }

    public static void getPrimer(long num){
        for (int i= 2;i <= num; i++){
            if (num % i==0){
                System.out.print(i + " ");
                getPrimer(num/i);
                break;
            }
            if (i==num){
                System.out.print( i + "");
            }
        }
    }

    /**
     * primeFactor2:
     * 质数因子，此算法可以通过
     * @author hui
     * @version 1.0
     * @param args
     * @return void
     * @date 2023/6/27 23:45
     */
    public static void primeFactor2(String[] args) {
        // 处理输入
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // 获取需要求解的值
            int target = sc.nextInt();
            int y = 2;// 因子从2开始算
            while(target != 1){ // 短除法，除到目标值为1为止
                if(target % y == 0) // 能能够整除2
                {
                    System.out.print(y+" ");
                    target /= y;
                }else{// 更新y的值
                    if(y > target / y) {
                        y = target;//如果剩余值为质数
                    } else {
                        y++;  //y值增加1
                    }
                }
            }
        }
    }

    /**
     * similarVal:
     * 取近似值
     * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
     * @author hui
     * @version 1.0
     * @param args
     * @return void
     * @date 2023/6/28 0:04
     */
    public static void similarVal(String[] args) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.forEach((a,b) ->
            System.out.println(a + " " + b)
        );

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextDouble()) { // 注意 while 处理多个 case
            double a = in.nextDouble();
            String s = String.valueOf(a);
            String p = s.substring(s.indexOf('.') + 1,s.indexOf('.')+2);
            if(Integer.parseInt(p)<5){
                System.out.print(s.substring(0,s.indexOf('.')));
            }else{
                System.out.print(Integer.parseInt(s.substring(0,s.indexOf('.')))+1);
            }
        }
        //简单版
//        Scanner in = new Scanner(System.in);
//        double number = in.nextDouble();
//        System.out.println((int)(number + 0.5));
    }

    /**
     * mergeTableRecord:
     * 合并表记录
     * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
     * 提示:
     * 0 <= index <= 11111111
     * 1 <= value <= 100000
     * @author hui
     * @version 1.0
     * @param args
     * @return void
     * @date 2023/6/28 0:20
     */
    public static void mergeTableRecord(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String a = in.nextLine();
        //使用treeMap,题目的结果输出是按照key排序的,如果使用hashMap通过率是9/11
        Map<Integer,Integer> map = new TreeMap<>();
        for(int i = 1 ;i <= Integer.parseInt(a) ; i++)  {
            int b = in.nextInt();
            int c = in.nextInt();
            if(map.containsKey(b)){
                map.put(b,map.get(b)+c);
            }else{
                map.put(b,c);
            }
        }
        map.forEach((q,w) ->
                System.out.println(q+ " " + w)
        );
    }

}
