package test;

import java.math.BigDecimal;

import org.junit.Test;

public class TestSomething {

	@Test
	public void readFileNames() {
		
		BigDecimal total = BigDecimal.ZERO;
		
		BigDecimal num1 = BigDecimal.TEN;
		BigDecimal num2 = BigDecimal.ONE;
		
		total = total.add(num1);
		total = total.add(num2);
		
		System.out.println(total);
        
        
	}

}
