package inputstream.reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.charset.Charset;

public class FileInputStreamExamp {

	
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/zhengzhenji/Desktop/list3.txt");
		FileInputStream fis = new FileInputStream(file);
		//下面的写法效果一样
		//FileInputStream fis = new FileInputStream("C:/Users/zhengzhenji/Desktop/list3.txt");
		
		byte buf[] = new byte[1024];
		//保存实际读取的字节数
		int hasread = 0;
		while(   (hasread=fis.read(buf)  )>0)
		{
			String content = new String(buf,0,hasread,"GBK");
			System.out.println(content);
		}
		fis.close();
		String defaultCharsetName=Charset.defaultCharset().displayName();   
        System.out.println("defaultCharsetName:"+defaultCharsetName);  
	}

}
