package net.http.httpinvoker.server.service;

import net.http.httpinvoker.server.bean.User;

public class UserServiceImpl implements UserService{

	public User getUserbyId(long id) {
		System.out.println(id);
		User user = new User();
		user.setId(id);
		user.setName("orange");
		user.setAge(12);
		user.setMsg("hello ~~ world");
		return user;
	}

}
