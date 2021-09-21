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
		ServerSocketChannel serverSocketChannel = null;
		Selector selector = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			selector = Selector.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(6666));
			//设置为非阻塞模式
			serverSocketChannel.configureBlocking(false);
			//把ServerSocketChannel注册到Selector,关心事件为OP_ACCEPT
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//循环等待客户端连接
		while(true) {
			//等待1秒 如果没油事件发生则返回
			selector.select();
			//如果返回>0则返回相关的selectionKey
			//selector.selectedKeys()返回所有关注事件的集合
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
			while(keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
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
					/*
					获取该channel关联的buffer
					由于不知道客户端发送多少数据,bytebuffer有一个扩容的需求,此处不展开了
					* */
					ByteBuffer buffer = (ByteBuffer)key.attachment();
					int read = channel.read(buffer);
					//如果正常断开，会返回-1
					if(read == -1){
						key.cancel();
					}else{
						buffer.flip();
					}
					System.out.println("receive:"+ new String(buffer.array()));

					/*
					事件要么处理要么取消,否则下次还需要处理
					客户端断开连接时会触发一次read事件，因此要捕获这次异常
					* */
					key.cancel();
				}
				//手动将事件从集合中移除
				keyIterator.remove();
				
			}
			
		}

	
	
	}

}
