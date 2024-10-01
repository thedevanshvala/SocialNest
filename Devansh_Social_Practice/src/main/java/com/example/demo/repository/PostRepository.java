package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}