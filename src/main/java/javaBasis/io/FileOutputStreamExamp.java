package javaBasis.io;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileOutputStreamExamp {

	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("C:/Users/zhengzhenji/Desktop/newnew.txt");
		//byte []buf = new byte[32];
		byte []buf = {'a','b','h','d','b'};
		
		fos.write(buf);
		
		fos.close();
		String defaultCharsetName=Charset.defaultCharset().displayName();   
        System.out.println("defaultCharsetName:"+defaultCharsetName);  
		
	}

}
