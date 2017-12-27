package net;
import java.io.*;
import java.net.*;
/*
 * 多线程处理连接(Bufferedreader readline)
 * 
 * */
public class SocketServer {

	public static void main(String[] args) {
		ServerSocket  serverSocket = null;
		try {
			serverSocket = new  ServerSocket(6666);
			Socket socket = null;
			while(true)
			{
				socket = serverSocket.accept();
				Thread workThread=new Thread(new Handler(socket));  //创建一个工作线程
		        workThread.start();  //启动工作线程
				System.out.println("新连接！");
			}	
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
	}

}

class Handler implements Runnable{
	  private Socket socket;
	  public Handler(Socket socket){
	    this.socket=socket;
	  }
	  private PrintWriter getWriter(Socket socket)throws IOException{
	    OutputStream socketOut = socket.getOutputStream();
	    return new PrintWriter(socketOut,true);
	  }
	  private BufferedReader getReader(Socket socket)throws IOException{
	    InputStream socketIn = socket.getInputStream();
	    return new BufferedReader(new InputStreamReader(socketIn));
	  }
	  public String echo(String msg) {
	    return "echo:" + msg;
	  }
	  public void run(){
	    try {
	      System.out.println("New connection accepted " +
	      socket.getInetAddress() + ":" +socket.getPort());
	      BufferedReader br =getReader(socket);
	      PrintWriter pw = getWriter(socket);

	      String msg = null;
	      while ((msg = br.readLine()) != null)
	      {
	        //System.out.println(msg+",字符串长度"+msg.length());
	        String temp = new String(msg.getBytes(),"GBK");
	        
	    	System.out.println("temp="+temp);
	        byte []buf = msg.getBytes();
	        System.out.println("字节数组长度:"+buf.length);
	        System.out.println(Integer.toHexString(buf[0]));
	        System.out.println(Integer.toHexString(buf[1]));
	        System.out.println(Integer.toHexString(buf[2]));
	        System.out.println(Integer.toHexString(buf[3]));
	        //pw.println(echo(msg));
	        
	        
	          
	      }
	    }catch (IOException e) {
	       e.printStackTrace();
	    }finally {
	       try{
	         if(socket!=null)
	         {
	        	socket.close();
	        	System.out.println("关闭连接");
	         }
	         
	       }catch (IOException e) {e.printStackTrace();}
	    }
	  }
	  
	}
