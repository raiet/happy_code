package edu.hust.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.hust.model.User;

public class UserServiceTest {

	@Test
	public void test() {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService userService = (UserService)ctx.getBean("userService");
		
		
		User user = new User();
		user.setAge(10);
		user.setName("raiet007");
		userService.login(user);
		
		
	}

}
