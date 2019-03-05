package common.apache.commons.fileUtils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileUtilsUsage {

	@Test
	/*
	 * 读写文件
	 */
	public void readWriteFile() throws Exception {
		/*
		 * write(File file, CharSequence data) 将指定的数据(data)写入到指定的文件(file)中
		 * file必须是文件，是目录时抛异常 file整个路径可以不存在，即使父目录不再，不再时也会自动全部新建
		 * 这种方式写入时，如果文件已经存在了，则先删除之前的内容，然后在重新写入，即完全覆盖，而不是内容追加
		 * 
		 * writeLine()、writeLiness()
		 */
		File file = new File("D:\\Technology\\Books\\javaweb\\haha.txt");
		String context = "{\"name\":\"BeJson帅\",\"url\":\"http://www.bejson.com\"page\":88,\"isNonProfit\":true}";
		FileUtils.write(file, context, true);

		//把文件读取到字符串
		String read = FileUtils.readFileToString(file, "UTF-8");
		System.out.println(read);
		
		//把文件读取到字节数组里面
		byte[] bytes = FileUtils.readFileToByteArray(file);

	}

	@Test
	/*
	 * 复制文件或文件夹方法
	 */
	public void CopyFileorDirectory() throws Exception {
		File file1 = new File("D:\\Technology\\Books\\javaweb\\list.txt");
		File file2 = new File("D:\\Technology\\Books\\javaweb\\test");
		File file3 = new File("D:\\Technology\\Books\\javaweb\\haha.txt");
		File file4 = new File("D:\\Technology\\Books\\javaweb\\newtest");
		File file5 = new File("D:\\Technology\\Books\\javaweb\\newtest2");
		// 如果不存在，则创建目标目录。如果目标文件存在，则该方法将覆盖它。
		FileUtils.copyFileToDirectory(file1, file2);// 文件不重命

		// 将文件复制到一个新的地方(重命名文件)并保存文件日期的时间。
		FileUtils.copyFile(file1, file3);

		// 复制文件夹到指定目录下,如果指定目录不存在则创建
		FileUtils.copyDirectoryToDirectory(file2, file4);

		// 复制文件夹到指定目录下并重命名
		FileUtils.copyDirectory(file4, file5);

	}

	@Test
	/*
	 * 删除文件或文件
	 */
	public void FileorDirectoryDelete() throws Exception {
		File file = new File("D:\\Technology\\Books\\javaweb\\list.txt");
		File directory = new File("D:\\Technology\\Books\\javaweb\\test");
		// 递归删除一个目录(包括内容)。
		FileUtils.deleteDirectory(directory);

		// 删除一个文件，不会抛出异常。如果文件是一个目录，删除它和所有子目录。
		FileUtils.deleteQuietly(file);

		// 清理内容而不删除它。
		FileUtils.cleanDirectory(directory);

		// 删除一个文件，会抛出异常
		// 如果file是文件夹，就删除文件夹及文件夹里面所有的内容。如果file是文件，就删除。
		// 如果某个文件/文件夹由于某些原因无法被删除，会抛出异常
		FileUtils.forceDelete(file);
	}

	@Test
	/*
	 * 创建目录
	 */
	public void CreatDirectory() throws Exception {
		File file = new File("D:\\Technology\\Books\\javaweb\\list.txt");
		// 创建一个文件夹，如果由于某些原因导致不能创建，则抛出异常
		// 一次可以创建单级或者多级目录
		FileUtils.forceMkdir(new File("D:/Technology/Books/javaweb/inner/haha"));
		// 为指定文件创建文件的父级目录
		FileUtils.forceMkdirParent(file);
	}

	@Test
	/*
	 * 移动文件或文件夹
	 */
	public void moveFileOrDirectory() throws Exception {
		File file = new File("D:\\Technology\\Books\\javaweb\\");
		// 移动文件夹,并重新命名
		FileUtils.moveDirectory(new File("/Users/Downloads/file1"), new File("/Users/Downloads/file2/file3"));

		// 移动文件夹，并给定是否重命名
		FileUtils.moveDirectoryToDirectory(new File("/Users/Downloads/file1"), new File("/Users/Downloads/file2/"),
				false);

		// 移动文件到指定文件夹中,并重新命名
		FileUtils.moveFile(file, new File("/Users/Downloads/海葡萄.jpen"));

		// 移动文件到指定文件夹中，并给定是否创建文件夹
		FileUtils.moveFileToDirectory(new File("/Users/Downloads/海葡萄.jpeg"), new File("/Users/Downloads/file2"), false);

	}

}
