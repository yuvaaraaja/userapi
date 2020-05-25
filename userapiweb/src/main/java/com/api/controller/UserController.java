package com.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.dao.UserDAO;
import com.api.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAO  userDAO ;
	
	@RequestMapping(value = UserRestURIConstants.DUMMY_USER, method = RequestMethod.GET)
	public @ResponseBody User getDummyUser(HttpServletResponse response) throws IOException {
		User user ;
		if(userDAO.listByID("dummy") != null) {
			user = userDAO.listByID("dummy");
		}
		else {
			user = new User();
			user.setUser_id("dummy");
			user.setFirstname("firstname");
			user.setLastname("lastname");
			userDAO.save(user);
		}
		return user;
	}
	
	@RequestMapping(value = UserRestURIConstants.GET_USER, method = RequestMethod.GET)
	public @ResponseBody User getUserById(@PathVariable("user_id") String user_id) {
		return userDAO.listByID(user_id);
	} 
	
	@RequestMapping(value = UserRestURIConstants.GET_ALL_USER, method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		return userDAO.listAll();
	}
	
	@RequestMapping(value = UserRestURIConstants.CREATE_USER, method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user) {
		userDAO.save(user);
		return user;
	}
	
	@RequestMapping(value = UserRestURIConstants.UPDATE_USER, method = RequestMethod.PUT)
	public @ResponseBody User updateUser(@RequestBody User newUser) {
		User user = userDAO.listByID(newUser.getUser_id());
		user.setFirstname((newUser.getFirstname() == null || newUser.getFirstname().isEmpty()) ? user.getFirstname() : newUser.getFirstname());
		user.setLastname((newUser.getLastname() == null || newUser.getLastname().isEmpty()) ? user.getLastname() : newUser.getLastname());
		userDAO.update(user);
		return userDAO.listByID(newUser.getUser_id());
	}
	
	@RequestMapping(value = UserRestURIConstants.DELETE_USER, method = RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@PathVariable("user_id") String user_id) {
		User user = userDAO.listByID(user_id);
		userDAO.delete(user_id);
		return user;
	}
	
}
