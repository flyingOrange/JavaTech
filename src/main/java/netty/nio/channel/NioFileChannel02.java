package netty.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * 使用一个buffer进行文件拷贝
 * 
 * */
public class NioFileChannel02 {
	public static void main(String[] args) throws IOException {
		
		/******************使用一个缓冲区拷贝文件*******************************/
		String srcPath = "C:\\Users\\orange\\Desktop\\file.txt";
		String dstPath = "D:\\BaiduNetdiskDownload\\1.txt";
		
		FileInputStream fis = new FileInputStream(srcPath);
		FileOutputStream fos = new FileOutputStream(dstPath);
		FileChannel rChannel = fis.getChannel();
		FileChannel wChannel = fos.getChannel();		
		
		ByteBuffer buffer = ByteBuffer.allocate(512);
		while(true) {
			//清空标志位。如果不复位，pos=limit，始终读不到数据，read一致返回0永远不返回-1
			buffer.clear();
			int read = rChannel.read(buffer);
			System.out.println("read="+read);
			if(read == -1) {
				break;
			}
			//切换
			buffer.flip();
			wChannel.write(buffer);
		}
		/////////////////也可以使用api直接拷贝////////////////////////
		//wChannel.transferFrom(rChannel, 0, rChannel.size());
		/////////////////也可以使用api直接拷贝////////////////////////
		
		
		fos.close();
		fis.close();
		System.out.println("over");
		

	}

}
