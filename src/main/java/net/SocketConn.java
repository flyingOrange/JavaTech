package net;
import java.io.*;
import java.net.*;

public class SocketConn {


	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = null;
		OutputStream os;
		InputStream is;
		
		try {
			socket = new Socket("localhost",6666);
			
			os = socket.getOutputStream();
			os.write(49);
			is = socket.getInputStream();
				
			
			byte[] buf = new byte[1024];
			
			while(is.read(buf)!=-1)
			{
				System.out.println(new String(buf));
				
				String returnStr = new String("你们好啊a！哈哈".getBytes(),"UTF-8");
				os.write(returnStr.getBytes("UTF-8"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}

}
