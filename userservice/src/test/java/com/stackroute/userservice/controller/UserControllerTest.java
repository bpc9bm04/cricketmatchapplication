package com.stackroute.userservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.services.SecurityTokenGenerator;
import com.stackroute.userservice.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@MockBean
	private UserRepository repository;
	
	@MockBean
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	
	/*@InjectMocks
	private UserController controller;*/
	
	private User user;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setCreationDate(new Date());
		user.setFirstName("test1");
		user.setLastName("lastname");
		user.setPassword("password");
		user.setUserId("test");
	}
	
	@Test
	public void testRegisterUser() throws JsonProcessingException, Exception {
		when(userServiceImpl.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated());
		verify(userServiceImpl, times(1)).saveUser(Mockito.any(User.class));
	}
	
	@Test
	public void testLoginUser() throws JsonProcessingException, Exception {
		when(userServiceImpl.saveUser(user)).thenReturn(true);
		when(userServiceImpl.findByUserIdAndPassword("test", "password")).thenReturn(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk());
		verify(userServiceImpl, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	/**
	 * converts object into json string
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	private String jsonToString(final Object obj) throws JsonProcessingException {
		
		final ObjectMapper objectMapper = new ObjectMapper();
		final String jsonString = objectMapper.writeValueAsString(obj);
		return jsonString;
	}
}
