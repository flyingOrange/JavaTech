package netty.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
 * MappedByteBuffer 可以让文件在内存(堆外内存)修改，操作系统不需要拷贝一次
 * 
 * */
public class NioFileChannel03 {

	public static void main(String[] args) throws IOException {
		String srcPath = "C:\\Users\\orange\\Desktop\\file.txt";
		
		RandomAccessFile randomAccessFile = new RandomAccessFile(srcPath,"rw");
		
		FileChannel channel = randomAccessFile.getChannel();
		/*
		 * 参数一：所用的读写模式
		 * 参数二：可以直接修改的起始位置
		 * 参数三：映射到内存的大小(不是索引位置)。
		 * 可以直接修改的范围是0-5
		 * */
		MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
		mappedByteBuffer.put(0,(byte)'K');
		mappedByteBuffer.put(3,(byte)'9');
		System.out.println("修改成功");
		

	}

}
