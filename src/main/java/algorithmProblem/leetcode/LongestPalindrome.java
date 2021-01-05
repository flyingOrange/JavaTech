package algorithmProblem.leetcode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

/*
* 409. 最长回文串
给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
注意:假设字符串的长度不会超过 1010。

示例 1:
输入:
"abccccdd"

输出:
7

解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
* */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if(s.length()==0)
            return 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        //统计字母出现次数
        Map<Character,Integer> statistics = new HashMap<Character,Integer>();
        for(int i=0;i<len;i++){
            char current = chars[i];
            if(statistics.containsKey(current)){
                int count = statistics.get(current);
                statistics.put(current,count+1);
            }else{
                statistics.putIfAbsent(current,1);
            }
        }
        //计算最大回文数
        int result = 0;
        boolean oneTime = false;//是否存在出现1次的字母
        Iterator it = statistics.values().iterator();
        Integer value = null;
        while (it.hasNext()) {
            value = (Integer) it.next();
            if(value %2 == 0){//出现次数是偶数
                result = result+value;
            }else{//出现次数是奇数
                result = result+value-1;
            }
        }

        /** 精华语句 **/
        //排除那种情况:中间一个字母 两边对称
        return result<len?result+1:result;
    }

    public static void main(String[] args) {
        int result = new LongestPalindrome().longestPalindrome("ccc");
        System.out.println(result);
    }
}
