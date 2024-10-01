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

import com.example.demo.models.Reels;
import com.example.demo.repository.ReelsRepository;

@RestController
public class ReelsController {
	@Autowired
	ReelsRepository reelsRepository;
	
	@PostMapping("/Reels")
	public Reels createReels(@RequestBody Reels reels) {
		
		Reels newReels = new Reels();
		
		newReels.setEmail(reels.getEmail());
		newReels.setFirstName(reels.getFirstName());
		newReels.setLastName(reels.getLastName()); // Set from user, not newUser
		newReels.setPassword(reels.getPassword()); // Set from user, not newUser
		newReels.setId(reels.getId()); // Set from user, not newUser
	     
	     Reels savedReels = reelsRepository.save(newReels);
		
		return savedReels;
	}
	
	@GetMapping("/Reels")
	public List<Reels> getReels(){
		List<Reels> R1 = reelsRepository.findAll();

		return R1;  // Return the list of users
	}
	
	@GetMapping("/Reels/{reelsId}")
	public Reels getReels(@PathVariable("msgId")Integer id){
		
		Reels Reels1 = new Reels(4,"Code111", "Reels", "thedevanshvala@gmail.com", "Devil");
		Reels1.setId(id);
		
		return Reels1;  // Return the list of users
	}
	
	@PutMapping("/Reels/{reelsId}")
	public Reels updateReels(@RequestBody Reels reels, @PathVariable Integer reelsId) throws Exception {
		
		Optional<Reels> reels1 = reelsRepository.findById(reelsId);
		
		
		if(reels1.isEmpty()) {
			throw new Exception("User not exist with UserId "+ reelsId);
		}
		
		Reels oldReels = reels1.get(); 
		
		if(reels.getFirstName()!=null) {
			oldReels.setFirstName(reels.getFirstName());
		}
		if(reels.getLastName()!=null) {
			oldReels.setLastName(reels.getLastName());
		}
		if(reels.getEmail()!=null) {
			oldReels.setEmail(reels.getEmail());
		}
		
		Reels updatedReels = reelsRepository.save(oldReels);
		return updatedReels;
	}
	
	@DeleteMapping("/Reels/{reelsId}")
	public String deleteReels(@PathVariable Integer reelsId) throws Exception {
		
		Optional<Reels> reels = reelsRepository.findById(reelsId);
		
		if(reels.isEmpty()) {
			throw new Exception("User not exist with UserId "+ reelsId);
		}
		reelsRepository.delete(reels.get());
		
			return "Reel Deleted Successfully with this id " + reelsId;
		
	}

}
