package inputstream.reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileReaderExamp {

	
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/zhengzhenji/Desktop/list3.txt");
		FileInputStream fis = new FileInputStream(file);
		FileReader fr = new FileReader(file);
		//FileReader fr = new FileReader("C:/Users/zhengzhenji/Desktop/list3.txt");
		
		char buf[] = new char[32];
		//保存实际读取的字节数
		int hasread = 0;
		while(   (hasread=fr.read(buf)  )>0)
		{
			String content = new String(buf,0,hasread);
			System.out.println(content);
		}
		fr.close();
		
		String defaultCharsetName=Charset.defaultCharset().displayName();   
        System.out.println("defaultCharsetName:"+defaultCharsetName);  
		
		
	}

}
