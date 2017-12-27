package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() throws FileNotFoundException, IOException {
		// 获取此目录下的文件夹
		File file = new File("D:/work/MyEclipse 2015 workspace");
		File files[] = file.listFiles();
		//使用POI导出文件
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table"); // 创建table工作薄
	
		HSSFRow row;
		HSSFCell cell;
		
		for (int i=0;i<files.length;i++) {
			row = sheet.createRow(i);//创建表格行
			System.out.println(files[i].getName());
			cell = row.createCell(0);//根据表格行创建单元格
			cell.setCellValue(files[i].getName());
		}
		
		wb.write(new FileOutputStream("C:/Users/Administrator/Desktop/ttt.xls"));
		
	}

}
