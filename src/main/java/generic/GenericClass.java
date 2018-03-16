package generic;

//泛型类
//泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
/*
 class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
 private 泛型标识 成员变量类型  var; 
 .....

 }
 }
 */
public class GenericClass<T> {
	private T key;

	public GenericClass(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}
	
	public void say(){
		System.out.println(key.toString());
	}

}
