package common.beanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class UseBeanUtils {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//这里要求Student类必须是public class
		//设置属性
		Student student1 = new Student();
		BeanUtils.setProperty(student1, "name", "哈哈");
		BeanUtils.setProperty(student1, "age", 122);
		BeanUtils.setProperty(student1, "birth", new Date());
		System.out.println(student1);
		
		//获取属性
		Student student2 = new Student("张三",22,new Date());
		String name = BeanUtils.getProperty(student2, "name");
		String age = BeanUtils.getProperty(student2, "age");
		String birth = BeanUtils.getProperty(student2, "birth");
		System.out.println("name="+name+",age="+age+",birth="+birth);
		
		//属性拷贝
		Student student3 = new Student("李四",18,new Date());
		Student student4 = new Student();
        BeanUtils.copyProperties(student4, student3);
        System.out.println(student4);
        
        //把一个map集合中的数据拷贝到javaBean中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "orange");
        map.put("age", 100);
        map.put("birth", new Date());

        Student student5 = new Student();
        BeanUtils.copyProperties(student5, map);
        System.out.println(student5);
		

	}

}
