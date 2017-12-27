package reflect;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Properties;

public class TestReflect {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
//		Class<?> t= Class.forName("java.lang.String");
//		Constructor ct = t.getConstructor(String.class);
//		String ss = (String) ct.newInstance("GBK");
//		System.out.println(ss);
		
		FileInputStream fs = new FileInputStream("src/test.properties");
		Properties p = new Properties();
		p.load(fs);
		String path = p.getProperty("path");
		String isReal = p.getProperty("isReal");
		  
		System.out.println(path+"|"+isReal);
	}

}
