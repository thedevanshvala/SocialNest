package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {

	     User newUser = new User();
	     
	     newUser.setEmail(user.getEmail());
	     newUser.setFirstName(user.getFirstName());
	     newUser.setLastName(user.getLastName()); // Set from user, not newUser
	     newUser.setPassword(user.getPassword()); // Set from user, not newUser
	     newUser.setId(user.getId()); // Set from user, not newUser
	     
	     User savedUser = userRepository.save(newUser);
	     
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("User Not Exist with this userid "+userId);  // Return the list of users
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1; 
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);
		
		
		if(user1.isEmpty()) {
			throw new Exception("User not exist with UserId "+ userId);
		}
		
		User oldUser = user1.get(); 
		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		User updatedUser = userRepository.save(oldUser);
		
		
//		User user1 = new User(1,"Code", "Devansh", "thedevanshvala@gmail.com", "Devansh");
//		
//		if(user.getFirstName()!=null) {
//			user1.setFirstName(user.getFirstName());
//		}
//		if(user.getLastName()!=null) {
//			user1.setLastName(user.getLastName());
//		}
//		if(user.getEmail()!=null) {
//			user1.setEmail(user.getEmail());
//		}
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

}
