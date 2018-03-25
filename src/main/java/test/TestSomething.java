package test;

import java.util.UUID;

import org.junit.Test;

public class TestSomething {

	@Test
	public void test() {
		System.out.println(UUID.randomUUID());
	}

	// @Test
	public void generateCode() {
		for (int i = 9060; i <= 9080; i++) {
			System.out.print("\"" + i + "\"");
			System.out.println(":\"" + UUID.randomUUID() + "\",");
		}
	}

}
