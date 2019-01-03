package test;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() {
		
		BigDecimal num3 = BigDecimal.TEN;
		
		BigDecimal num1 = BigDecimal.ZERO;
		BigDecimal num2 = BigDecimal.ONE;
		BigDecimal num4 = BigDecimal.ONE;
		BigDecimal total = num1.add(num2).add(num4);


		String str = RandomStringUtils.randomAscii(32);
		System.out.println(str);

        
	}

}
