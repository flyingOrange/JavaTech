package generic;

import org.junit.Test;

public class TestCase {

	@Test
	public void GenericClass() {
		GenericClass<Integer> gc = new GenericClass<Integer>(111);
		System.out.println(gc.getKey());
		gc.say();
		
	}
}
