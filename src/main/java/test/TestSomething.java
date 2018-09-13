package test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() throws ParseException{
		
	
//		System.out.println(System.getProperty("java.version"));
//		int stime = 20180716;
//      Date sttime = FastDateFormat.getInstance("yyyyMMdd").parse(String.valueOf(stime));
//      System.out.println(sttime);
		
		BigDecimal total = BigDecimal.ZERO;
		
		BigDecimal num1 = BigDecimal.TEN;
		BigDecimal num2 = BigDecimal.ONE;
		
		total = total.add(num1);
		total = total.add(num2);
		
		System.out.println(total);
        
        
	}

}
