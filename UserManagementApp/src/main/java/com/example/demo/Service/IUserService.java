package com.example.demo.Service;


import org.springframework.http.ResponseEntity;

import com.example.demo.Model.User;
import com.example.demo.Model.UserDto;

public interface IUserService 
{
	public ResponseEntity<?> getAllUsers() throws Exception;
	
	public ResponseEntity<?> addUser(User user) throws Exception;
		
	public ResponseEntity<?> findById(int id) throws Exception;
	
	public  ResponseEntity<?> updateUser(User user) throws Exception;

	public ResponseEntity<?> login(UserDto user) throws Exception;

	public ResponseEntity<?> deleteById(int id) throws Exception;
}
