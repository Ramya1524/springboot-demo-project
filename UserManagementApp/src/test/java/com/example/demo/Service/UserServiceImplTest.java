package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userservice;
	
	@Mock
	private UserRepository repo;
	
	User user;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		user = new User();
		user.setId(1);
		user.setName("ashish");
		user.setUsername("ramya2234");
		user.setAddress("bangalore street,bangalore");
		user.setEmail("ramyad24101997@gmail.com");
		user.setPhone(Long.valueOf("9945009867"));
		user.setPassword("qTyuywyyue_iu");
		user.setPan("DAEP123455");
		user.setType("savings");
		user.setState("Karnataka");
		user.setCountry("India");
		user.setDob("1997-10-28T18:30:00.000Z");
	}
	
	
	@Test
	void testGetAllUsers() throws Exception {
		List<User> usrList = new ArrayList<User>();
		usrList.add(user);
		
		when(repo.findAll()).thenReturn(usrList);
		ResponseEntity<?> users = userservice.getAllUsers();
		assertEquals(usrList,users.getBody());
		
	}

	@Test
	void testAddUser() throws Exception {
		when(repo.saveAndFlush(user)).thenReturn(user);
        ResponseEntity<?> usr = userservice.addUser(user);

       assertNotNull(usr);

       assertEquals(user, usr.getBody());
	}


	@Test
	void testFindById() {
		when(repo.getUserById(user.getId())).thenReturn(user);
		
		ResponseEntity<?> usr = userservice.findById(user.getId());
		assertEquals(user,usr.getBody());
	}

	@Test
	void testUpdateUser() throws Exception {
		User userUpdated = user;
		userUpdated.setState("Andhra pradesh");
		
		when(repo.getUserById(userUpdated.getId())).thenReturn(userUpdated);
		when(repo.save(userUpdated)).thenReturn(userUpdated);
		
		ResponseEntity<?> usr =  userservice.updateUser(userUpdated);
		assertEquals(userUpdated, usr.getBody());
	}



}
