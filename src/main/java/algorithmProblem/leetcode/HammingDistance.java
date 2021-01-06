package algorithmProblem.leetcode;
/*
* 461. 汉明距离
两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 2的31次方.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。
* */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int i = x^y;
        //函数功能说明：计数整型i二进制表示中1的个数。
        int result = Integer.bitCount(i);
//        String bin = Integer.toBinaryString(i);
//        int result = 0;
//        for(char ch:bin.toCharArray()){
//            if(ch == '1')
//                result++;
//        }
        return result;
    }

    public static void main(String[] args) {
        int res = new HammingDistance().hammingDistance(1,4);
        System.out.println(res);
    }
}
