package com.example.demo.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Model.UserDto;
import com.example.demo.Service.IUserService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("auth")
public class AuthenticationController 
{	
	
	
	@Autowired
	private IUserService iuserService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) throws Exception
	{
		
		return iuserService.login(user);
		
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) throws Exception
	{	
		return iuserService.addUser(user);
	
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
				
		return iuserService.updateUser(user);
			
	}
	
	@GetMapping("/user/getuser/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) throws Exception{
		
		return iuserService.findById(id);
		
	}
	
	@GetMapping("/user/getAllUsers")
	public ResponseEntity<?> getAllUsers() throws Exception{
		
		return iuserService.getAllUsers();
	}
	
	@DeleteMapping("/user/deleteById/{id}")
	public ResponseEntity<?> deleteUserByID(@PathVariable int id) throws Exception{
		
		return iuserService.deleteById(id);
	}
	
}
	

