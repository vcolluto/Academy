package com.corsojava.academy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "studentId") int studentId) {
		Optional<Student> s=studentRepository.findById(studentId);
		if (s.isPresent())
			return new ResponseEntity<Student> (s.get(),HttpStatus.OK);		//ritorno l'istanza di studente
		else
			return new ResponseEntity<Student> ((Student) null, HttpStatus.NOT_FOUND); //ritorno l'oggetto vuoto
	}
	
	@DeleteMapping("/student/{studentId}")  //DELETE /api/student/3
	public void deleteStudentbyId(@PathVariable(value = "studentId") int studentId) {
		studentRepository.deleteById(studentId);
	}
	
	@PostMapping("/students")	//POST /api/students   (CREAZIONE NUOVO STUDENTE)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(
				studentRepository.save(student), HttpStatus.CREATED);
	}
	
	@PutMapping("/students/{studentId}")  //PUT /api/students/3
	public ResponseEntity<Student>  updateStudent(
			@PathVariable(value = "studentId") int studentId,
			@RequestBody Student student) {
		
		Optional<Student> s=studentRepository.findById(studentId);
		if (s.isPresent()) {
			Student s1=s.get();
			s1.setFirstName(student.getFirstName());
			s1.setLastName(student.getLastName());
			s1.setEmail(student.getEmail());
			s1.setCourse(student.getCourse());
			return new ResponseEntity<Student> (studentRepository.save(s1),HttpStatus.OK);
		} else
			return new ResponseEntity<Student> ((Student) null, HttpStatus.NOT_FOUND);
		
	}
}
