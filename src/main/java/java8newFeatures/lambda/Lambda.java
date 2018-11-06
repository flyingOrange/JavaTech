package java8newFeatures.lambda;

//lambda表达式用于替换函数式接口
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
/*
 *
 	lambda表达式的一般语法
	(Type1 param1, Type2 param2, ..., TypeN paramN) -> {
		statment1;
		statment2;
		//.............
		return statmentM;
	}

	单参数语法
  		param1 -> {
  			statment1;
  			statment2;
  		//.............
  		return statmentM;
	}
 * */

public class Lambda {
	
	
	
	@Test
	//无参数
	public void lambda1() {
		//原始方法
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("run 1111");
			}
			
		});
		thread1.start();
		
		//lambda方式
		Thread thread2 = new Thread(()->System.out.println("run 2222")); 
		thread2.start();
	}
	
	@Test
	//单参数
	public void lambda2() {
		List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
		List<String> lowercaseNames2 = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
		System.out.println(lowercaseNames2);
		
	}
	
	@Test
	//两个参数
	public void lambda3() {
		List<Student> studentList = new ArrayList<Student>(){
	        {
	            add(new Student("stu1",100.0));
	            add(new Student("stu2",97.0));
	            add(new Student("stu3",96.0));
	            add(new Student("stu4",95.0));
	        }
	    };
	    //原始方式
	    Collections.sort(studentList, new Comparator<Student>() {
	        @Override
	        public int compare(Student o1, Student o2) {
	            return Double.compare(o1.getScore(),o2.getScore());
	        }
	    });
	    System.out.println(studentList);	
	    //lambda方式
	    Collections.sort(studentList,(s1,s2)-> Double.compare(s1.getScore(),s2.getScore()));
        System.out.println(studentList);
        
	}
	
	
	
	class Student{
	    private String name;
	    private Double score;
	  
	    public Student(String name, Double score) {
	        this.name = name;
	        this.score = score;
	    }
	  
	    public String getName() {
	        return name;
	    }
	  
	    public Double getScore() {
	        return score;
	    }
	  
	    public void setName(String name) {
	        this.name = name;
	    }
	  
	    public void setScore(Double score) {
	        this.score = score;
	    }
	  
	    @Override
	    public String toString() {
	        return "{"
	                + "\"name\":\"" + name + "\""
	                + ", \"score\":\"" + score + "\""
	                + "}";
	    }
	}

}
