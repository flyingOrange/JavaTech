package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		Selector selector = Selector.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(6666));
		//设置为非阻塞模式
		serverSocketChannel.configureBlocking(false);
		//把ServerSocketChannel注册到Selector,关心事件为OP_ACCEPT
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//循环等待客户端连接
		while(true) {
			//等待1秒 如果没油事件发生则返回
			if(selector.select(1000) == 0) {
				System.out.println("服务器等待1秒,无连接");
				continue;
			}
			
			//如果返回>0则返回相关的selectionKey
			//selector.selectedKeys()返回所有关注事件的集合
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> ketIterator = selectionKeys.iterator();
			while(ketIterator.hasNext()) {
				SelectionKey key = ketIterator.next();
				//根据key对应的通道发生的事件进行处理
				if(key.isAcceptable()) {//如果是OP_ACCEPT事件--有新客户端连接
					SocketChannel socketChannel = serverSocketChannel.accept();
					//把socketChannel设置为非阻塞
					socketChannel.configureBlocking(false);
					System.out.println("客户端连接");
					//将socketChannel注册到selector,关注事件为OP_READ。同时给socketChannel关联一个ByteBuffer
					socketChannel.register(selector, SelectionKey.OP_READ,ByteBuffer.allocate(1024));
				}
				if(key.isReadable()) {
					//通过key获取对应的channel
					SelectableChannel selectableChannel = key.channel();
					SocketChannel channel = (SocketChannel)selectableChannel;
					//获取该channel关联的buffer
					ByteBuffer buffer = (ByteBuffer)key.attachment();
					channel.read(buffer);
					System.out.println("receive:"+ new String(buffer.array()));
					
				}
				//手动从集合中移除key,防止重复操作
				ketIterator.remove();
				
			}
			
		}

	
	
	}

}
