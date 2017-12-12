package net;
/**
 * 非阻塞模式-单线程 
 */
import java.io.*;
import java.nio.*;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.net.*;
import java.util.concurrent.*;


public class NioServer2 {
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel = null;
	private int port = 8081;
	private Charset charset = Charset.forName("UTF-8");

	public NioServer2() throws IOException
	{
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().setReuseAddress(true);
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		System.out.println("服务器启动");
		
	}
	public void service() throws IOException
	{
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(selector.select()>0)
		{
			Set readyKeys = selector.selectedKeys();
			Iterator it = readyKeys.iterator();
			while(it.hasNext())
			{
				SelectionKey key = null;
				
				try{
					
					key = (SelectionKey)it.next();
					it.remove();
					
					if(key.isAcceptable())
					{
						ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
						SocketChannel socketChannel = /*(SocketChannel)*/ssc.accept();
						System.out.println("接收到连接:  "+socketChannel.socket().getInetAddress()+"   "+  socketChannel.socket().getPort());
						
						socketChannel.configureBlocking(false);
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE,buffer);
						
					}
					if(key.isReadable())
					{
						receive(key);
					}
					if(key.isWritable())
					{
						send(key);
					}
				}catch(IOException e)
				{
					e.printStackTrace();
					try{
						if(key!=null)
						{
							key.cancel();
							key.channel().close();
							
						}
					}catch(IOException e2){e2.printStackTrace();}
				}
				
				
				
			}//#while
			
			
		}//#while
		
		
	}
	public void receive(SelectionKey key) throws IOException
	{
		ByteBuffer buffer = (ByteBuffer)key.attachment();
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(32);
		socketChannel.read(readBuffer);
		readBuffer.flip();
		
		buffer.limit(buffer.capacity());
		buffer.put(readBuffer);
		
		
	}
	public void send(SelectionKey key) throws IOException
	{
		ByteBuffer buffer = (ByteBuffer)key.attachment();
		SocketChannel socketChannel = (SocketChannel) key.channel();
		buffer.flip();
		String data = decode(buffer);
		if(data.indexOf("\r\n")==-1)
			return;
		String outputData = data.substring(0,data.indexOf("\n")+1);
		System.out.println(outputData);
		ByteBuffer outputBuffer = encode("echo:  "+outputData);
		while(outputBuffer.hasRemaining())
			socketChannel.write(outputBuffer);
		
		ByteBuffer temp = encode(outputData);
		buffer.position(temp.limit());
		buffer.compact();
		
		if(outputData.equals("bye\r\n"))
		{
			key.cancel();
			socketChannel.close();
			
			System.out.println("关闭与客户端连接");
		}
		
		
	}
	public ByteBuffer encode(String str)
	{
		
		return charset.encode(str);
	}
	public String decode(ByteBuffer buffer)
	{
		CharBuffer charBuffer = charset.decode(buffer);
		return charBuffer.toString();
	}
	
	public static void main(String[] args) throws IOException {

		new NioServer2().service();
	}

}
