package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface China{
    public static final String name="Rollen";
    public static  int age=20;
    public void sayChina();
    public void sayHello(String name, int age);
}
 
class Person implements China{
    public Person() {
         this.sex = "默认1";
         System.out.println("默认构造器");
    }
    public Person(String sex,String age){
        this.sex=sex;
        System.out.println("带参数的构造器:"+sex+"  age="+age);
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void sayChina(){
        System.out.println("hello ,china");
    }
    public void sayHello(String name, int age){
        System.out.println(name+"  "+age);
    }
    private String sex;
}

public class Hello {
	 public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	        Class<?> demo1=null;
	        try{
	            demo1=Class.forName("reflect.Person");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }

	        Constructor ct = demo1.getConstructor(String.class,String.class);
	        Person p = (Person)ct.newInstance("orange","33");
	        
	        //Person p = (Person)demo1.newInstance();
	        System.out.println(p.getSex());
	        /*
	        Class<?> demo2 = Person.class;
	        try {
	        	Person p = (Person)demo2.newInstance();
	        	
	        	//System.out.println(p.getSex());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	        
	        
	        //demo2.getClassLoader();
	        Field field[] = demo1.getDeclaredFields();
	        for (int j = 0; j < field.length; j++) 
	        {
	            System.out.println("变量名:  "+field[j].getName());
	        }
	        
	        //获得接口
	        Class<?> intes[] = demo1.getInterfaces();
	        for (int i = 0; i < intes.length; i++) 
	        {
	            System.out.println("接口名:  "+intes[i].getName());
	        }
	        //获得方法
	        Method methods[] = demo1.getMethods();
	        for (int i = 0; i < methods.length; i++) 
	        {
	            System.out.println("方法名:  "+methods[i].getName());
	        }
	        */
	        
	        
	    }

}
