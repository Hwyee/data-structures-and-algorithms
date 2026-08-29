package cn.hwyee.algorithms.leecode.train;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Train2409
 * @description
 * @date 2024/9/2
 * @since JDK 1.8
 */
public class Train2409 {
    public static void main(String[] args) {
        int[][] train = new int[][]{
                {5,0,0,1},
                {0,4,1,5},
                {0,5,2,0},
                {4,1,0,2}};
        checkXMatrix(train);
    }

    public static boolean checkXMatrix(int[][] grid) {
        boolean x = false;
        boolean other = false;
        int lenX = grid.length-1;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                int temp = i+j;
                if(i==j || temp==lenX){
                    if(!x && grid[i][j] == 0){
                        x = true;
                    }
                }else{
                    if(!other && grid[i][j] != 0){
                        other = true;
                    }
                }
                if(x || other){
                    return false;
                }
            }
        }
        return true;
    }
}

 abstract class A{
    public abstract int a();
    int $is;

}

 interface b{

}