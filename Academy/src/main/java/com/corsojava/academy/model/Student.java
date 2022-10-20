package com.corsojava.academy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	//@Column(name="id")
	//private int idStudente;  //il nome della colonna (DB) corrisponde al nome dell'attributo quindi  serve mettere l'annotazione @Column
	  
	@Id					//indica che questo attributo identifica univocamente un'istanza della classe
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//indica che l'id viene generato tramite autoincrement	
	private int id;		//il nome della colonna (DB) corrisponde al nome dell'attributo quindi non serve mettere l'annotazione @Column
	  
	private String firstName;
	private String lastName;
	  
	@Column(nullable=false)
	private String email;

	@ManyToOne
	@JoinColumn(name="course_id", nullable = true)
	private Course course;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	


}
