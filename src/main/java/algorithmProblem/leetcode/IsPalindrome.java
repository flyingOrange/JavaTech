package algorithmProblem.leetcode;
/*
* 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:
输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。

链接：https://leetcode-cn.com/problems/palindrome-number
* */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        String positive = String.valueOf(x);
        //反转字符串
        StringBuilder sb = new StringBuilder(positive);
        String reverse = sb.reverse().toString();
        if(positive.equals(reverse)){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(1113));
    }

}
