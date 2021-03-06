package spring.core.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Student {
	private String id;
	
	private String name;
	
	private int age;
	
	@Resource(name="courseBean")
	private Course course;
	
	public void say(){
		System.out.println("my course is "+course.getCourseName());
	}
	
	public void destroy(){
		System.out.println("destroy");
	}
	
	public Student(){
		System.out.println("Student constructor1");
	}
	
	public Student(Course course){
		System.out.println("Student constructor2");
		this.course = course;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
	
}
