package com.corsojava.academy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.corsojava.academy.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
	public List<Student> findByLastName(String lastName);
	
}
