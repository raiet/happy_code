package edu.hust.service;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.hust.dao.UserDao;
import edu.hust.model.User;

public class UserServiceTest {
	
	@Test
	public void testUserService(){
		//BeanFactory applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		// UserService userService = (UserService)applicationContext.getBean("userService");
		 UserService userService = (UserService)ctx.getBean("userService");
 
		 User user = new User();
		 user.setAge(12);
		 user.setName("raiet");
		 
		 userService.login(user);
		 
	}
	
}
