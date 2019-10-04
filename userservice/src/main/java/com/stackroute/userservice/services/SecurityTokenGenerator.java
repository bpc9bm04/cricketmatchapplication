package com.stackroute.userservice.services;

import java.util.Map;

import com.stackroute.userservice.model.User;


public interface SecurityTokenGenerator {
	
	public Map<String, String> generateToken(User user);

}
