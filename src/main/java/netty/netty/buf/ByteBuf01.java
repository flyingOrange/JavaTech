package netty.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBuf01 {

	public static void main(String[] args) {

		
		//下例包含了 byte[10]数组
		//netty的ByteBuf不需要flip()翻转，因其底层维护了writeIndex和readIndex
		//通过writeIndex和readIndex把buf分成0-readIndex可读区域，readIndex-writeIndex可写区域和writeIndex-capacity三部分
		ByteBuf buffer = Unpooled.buffer(10);
		for(int i=0;i<10;i++) {
			buffer.writeByte(i);
		}
		System.out.println("capacity="+ buffer.capacity());
		
		//getByte不会移动readIndex,readableBytes才会移动readIndex
		for(int i=0;i<buffer.capacity();i++) {
			System.out.println(buffer.getByte(i));
			
		}
		for(int i=0;i<buffer.capacity();i++) {
			System.out.println(buffer.readableBytes());
			
		}
		
		
	}

}
