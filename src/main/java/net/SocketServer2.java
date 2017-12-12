package net;
import java.io.*;
import java.net.*;
/*
 * 多线程处理连接(Bufferedreader readline)
 * 
 * */
public class SocketServer2 {

	public static void main(String[] args) {
		ServerSocket  serverSocket = null;
		try {
			serverSocket = new  ServerSocket(6666);
			Socket socket = null;
			while(true)
			{
				socket = serverSocket.accept();
				Thread workThread=new Thread(new Handler2(socket));  //创建一个工作线程
		        workThread.start();  //启动工作线程
				System.out.println("新连接！");
			}	
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
	}

}

class Handler2 implements Runnable {
	private Socket socket;

	public Handler2(Socket socket) {
		this.socket = socket;
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	private InputStream getInputStream(Socket socket) throws IOException {
		return socket.getInputStream();
	}

	private OutputStream getOutputStream(Socket socket) throws IOException {
		return socket.getOutputStream();
	}

	public String echo(String msg) {
		return "echo:" + msg;
	}

	public void run() {
		try {
			System.out.println("New connection accepted "
					+ socket.getInetAddress() + ":" + socket.getPort());
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);
			InputStream is = getInputStream(socket);
			OutputStream os = getOutputStream(socket);
			
			//String msg = null;
			byte []buf = new byte[100];
			int n;
			while ( (n= is.read(buf))!=-1) 
			{
				String msg = new String(buf,0,n,"GBK");
				System.out.println("字符串长度:"+msg.length()+"|收到字节数:"+n);
				System.out.println("buf="+buf.length+"|"+msg.trim());
				//pw.println(echo(msg));
				
				System.out.println((char)buf[0]);
				System.out.println((char)buf[1]);
				System.out.println((char)buf[2]);
				System.out.println((char)buf[3]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
					System.out.println("关闭连接");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
