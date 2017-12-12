package algorithmProblem;
/**
 * 单链表
 */

class Node{
    int val;
    Node next;
    public Node(int val)
    {
    	this.val = val;
    }
    public Node()
    {
    }
}
public class SingleList {
	//头插法(与初始顺序相同)
	public static void headInsert(int arr[])
	{
		Node head=new Node();
        head.next=null;
        for(int i=0;i<arr.length;i++)
        {
        	Node node = new Node(arr[i]);
        	node.next = head.next;
        	head.next = node;
        	
        }
        Node p = head.next;
        while(p!=null){
            System.out.print(p.val+"  ");
            p=p.next;
        }
	}
	
	//尾插法(与初始顺序相反)
	public static void tailInsert(int arr[])
	{
		Node first = new Node();
		first.next = null;
		Node r = first;//r代表队尾的元素
		for(int i=0;i<arr.length;i++)
		{
			Node node = new Node(arr[i]);
			r.next = node;//首次运行时first->next指向了arr[0]
			r = node;
		}
		Node p = first.next;
        while(p!=null){
            System.out.print(p.val+"  ");
            p=p.next;
        }
		
	}

	
	
	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5,6,7};
		headInsert(arr);
		//tailInsert(arr);

	}

}
