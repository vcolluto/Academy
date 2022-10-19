package com.corsojava.academy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.academy.model.Student;
import com.corsojava.academy.repository.StudentRepository;

@RestController
@RequestMapping("/api")		//tutte le richieste del tipo /api/*
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")	//GET /api/students
	public List<Student> getStudents() {
		return (List<Student>) studentRepository.findAll();
	}
	
	@GetMapping("/studentsbylastname")	//GET /api/studentsbylastname?lastname=Rossi
	public List<Student> getStudentsByLastName(@RequestParam(name = "lastname",required = false) String lastName) {
		if (lastName==null)
			return (List<Student>) studentRepository.findAll();
		else
			return (List<Student>) studentRepository.findByLastName(lastName);
	}
	
	@GetMapping("/student/{studentId}")  //GET /api/student/3
	public Student getStudentById(@PathVariable(value = "studentId") int studentId) {
		Optional<Student> s=studentRepository.findById(studentId);
		if (s.isPresent())
			return s.get();		//ritorno l'istanza di studente
		else
			return null; //ritorno l'oggetto vuoto
	}
}
