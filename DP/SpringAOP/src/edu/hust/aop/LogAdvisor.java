package edu.hust.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.hust.model.User;

/*
 * ʹ��aspectjע��ʵ��aop
 * */

@Aspect
@Component
public class LogAdvisor {
	@Before("execution��public void edu.hust.service.UserService.login(User user)")
	public void before(){
		System.out.println("before method invoke");
	}
}
