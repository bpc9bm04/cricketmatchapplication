package com.stackroute.userservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.services.UserServiceImpl;

public class UserServiceImplTest {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	private Optional<User> options;
	
	private User user;
	
	@Before	
	public void setup() {
		user = new User();
		user.setCreationDate(new Date());
		user.setFirstName("test");
		user.setLastName("lastname");
		user.setPassword("password");
		user.setUserId("test");
		MockitoAnnotations.initMocks(this);
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUser() throws UserAlreadyExistsException {
		when(repository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("cannot save user", true, flag);
		verify(repository, times(1)).save(user);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUSerFailure() throws UserAlreadyExistsException {
		when(repository.findById(user.getUserId())).thenReturn(options);
		when(repository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
	}
	
	@Test
	public void testValidateSucces() throws UserNotFoundException {
		when(repository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User object =userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(object);
		verify(repository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(repository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
		User object = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
