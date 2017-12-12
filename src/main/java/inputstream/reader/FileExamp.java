package inputstream.reader;
import java.io.File;
import java.io.IOException;
/* 
 java.io.File的使用
 */

public class FileExamp {

	public static void main(String[] args) throws IOException {
		//File file = new File(".project");
		File file = new File("C:/Users/zhengzhenji/Desktop/list.txt");
		//File file = new File("C:/Users/zhengzhenji/Desktop/");
		//文件是否存在
		System.out.println(file.exists());
		//获取文件名
		System.out.println(file.getName());
		//获取父路径
		System.out.println(file.getParent());
		//获取绝对路径
		System.out.println(file.getAbsoluteFile());
		//列出文件和路径
		String []fileList = file.list();
		for(String fileName : fileList)
		{
			System.out.println(fileName);
		}
		
		//在当前路径下创建一个临时文件
		File file2 = new File(".");
		File tmpFile = File.createTempFile("aaa",".txt",file2);
		//JVM退出时删除文件
		tmpFile.deleteOnExit();
		
		File file3 = new File("ss");
		//创建一个文件
		//file3.createNewFile();
		//创建一个目录
		//file3.mkdir();
			
		
		
	}

}
