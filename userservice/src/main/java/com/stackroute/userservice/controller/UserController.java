package com.stackroute.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.services.SecurityTokenGenerator;
import com.stackroute.userservice.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping(path="/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("{\"message:\"" + "\"User created.\"", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}

	@PostMapping(path="/login")
	public ResponseEntity<?> login(@RequestBody User loginDetails) {

		try {
			User user = userService.findByUserIdAndPassword(loginDetails.getUserId(), loginDetails.getPassword());
			if (user == null) {
				String errMessage = "{\"message:\"" + "\"User not found\"";
				return new ResponseEntity<String>(errMessage, HttpStatus.UNAUTHORIZED);
			}
			String dbPasword = user.getPassword();
			if (!loginDetails.getPassword().equals(dbPasword)) {
				String errMessage = "{\"message:\"" + "\"Invalid login credentials.\"";
				return new ResponseEntity<String>(errMessage, HttpStatus.UNAUTHORIZED);
			}
			
			Map<String, String> tokenMap = securityTokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(tokenMap, HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("{\"message:\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}

}
