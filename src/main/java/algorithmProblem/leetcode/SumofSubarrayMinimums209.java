package algorithmProblem.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
        由于答案可能很大，因此返回答案模 10^9 + 7。
        
   示例：
   输入：[3,1,2,4]
   输出：17
   解释：
   子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
  最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。     
 * */
public class SumofSubarrayMinimums209 {
    public static int sumSubarrayMins(int[] A) {
        List<Integer> list = IntStream.of(A).boxed().collect(Collectors.toList());
        //截取所有连续子数组
        List<List<Integer>> subList = new ArrayList<List<Integer>>();
        int size = list.size();
        for(int i=0;i<=size;i++) {
            
            
        }
        
        
        //list.subList(fromIndex, toIndex);
        
        return 0;
    }
    
    //计算数组最小元素
    public static int min(List<Integer> list) {
        int size = list.size();
        int min = list.get(0);
        for(int i=1;i<size;i++) {
            if(min > list.get(i)) {
                min = list.get(i);
            }
        }
        return min;
    }
    public static void main(String[] args) {

        
    }

}
