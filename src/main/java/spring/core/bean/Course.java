package spring.core.bean;

public class Course {
	private String courseName;
	
	public String getCourseName() {
		return courseName;
	}
	public Course(){
		System.out.println("Course constructor");
		this.courseName = "cs";
		this.score = 90;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private int score;

}
