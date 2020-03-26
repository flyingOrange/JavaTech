package netty.netty.buf;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBuf02 {

	public static void main(String[] args) {
		ByteBuf buffer = Unpooled.copiedBuffer("hello world!",Charset.forName("UTF-8"));
		
		if(buffer.hasArray()) {
			byte[] content = buffer.array();
			//将数据转化为字符串
			System.out.println(new String(content,Charset.forName("UTF-8")));
			
			System.out.println("bytebuf="+buffer);
			
			System.out.println(buffer.arrayOffset());
			System.out.println(buffer.readerIndex());
			System.out.println(buffer.writerIndex());
			System.out.println(buffer.capacity());
			
			int len = buffer.readableBytes();
			System.out.println("len="+len);
			
			//按照某个范围读取
			System.out.println(buffer.getCharSequence(0, 4, Charset.forName("UTF-8")));
			System.out.println(buffer.getCharSequence(2, 4, Charset.forName("UTF-8")));
			
			
		}
		
	}
	
}
