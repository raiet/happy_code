package edu.hust.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import edu.hust.dao.UserDao;
import edu.hust.model.User;
@Service
public class UserService {
	//自动装配注解（默认是byType，根据类型进行自动的注入）
	//@Autowired，建议写在set方法上

	private UserDao userDao;
	public UserService(){}
	//使用构造函数注入的时候必须有这个构造函数
	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	//@Autowired
	//resource是默认byName
	@Resource
	//@resoure(name = "hh")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void login(User user){
		System.out.println("i am login");
		userDao.addUser(user);
	}
}
