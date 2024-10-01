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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Message;
import com.example.demo.repository.MessageRepository;


@RestController
public class MessageController {
	
	@Autowired
	MessageRepository messageRepository;
	
	@PostMapping("/Message")
	public Message createMessage(@RequestBody Message message) {
		
		Message newMessage = new Message();
		newMessage.setEmail(message.getEmail());
		newMessage.setFirstName(message.getFirstName());
		newMessage.setLastName(message.getLastName()); // Set from user, not newUser
		newMessage.setPassword(message.getPassword()); // Set from user, not newUser
		newMessage.setId(message.getId()); // Set from user, not newUser
	     
	     Message savedMessage = messageRepository.save(newMessage);
	     
	    return savedMessage;
	}
	
	@GetMapping("/Message")
	public List<Message> getMessage(){	
		List<Message> message = messageRepository.findAll();
		return message;  // Return the list of users
	}
	
	@GetMapping("/Message/{messageId}")
	public Message getMessageById(@PathVariable("msgId")Integer id) {
		
		Message msg1 = new Message(1,"Code", "Devansh", "thedevanshvala@gmail.com", "Devansh");
		msg1.setId(id);

		return msg1;  // Return the list of users
	}
	
	
	@PutMapping("/Message/{messageId}")
	public Message updateMessage(@RequestBody Message message, @PathVariable Integer messageId) throws Exception {
		
		Optional<Message> message1 = messageRepository.findById(messageId);
		
		
		if(message1.isEmpty()) {
			throw new Exception("User not exist with UserId "+ messageId);
		}
		
		Message oldMessage = message1.get(); 
		
		if(message.getFirstName()!=null) {
			oldMessage.setFirstName(message.getFirstName());
		}
		if(message.getLastName()!=null) {
			oldMessage.setLastName(message.getLastName());
		}
		if(message.getEmail()!=null) {
			oldMessage.setEmail(message.getEmail());
		}
		
		Message updatedMessage = messageRepository.save(oldMessage);
		return updatedMessage;
	}
	
	@DeleteMapping("/Message/{messageId}")
	public String deleteMessage(@PathVariable Integer messageId) throws Exception {
	Optional<Message> msg = messageRepository.findById(messageId);
		
		if(msg.isEmpty()) {
			throw new Exception("User not exist with UserId "+ messageId);
		}
		messageRepository.delete(msg.get());
			return "Message Deleted Successfully with this id " + messageId;
		
	}
	
}
