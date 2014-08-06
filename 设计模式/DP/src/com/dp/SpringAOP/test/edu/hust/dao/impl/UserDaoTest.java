package edu.hust.dao.impl;

import java.lang.reflect.Proxy;

import org.junit.Test;

import edu.hust.advice.MyAdvice;
import edu.hust.dao.UserDao;
import edu.hust.model.User;

/*
 * ≤‚ ‘∂ØÃ¨¥˙¿Ì
 * 
 * */
public class UserDaoTest {

	@Test
	public void testAddUse() {
		UserDao userDao = new UserDaoImpl();
		
		MyAdvice handler = new MyAdvice(userDao);
		UserDao userDaoProxy = (UserDao)Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), handler);
		 
		User user = new User();
		userDaoProxy.addUser(user);
		userDaoProxy.deleteUser(user);
	}

}
