package netty.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * 使用channel读写文件
 * 
 * */
public class NioFileChannel01 {
	
	public static void main(String[] args) throws IOException {
		String str = "hello world你好";
		String path = "C:\\Users\\orange\\Desktop\\file.txt";
		
		/******************写文件*******************************/
		FileOutputStream fos = new FileOutputStream(path);
		//通过FileOutputStream获取对应的FileChannel,真实类型是FileChannelImpl
		FileChannel fileChannel = fos.getChannel();
		
		//创建一个缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//将str 放入buffer
		buffer.put(str.getBytes());
		//对buffer进行flip
		buffer.flip();
		
		//把缓冲区数据写入通道
		fileChannel.write(buffer);
		
		//关闭流
		fos.close();
		/******************读文件*******************************/
		FileInputStream fis = new FileInputStream(path);
		//获取通道
		FileChannel rChannel = fis.getChannel();
		//分配缓冲区
		ByteBuffer readBuffer = ByteBuffer.allocate(512);
		//将数据读取到缓冲区
		rChannel.read(readBuffer);
		//将缓冲区数据读输出
		String content = new String(readBuffer.array());
		System.out.println(content);
		
		fis.close();
		
	}

}
