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
	//�Զ�װ��ע�⣨Ĭ����byType���������ͽ����Զ���ע�룩
	//@Autowired������д��set������

	private UserDao userDao;
	public UserService(){}
	//ʹ�ù��캯��ע���ʱ�������������캯��
	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	//@Autowired
	//resource��Ĭ��byName
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
