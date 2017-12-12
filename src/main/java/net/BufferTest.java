package net;

import java.nio.CharBuffer;

/**
 * Buffer的使用方法
 */
public class BufferTest {


	public static void main(String[] args) {
		CharBuffer buff = CharBuffer.allocate(8);
		System.out.println("capacity:     "+buff.capacity());
		System.out.println("limit:     "+buff.limit());
		System.out.println("position:     "+buff.position());
		//放入元素
		buff.put('a');
		buff.put('b');
		buff.put('c');
		System.out.println("加入三个元素后,position="+buff.position());
		System.out.println("加入三个元素后,limit="+buff.limit());
		//调用flip()
		buff.flip();
		System.out.println("flip()后,position="+buff.position());
		System.out.println("flip()后,limit="+buff.limit());
		//取出第一个元素
		System.out.println("第一个元素position=0:  "+buff.get());
		System.out.println("取出第一个元素后,position="+buff.position());
		//调用clear()方法
		buff.clear();
		System.out.println("clear()后,position="+buff.position());
		System.out.println("clear()后,limit="+buff.limit());
		System.out.println("调用clear()后,数据并没有清除:  "+buff.get(2));
		System.out.println("执行绝对读取后,position="+buff.position());
		
	}

}
