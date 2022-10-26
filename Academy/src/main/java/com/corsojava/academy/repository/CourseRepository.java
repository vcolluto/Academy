package com.corsojava.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corsojava.academy.model.Course;


@Repository
public interface CourseRepository  extends JpaRepository<Course, Integer>{
	
}
