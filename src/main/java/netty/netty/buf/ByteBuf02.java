package netty.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

//ByteBuf零拷贝
public class ByteBuf02 {

	public static void main(String[] args) {
		ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(10);
		byteBuf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
		System.out.println(byteBuf);

		//slice切片并没有复制数据,和原数据公用内存
		ByteBuf f1= byteBuf.slice(0,5);
		ByteBuf f2= byteBuf.slice(5,5);
		f1.retain();//引用计数+1
		byteBuf.release();//byteBuf引用计数-1，计数为0后f1 f2也不能用了
		System.out.println(f1);
		System.out.println(f2);
		//duplicate和原数据公用一片内存，且不截取
		ByteBuf f3 = byteBuf.duplicate();
		//copy，和原数据不公用内存，再无关系
		ByteBuf f4 = byteBuf.copy();

		//多个ButeBuf聚合
		ByteBuf bf1 = ByteBufAllocator.DEFAULT.buffer();
		bf1.writeBytes(new byte[]{1,2,3,4,5});
		ByteBuf bf2 = ByteBufAllocator.DEFAULT.buffer();
		bf2.writeBytes(new byte[]{6,7,8,9,10});
		ByteBuf bf = ByteBufAllocator.DEFAULT.buffer();
		bf.writeBytes(bf1).writeBytes(bf2);//会发生数据拷贝
		System.out.println(bf);

		CompositeByteBuf buf = ByteBufAllocator.DEFAULT.compositeBuffer();//不会进行内存数据拷贝
		buf.addComponents(true, bf1, bf2);
		System.out.println(buf);

	}
	
}
