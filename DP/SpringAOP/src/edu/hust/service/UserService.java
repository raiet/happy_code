package edu.hust.service;


import org.springframework.stereotype.Component;

import edu.hust.dao.UserDao;
import edu.hust.model.User;

@Component
public class UserService {
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void login(User user){
		System.out.println("i am login");
		userDao.addUser(user);
	}
	
}
