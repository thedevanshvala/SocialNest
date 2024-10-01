package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Stories;

public interface StoriesRepository extends JpaRepository<Stories, Integer> {

}
