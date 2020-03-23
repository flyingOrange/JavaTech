package netty.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
/*
 * Scattering:将数组写入到buffer时，可以采用buffer数组，依次写入
 * Gathering:从buffer读取数据时，可以采用buffer数组，依次读取
 * */
public class NioFileChannel04 {

	public static void main(String[] args) throws IOException {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
		serverSocketChannel.socket().bind(inetSocketAddress);
		//创建ByteBuffer数组
		ByteBuffer[] byteBuffers = new ByteBuffer[2];
		byteBuffers[0] = ByteBuffer.allocate(5);
		byteBuffers[1] = ByteBuffer.allocate(3);
		
		//等待客户端连接
		SocketChannel socketChannel = serverSocketChannel.accept();
		int messageLength = 8;
		//循环读取
		while(true) {
			int read = 0;
			while(read < messageLength) {
				
				long ll = socketChannel.read(byteBuffers);
				read += ll; 
				System.out.println("read="+read);
				
				Arrays.asList(byteBuffers).stream().forEach(buffer->{
					System.out.println("position=" + buffer.position() + ",limit=" +buffer.limit());
				});
				//将所有的buffer进行翻转
				Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
				
			}
			
		}
		
	}

}
