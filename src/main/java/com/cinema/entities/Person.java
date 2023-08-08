package com.cinema.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "people")
public class Person {
	

	@Id
	@Column(name = "id")
	private Integer id ; 

	
	
	@Column(name = "name",nullable = false)
	private String person_name;
	
	@Column(name = "birth")
	private Integer person_birthDay;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public Integer getPerson_birthDay() {
		return person_birthDay;
	}

	public void setPerson_birthDay(Integer person_birthDay) {
		this.person_birthDay = person_birthDay;
	}
	
	
	
}
