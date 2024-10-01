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

import com.example.demo.models.Post;
import com.example.demo.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping("/Post")
	public Post createPost(@RequestBody Post post) {
		
		Post newPost = new Post();
		
		newPost.setEmail(post.getEmail());
		newPost.setFirstName(post.getFirstName());
		newPost.setLastName(post.getLastName()); // Set from user, not newUser
		newPost.setPassword(post.getPassword()); // Set from user, not newUser
		newPost.setId(post.getId()); // Set from user, not newUser
	     
	     Post savedPost = postRepository.save(newPost);
		
		return savedPost;
	}
	
	@GetMapping("/Post")
	public List<Post> getPost() {
		List<Post> Post = postRepository.findAll();
		return Post;  // Return the list of users
	}
	@GetMapping("/Post/{postId}")
	public Post getPostById(@PathVariable("postId")Integer id) {
		
		Post post1 = new Post(1,"Code", "Devansh", "thedevanshvala@gmail.com", "Devansh");
		post1.setId(id);

		return post1;  // Return the list of users
	}
	
	@PutMapping("/Post/postId")
	public Post updatePost(@RequestBody Post post, @PathVariable Integer postId) throws Exception {
		
		Optional<Post> post1 = postRepository.findById(postId);
		
		
		if(post1.isEmpty()) {
			throw new Exception("User not exist with UserId "+ postId);
		}
		
		Post oldPost = post1.get(); 
		
		if(post.getFirstName()!=null) {
			oldPost.setFirstName(post.getFirstName());
		}
		if(post.getLastName()!=null) {
			oldPost.setLastName(post.getLastName());
		}
		if(post.getEmail()!=null) {
			oldPost.setEmail(post.getEmail());
		}
		
		Post updatedPost = postRepository.save(oldPost);
		return updatedPost;
	}
	
	@DeleteMapping("/Post/{postId}")
	public String deletePost(@PathVariable Integer postId) throws Exception {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty()) {
			throw new Exception("User not exist with UserId "+ postId);
		}
		postRepository.delete(post.get());
			return "Post Deleted Successfully with this id " + postId;
		
	}

}
