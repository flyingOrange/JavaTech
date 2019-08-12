package algorithmProblem.leetcode;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/*
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
        输入: [-2,1,-3,4,-1,2,1,-5,4],
        输出: 6
        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * */
public class MaximumSubarray53 {

    // 暴力解法
    public static int maxSubArray(int[] nums) {
        int size = nums.length;
        int maxSum = nums[0];// 总和
        int sum = 0;// 临时求和
        for (int begin = 0; begin < size; begin++) {
            sum = 0;
            for (int end = begin; end < size; end++) {
                sum = sum + nums[end];
                if(maxSum < sum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /* 分治、递归算法:
     * 选定一点把数列一分为二,此时我们考虑最大的子数组存在的可能位置：
　　　　1.仅在nums数组的左半边；
　　　　2.仅在nums数组的右半边；
　　　　3.跨立nums数组的中间。
                对于前两种情况我们可以继续对子数组进行重复调用自身的办法继续细分问题，从而将问题无限减小到可以直接计算，而第3种情况则不能，
                但我们可以考虑到这种情况下子数组一定是分成两部分：左半边的最大后缀和右半边的最大前缀。以选定点开始，向两边相加，和的最大值。
     * */
    public static void maxSubArray2(int[] nums) {
        
        
    }
    
    public static void main(String[] args) {
        int []nums = {-2,1,-3,4,-1,2,1,-5,4};
        
        int result = maxSubArray(nums);
        System.out.println(result);
        


    }

}
