package outputstream.writer;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileWriterExamp {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("C:/Users/zhengzhenji/Desktop/newnnn.txt"); 
		fw.write("你好啊\n");
		fw.write("哈哈哈哈哈哈哈哈哈哈\n");
		fw.write("郑程\n");
		
		fw.close();
		String defaultCharsetName=Charset.defaultCharset().displayName();   
        System.out.println("defaultCharsetName:"+defaultCharsetName);  
		
	}

}
