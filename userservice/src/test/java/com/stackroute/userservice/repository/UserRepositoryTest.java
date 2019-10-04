package com.stackroute.userservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setup() {
		user = new User();
		user.setCreationDate(new Date());
		user.setFirstName("test");
		user.setLastName("lastname");
		user.setPassword("password");
		user.setUserId("test");
	}
	
	@Test
	public void testSaveUser() {
		userRepository.save(user);
		
		Optional<User> object = userRepository.findById(user.getUserId());
		assertThat(object.equals(user));
	}
}
