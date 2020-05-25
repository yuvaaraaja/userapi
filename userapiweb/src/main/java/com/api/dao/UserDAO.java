package com.api.dao;

import java.util.List;

import com.api.model.User;

public interface UserDAO {
	
	public void save(User u);
	
	public void update(User u);
	
	public List<User> listAll();
	
	public User listByID(String user_id);
	
	public void delete(String user_id);

}
