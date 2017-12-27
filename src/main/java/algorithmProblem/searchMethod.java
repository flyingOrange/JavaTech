package algorithmProblem;

import java.util.HashMap;
import java.util.Map.Entry;

public class searchMethod {

	//二分查找(非递归二分查找算法 )
	public static int binarySearch1(int[] array, int value)
    {
        int low = 0;
        int high = array.length - 1;
        while(low <= high)
        {
            int middle = (low + high) / 2;
            if(value == array[middle])
            {
                return middle;
            }
            if(value > array[middle])
            {
                low = middle + 1;
            }
            if(value < array[middle])
            {
                high = middle - 1;
            }
        }
        return -1;
    }
	
	
	
	
	public static void main(String[] args) {
		int a[] = {1,2,4,5,6,7,8,9};
		int value1 = binarySearch1(a, 8);
        System.out.println(value1);
        
	}

}
