package cn.hwyee.algorithms.interview;

import java.util.Scanner;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ODInterview
 * @description
 * @date 2023/9/8
 * @since JDK 1.8
 */
public class ODInterview {

    /**
     * :
     * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
     * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
     * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
     * 实现 MyLinkedList 类：
     * MyLinkedList() 初始化 MyLinkedList 对象。
     * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
     * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
     * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
     * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，
     * 那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
     * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
     * 示例：
     * 输入
     * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
     * [[],[1],[3],[1,2],[1],[1],[1]]
     * 输出
     * [null, null, null, null, 2, null, 3]
     * 解释
     * MyLinkedList myLinkedList = new MyLinkedList();
     * myLinkedList.addAtHead(1);
     * myLinkedList.addAtTail(3);
     * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
     * myLinkedList.get(1);              // 返回 2
     * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
     * myLinkedList.get(1);              // 返回 3
     * 提示：
     * 0 <= index, val <= 1000
     * 请不要使用内置的 LinkedList 库。
     * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/9/8 19:51
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        String methodString = input1.substring(1, input1.length() - 1);
        String[] methods = methodString.split(",");
        String paramString = input2.substring(1, input2.length() - 1);
        String[] params = paramString.split("],");
        if (methods.length==params.length && methods.length>0){
            StringBuilder sb = new StringBuilder();
            MyLinkedList myLinkedList = new MyLinkedList();
            sb.append("[null");
            for (int i=1; i<methods.length; i++){
                String s = runMethod(myLinkedList, methods[i], params[i]);
                sb.append(",").append(s);
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    public static String  runMethod(MyLinkedList list,String method,String param){
        String result = null;
        switch (method.trim()){
            case "MyLinkedList":
                break;
            case "\"addAtHead\"":
                list.addAtHead(Integer.parseInt(param.substring(1,2)));
                break;
            case "\"addAtTail\"":
                list.addAtTail(Integer.parseInt(param.substring(1,2)));
                break;
            case "\"addAtIndex\"":
                list.addAtIndex(Integer.parseInt(param.substring(1,2)),Integer.parseInt(param.substring(3,4)));
                break;
            case "\"get\"":
                result = String.valueOf(list.get(Integer.parseInt(param.substring(1, 2))));
                break;
            case "\"deleteAtIndex\"":
                list.deleteAtIndex(Integer.parseInt(param.substring(1,2)));
                break;
            default:
                break;
        }
        return result;
    }

    static class MyLinkedList{
        private  Integer val;
        private  MyLinkedList next;

        MyLinkedList(){

        }
        MyLinkedList(Integer val){
            this.val = val;
        }

        /**
         * get:
         * 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1
         * @author hui
         * @version 1.0
         * @param index
         * @return int
         * @date 2023/9/8 19:52
         */
        int get(int index){
            if(index <0){
                return -1;
            }
            MyLinkedList temp = this;
            for (int i = 0; i <= index; i++) {
                if (temp==null){
                    return -1;
                }
                if (i == index){
                    return temp==null?-1:temp.val;
                }
                temp=temp.next;
            }
            return -1;
        }

        /**
         * addAtHead:
         * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
         * @author hui
         * @version 1.0
         * @param val
         * @return void
         * @date 2023/9/8 19:52
         */
        void addAtHead(int val){
            MyLinkedList temp = this.next;
            MyLinkedList myLinkedList = new MyLinkedList(this.val);
            myLinkedList.next = temp;
            this.val = val;
            this.next = myLinkedList;
        }


        /**
         * addAtTail:
         * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
         * @author hui
         * @version 1.0
         * @param val
         * @return void
         * @date 2023/9/8 19:53
         */
        void addAtTail(int val){
            MyLinkedList myLinkedList = new MyLinkedList(val);
            MyLinkedList temp = this;
            while (temp.next.val != null){
                temp = temp.next;
            }
            temp.next = myLinkedList;
        }


        void addAtIndex(int index, int val){
            if (index <0){
                return;
            }
            if (index == 0){
                addAtHead(val);
            }
            MyLinkedList temp = this;
            MyLinkedList prev = this;
            for (int i = index; i > 0; i--) {
                if (temp == null){
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
            MyLinkedList myLinkedList = new MyLinkedList(val);

            if ( temp==null){
                prev.next=myLinkedList;
            }else {
                prev.next  = myLinkedList;
                myLinkedList.next = temp;
            }
        }

        /**
         * deleteAtIndex:
         * 如果下标有效，则删除链表中下标为 index 的节点。
         * @author hui
         * @version 1.0
         * @param index
         * @return void
         * @date 2023/9/8 19:53
         */
        void deleteAtIndex(int index) {
            if (index>=0){
                MyLinkedList temp = this;
                MyLinkedList prev = this;
                for (int i = index; i > 0; i--) {
                    if (prev == null){
                        return;
                    }
                    prev = temp;
                    temp = temp.next;
                }
                prev.next = temp.next;
            }
        }


    }
}
