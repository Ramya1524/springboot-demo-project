package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserDto;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.JWTUtil;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
	private Map<String, String> mapObj=new HashMap<String, String>();
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JWTUtil Jutil;
	
	
	
	public User validateUser(String username, String password) throws Exception {
		User user= userRepo.validateUser(username, password);
		return user;
	}
	
	@Override
	public ResponseEntity<?> login(UserDto user) throws Exception {
		
		try
		{
			User u = validateUser(user.getUsername(), user.getPassword());
			if(u!= null)
			{
				String jwtToken = Jutil.generateToken(user);
				mapObj.put("message", "User successfully logged in");
				mapObj.put("userId", Integer.toString(u.getId()));
				mapObj.put("token", jwtToken);
				return new ResponseEntity<>(mapObj, HttpStatus.OK);
			}else {
				throw new ServletException("Invalid credentials");
			}
			
			
		}catch(Exception e) {
			throw e;
		}
	}
		
	@Override
	public ResponseEntity<?> getAllUsers() throws Exception
	{
		try {
			List<User> userList = userRepo.findAll();
			if(userList != null & userList.size()>0)
			{
				return ResponseEntity.status(HttpStatus.OK).body(userList);
			}
			 
			mapObj.put("message", "No users Exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mapObj);
		}
		catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseEntity<?> addUser(User user) throws Exception {
		try {
			User userExist= userRepo.validateUser(user.getUsername(), user.getPassword());
			
			if(userExist!=null){
				mapObj.put("message", "Try with different Username and Password");
				return ResponseEntity.status(HttpStatus.OK).body(mapObj);
			}
			else{
				
				User userAdded = userRepo.saveAndFlush(user);
				return ResponseEntity.status(HttpStatus.CREATED).body(userAdded);
				}
			}
		catch(Exception e) {
			throw e;
		}
		
	}


	@Override
	public ResponseEntity<?> findById(int id) {
		
		try {
			User user = userRepo.getUserById(id);
			if(user!= null) {
				return ResponseEntity.status(HttpStatus.OK).body(user);
			}
			mapObj.put("message", "User dosent exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapObj);
			}
		catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public  ResponseEntity<?> updateUser(User user) throws Exception {
		
		try {
			User existUser = userRepo.getUserById(user.getId());
			
			if(existUser!= null) {
				User updatedUser = userRepo.save(user);
				return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
			}
			return new ResponseEntity<String>("User Profile updation failed", HttpStatus.CONFLICT);
		}
		catch(Exception e) {
			throw e;
		}

	}

	@Override
	public ResponseEntity<?> deleteById(int id) throws Exception {
		
		try {
			User userExist =  userRepo.getUserById(id);
			if(userExist!= null) {
				userRepo.deleteById(id);
				mapObj.put("message", "User Deleted Successfully");
				return ResponseEntity.status(HttpStatus.OK).body(mapObj);
			}
			mapObj.put("message", "User Dosent Exist");
			return ResponseEntity.status(HttpStatus.OK).body(mapObj);
			
		}
		catch(Exception e) {
			throw e;
		}
		
	}

	public UserDetails getByUsername(String username) {
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.add(authority);
		
		User user = userRepo.getByUsername(username);
		 return new org.springframework.security.core.userdetails.User(user.getUsername(),
	                user.getPassword(), 
	                updatedAuthorities);
	                
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
