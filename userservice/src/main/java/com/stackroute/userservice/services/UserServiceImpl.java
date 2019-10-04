package com.stackroute.userservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> userOpt = userRepository.findById(user.getUserId());
		if (userOpt.isPresent()) {
			throw new UserAlreadyExistsException("User id is already present, please enter different user id.");
		}
		userRepository.save(user);

		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("User is not present in database.");
		}
		return user;
	}

}
