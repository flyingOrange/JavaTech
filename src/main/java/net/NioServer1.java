/*
 * 阻塞模式-多线程
 * */
package net;
import java.io.*;
import java.nio.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.net.*;
import java.util.concurrent.*;

public class NioServer1 {
	private int port = 8081;
	private ServerSocketChannel serverSocketChannel = null;
	private ExecutorService executorService = null;//线程池
	private static final int POOL_MULTIPUL = 4;
	
	
	public NioServer1() throws IOException
	{
		//创建线程池
		executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors()*POOL_MULTIPUL);
		//
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().setReuseAddress(true);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		System.out.println("服务器启动");
		
	}
	
	public void service()
	{
		while(true)
		{
			SocketChannel socketChannel = null;
			
			try{
				socketChannel = serverSocketChannel.accept();
				executorService.execute(new Handler(socketChannel));
				
			}catch(IOException e)
			{
				e.printStackTrace();
				
			}
		
			
		}
		
	}
	
	class Handler implements Runnable
	{
		private SocketChannel socketChannel = null;

		public Handler(SocketChannel socketChannel)
		{
			this.socketChannel = socketChannel;
			
		}
		public void handle(SocketChannel socketChannel)
		{
			try{
				Socket socket = socketChannel.socket();
				System.out.println("接收到连接:  "+socket.getInetAddress()+"   "+  socket.getPort());
				
				//输入输出流
				InputStream is =socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os,true);
				
				String msg = null;
				while((msg = br.readLine()) != null)
				{
					byte []receive = msg.getBytes();
					//System.out.println("收到消息 :"+ receive[0]);
					System.out.println("收到消息 :"+ msg);
					pw.println("hello");
					if(msg.equals("bye"))
						break;
						
				}
				System.out.println("跳出循环!");
				
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}finally
			{
				if(socketChannel!=null)
					try {
						System.out.println("关闭连接");
						socketChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
			}
			
			
		}
		
		@Override
		public void run() 
		{
			handle(socketChannel);
			
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		new NioServer1().service();
		
		//while(true);
	}

}
