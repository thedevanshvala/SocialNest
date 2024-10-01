package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
	     User savedUser = userService.registerUser(user);
	    return savedUser;
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {  // Change return type to List<User>
		List<User> User = userRepository.findAll();
		
		return User;  // Return the list of users
	}
	@GetMapping("/users/{userId}")
	public User getUsersById(@PathVariable("userId")Integer id) throws Exception {  // Change return type to List<User>
		
		User UserById = userService.findUserById(id);
		
		return UserById;
	}
	
	@PutMapping("/Users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
		
		User updatedUser = userService.updateUser(user, userId);
		return updatedUser;
		
	}
	
	@DeleteMapping("/Users/{userId}")
	public String deleteUser(@PathVariable Integer userId) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new Exception("User not exist with UserId "+ userId);
		}
		userRepository.delete(user.get());
		
			return "User Deleted Successfully with this id " + userId;
	}
	@PutMapping("users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
		
		User user = userService.followUser(userId1, userId2);
		
		return user;
	}

	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		
		return users;
		
	}
}
