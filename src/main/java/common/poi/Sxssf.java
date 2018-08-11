package common.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

/*
1、SXSSF是限制滑动窗口中的行的访问来实现低内存的占用，注意是限制的是访问
2、滑动窗口的默认大小windowSize为100，是由SXSSFWorkbook.DEFAULT_WINDOW_SIZE定义。 
3、可new一个新的SXSSFWorkbook（int windowSize）在工作簿构建时指定窗口大小 ，例如： 
SXSSFWorkbook wb = new SXSSFWorkbook(1000); 
此时wb的滑动窗口大小为1000
4、windowSize为-1时，表示可以无限制访问。此种情况下，所有未调用flushRows()刷新的记录都可用于随机访问； 
5、SXSSF中的数据达到滑动窗口的限制数量，会产生临时文件且不会自动删除（Win和Linux的默认路径不同），通过调用dispose方法即可删除临时文件： 
SXSSFWorkbook wb = new SXSSFWorkbook(100); 
//假装有许多操作 
wb.dispose(); 
6、使用createRow()创建新行且未刷新记录的总数超过指定的窗口大小时，具有最低索引值的行将被刷新，并且不能再通过getRow()访问。 
*/
public class Sxssf {
	
	@Test
	public void testSxssfExcel() throws IOException{
		long start = System.currentTimeMillis();
		System.out.println("start");
		
		SXSSFWorkbook wb = new SXSSFWorkbook(1000);
		
		for (int sheetnum = 0; sheetnum < 20; sheetnum++) {
            Sheet sh = wb.createSheet();
            
            for(int rownum = 0; rownum < 50000; rownum++){
                Row row = sh.createRow(rownum);
                for(int cellnum = 0; cellnum < 10; cellnum++){
                    Cell cell = row.createCell(cellnum);
                    String address = new CellReference(cell).formatAsString();
                    cell.setCellValue(address);
                }
            }
        }
		
		FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\sxssf1.xlsx");
        wb.write(out);
        out.close();
        wb.dispose();
		
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒");
		
	}


}
