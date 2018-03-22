package generic;

import java.io.Serializable;

//设置限定符，要求泛型类传入对象实现某些 接口
public class GenericClass2<T extends Comparable & Serializable,K> {
	private T key;
	private K code;
	
	public GenericClass2(T key,K code) {
		this.key = key;
		this.code = code;
	}

	public T getKey() {
		return key;
	}
	
	public K getCode() {
		return code;
	}

	public void say() {
		System.out.println(key.toString());
	}
}
