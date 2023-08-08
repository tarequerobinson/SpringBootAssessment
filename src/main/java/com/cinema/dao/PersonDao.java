package com.cinema.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.Person;

@Repository
public interface PersonDao {

	
	public Person getPersonById (Integer person_id);
	public List<Person> getPersonByName (String  person_name);
	//public List<Person> getAllPersons();
	public void deletePersonById (Integer  person_id);
	public void createPerson(Person person , Boolean isDirector ,Integer movie_id  );
	public void updatePersonById (Person person, Integer person_id);
	List<Person> getAllPersons(int size, int offset, String sortBy, String sortOrder);

	
}
