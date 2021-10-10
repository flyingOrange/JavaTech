package algorithmProblem.dataStructure;

public class SortMethod {
	
	/*
	 * 冒泡排序
	 * 两两交换位置，每一趟选出一个最大元素放在最后，n-1趟完成排序
	 * 
	 * */
	public static void BubbleSort(int a[])
	{
		int len = a.length;
		for(int i =0 ; i< len; i++){//i代表趟数，如5个元素需要4趟
			for(int j=0;j<len-i-1;j++){//j代表每一趟从第0个元素排到第j个元素
				if(a[j] > a[j+1]){
					swap(a,j,j+1);
		        }
			}
		}
	}
	
	/*
	 * 快速排序
	 * 从数列中挑出一个元素，称为 “基准”（pivot）;重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
	 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
	 * */
	public static void quickSort(int a[],int low,int high)
	{
		if(high<=low)
			return;
		int index = partition(a, low, high);
		System.out.println("index="+index);
		quickSort(a,low,index-1);
		quickSort(a,index+1,high);
	}
	
	
	/*
	 * 希尔排序
	 * 
	 * */
	public static void shellSort(int a[])
	{
		int N = a.length;
		int h = 1;
		while(h<N/3)//h=1,4,13,40,121.....
			h = 3*h+1;
		while(h>=1)
		{
			//将数组变为h有序
			for(int i=h;i<N;i++)
			{
				for(int j=i;j>=h&&less(a[j],a[j-h]);j=j-h)
				{
					swap(a,j,j-h);
				}
			}
			h = h/3;
			
		}	
	}
	
	/*
	 * 直接插入排序
	 * 将a[i]元素不断插入到a[0]-a[i-1]元素中合适的位置去。从a[1]开始，和a[0]比较，放置，a[2]放置到a[0]、a[1]的合适位置……
	 * 总结：运行时间取决于输入元素的初始顺序。
	 * 最好情况(数据已经有序)：比较N-1次
	 * 
	 * */
	public static void insertSort(int []a) {
		int N = a.length;
		for(int i=1;i<N;i++)
		{
			//将a[i]元素插入到a[0]-a[i-1]元素中合适的位置去
			for(int j=i;j>0;j--)
			{
				if(less(a[j],a[j-1]))
					swap(a,j,j-1);
				else
					break;
			}
			
		}
		
	}
	
	
	/*
	 * 简单选择排序
	 * 在N个元素中选出最小的放在a[0]
	 * 从1-N个元素中选择最小的放在a[1]
	 * 2-N......			a[2]
	 * 
	 * 总结：需要做N次交换，元素对比次数是(N-1)+(N-2)+....+2+1=N(N-1)/2 ~N2/2
	 * 特点：1、运行时间和输入无关 2、移动元素数最少
	 * */
	public static void simpleSelectSort(int []a)
	{
		int N = a.length;
		for(int i=0;i<N;i++)
		{
			int min = i;//最小元素的索引
			for(int j=i+1;j<N;j++)
			{
				if(less(a[j],a[min]))
				{
					min = j;
				}
				if(i != min){
					//从第i+1--N个元素中选择最小的互换
					swap(a,i,min);
				}

			}
			
		}
	}

	///////////////辅助方法///////////////////
	private static boolean less(int v,int w)
	{
		if(v<w)
			return true;
		else 
			return false;
	}
	
	private static void swap(int []a,int i,int j)
	{
		int temp = a[i];
		a[i] =a [j];
		a[j] = temp;
	}
	public static void show(int []a)
	{
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+"  ");
		System.out.println("");
	}
	private static boolean isSorted(int []a)
	{
		for(int i=0;i<a.length;i++)
		{
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
		
	}
	private static int partition(int a[],int low,int high)
	{
		//将数组分为a[low....i-1] a[i] a[i+1...high]	
		//过程：从前向后找到第一个比切分元素大的元素，同时从后向前找到第一个比切分元素小的元素
		int i = low;
		int j = high+1;
		int key = a[low];//切分元素
		while(true){
			while(less(a[++i],key)){
				if(i == high)
					break;
			}
			while(less(key,a[--j])){
				if(j == low)
					break;
			}
			if(i >= j)
				break;
			
			swap(a,i,j); 
		}
		swap(a,low,j);
		return j;
		
	}

	public static void review(int a[]){
		int len = a.length;

		for(int i=1;i<len;i++){



		}

	}



	
	public static void main(String[] args) {
		int a[] = {2,9,4,6,3,8,1,7,5};
		//SortMethod.simpleSelectSort(a);
		//SortMethod.insertSort(a);
		//SortMethod.shellSort(a);
		//SortMethod.quickSort(a,0,8);
		//SortMethod.BubbleSort(a);
		SortMethod.review(a);
		SortMethod.show(a);
	}
	

	

}
