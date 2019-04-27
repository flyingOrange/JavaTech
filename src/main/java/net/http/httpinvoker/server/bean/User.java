package net.http.httpinvoker.server.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 9054136276856770530L;

	String name;
	
	long id;
	
	int age;
	
	String msg;

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", age=" + age + ", msg="
				+ msg + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
