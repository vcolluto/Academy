package com.corsojava.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.academy.model.Course;
import com.corsojava.academy.repository.CourseRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")		//tutte le richieste del tipo /api/*
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/courses")	//GET /api/students
	public List<Course> getCourses() {
		return (List<Course>) courseRepository.findAll(Sort.by("name"));
	}
}
