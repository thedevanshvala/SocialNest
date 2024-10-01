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

import com.example.demo.models.Stories;

import com.example.demo.repository.StoriesRepository;

@RestController
public class StoriesController {
	@Autowired
	StoriesRepository storiesRepository;
	
	@PostMapping("/Stories")
	public Stories createStories(@RequestBody Stories stories) {
		
		Stories newStories = new Stories();
		
		newStories.setEmail(stories.getEmail());
		newStories.setFirstName(stories.getFirstName());
		newStories.setLastName(stories.getLastName()); // Set from user, not newUser
		newStories.setPassword(stories.getPassword()); // Set from user, not newUser
		newStories.setId(stories.getId()); // Set from user, not newUser
	     
	     Stories savedReels = storiesRepository.save(newStories);
		
		return savedReels;
	}

	@GetMapping("/Stories")
	public List<Stories> getStories(){
		List<Stories> S1 = storiesRepository.findAll();
		return S1;  // Return the list of users
	}
	
	@GetMapping("/Stories/{storiesId}")
	public Stories getStories(@PathVariable("storiesId")Integer id){
		
		Stories Stories1 = new Stories(4,"Code111", "Reels", "thedevanshvala@gmail.com", "Devil");
		Stories1.setId(id);
		
		return Stories1;  // Return the list of users
	}
	
	@PutMapping("/Stories/{storiesId}")
	public Stories updateReels(@RequestBody Stories stories, @PathVariable Integer storiesId) throws Exception {
		
Optional<Stories> story1 = storiesRepository.findById(storiesId);
		
		
		if(story1.isEmpty()) {
			throw new Exception("User not exist with UserId "+ storiesId);
		}
		
		Stories oldStory = story1.get(); 
		
		if(stories.getFirstName()!=null) {
			oldStory.setFirstName(stories.getFirstName());
		}
		if(stories.getLastName()!=null) {
			oldStory.setLastName(stories.getLastName());
		}
		if(stories.getEmail()!=null) {
			oldStory.setEmail(stories.getEmail());
		}
		
		Stories updatedStories = storiesRepository.save(oldStory);
		return updatedStories;
	}
	
	@DeleteMapping("/Stories/{storiesId}")
	public String deleteStories(@PathVariable Integer storiesId) throws Exception {
		Optional<Stories> story = storiesRepository.findById(storiesId);
		
		if(story.isEmpty()) {
			throw new Exception("User not exist with UserId "+ storiesId);
		}
		storiesRepository.delete(story.get());
		
			return "Stories Deleted Successfully with this id " + storiesId;
		
	}
	
}
