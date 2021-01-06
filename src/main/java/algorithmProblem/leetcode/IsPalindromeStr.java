package algorithmProblem.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
* 125. 验证回文串
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:
输入: "A man, a plan, a canal: Panama"
输出: true

示例 2:
输入: "race a car"
输出: false
* */
public class IsPalindromeStr {
    public boolean isPalindrome1(String s) {//方法一：正则表达式过滤掉空格和标点
        String actual = s.replaceAll("[^a-z0-9A-Z]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }

    public boolean isPalindrome2(String s) {//方法二：最简单的就是使用双指针，一个指向前，一个指向后，遇到空格以及特殊字符要跳过，然后判断
        if (s.isEmpty())
            return true;
        s = s.toLowerCase();
        int left = 0;
        int right = s.length()-1;
        while (left<right){
            char lc = s.charAt(left);
            char rc = s.charAt(right);
            if((!Character.isDigit(lc))&&(!Character.isLetter(lc))){
                left++;
                continue;
            }
            if((!Character.isDigit(rc))&&(!Character.isLetter(rc))){
                right--;
                continue;
            }
            if(lc != rc){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean res = new IsPalindromeStr().isPalindrome1("A man, a plan, a canal: Panama");
        System.out.println(res);
        res = new IsPalindromeStr().isPalindrome2("A man, a plan, a canal: Panama");
        System.out.println(res);
    }
}
