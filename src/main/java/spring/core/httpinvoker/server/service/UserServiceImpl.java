package spring.core.httpinvoker.server.service;

import spring.core.httpinvoker.server.bean.User;

public class UserServiceImpl implements UserService{

	@Override
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
