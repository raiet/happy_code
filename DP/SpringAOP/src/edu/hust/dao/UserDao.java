package edu.hust.dao;

import edu.hust.model.User;

public interface UserDao {
	public void addUser(User user);
	public void deleteUser(User user);
}
