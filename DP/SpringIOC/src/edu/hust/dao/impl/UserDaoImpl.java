package edu.hust.dao.impl;

import org.springframework.stereotype.Service;

import edu.hust.dao.UserDao;
import edu.hust.model.User;
@Service
public class UserDaoImpl implements UserDao{
	
	public void addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("user is " + user.getName()+ ", " +user.getAge() + " years old");
		System.out.println("user has added!!");
	}

}
