package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
	
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		//设置非阻塞
		socketChannel.configureBlocking(false);
		InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
		//连接服务器
		if(!socketChannel.connect(inetSocketAddress)) {
			while(!socketChannel.finishConnect()) {
				System.out.println("连接需要时间,客户端不会阻塞");
			}
		}
		//连接成功,发送数据
		String msg = "hello world";
		//wrap方法不用指定buffer大小
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		//发送数据,把buffer数据写入channel
		socketChannel.write(buffer);
		System.in.read();
		
	}

}
