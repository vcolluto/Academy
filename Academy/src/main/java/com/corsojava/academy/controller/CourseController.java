package com.corsojava.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.academy.model.Course;
import com.corsojava.academy.model.Student;
import com.corsojava.academy.repository.CourseRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")		//tutte le richieste del tipo /api/*
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/courses")	//GET /api/courses
	public List<Course> getCourses() {
		return (List<Course>) courseRepository.findAll(Sort.by("name"));
	}
	
	@PostMapping("/courses")	//POST /api/courses   (CREAZIONE NUOVO Corso)
	public ResponseEntity<Object> createStudent(@RequestBody Course course) {		
		try {
			return new ResponseEntity<Object>(
				courseRepository.save(course), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}
	
	@DeleteMapping("/courses/{courseId}")  //DELETE /api/courses/3
	public void deleteCourseById(@PathVariable(value = "courseId") int courseId) {
		courseRepository.deleteById(courseId);
	}
	
}
