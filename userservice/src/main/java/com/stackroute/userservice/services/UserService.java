package com.stackroute.userservice.services;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	public boolean saveUser(User user) throws UserAlreadyExistsException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	
}
