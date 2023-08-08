package com.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.dao.PersonDao;
import com.cinema.entities.Person;

@Service
public class PersonService {
	
	 
	private final PersonDao personDao;

	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public List<Person> getAllPersons(int size , int offset , String sortBy , String sortOrder ) {
        return personDao.getAllPersons(size , offset , sortBy, sortOrder);
    }

    public Person getPersonById(Integer id) {
        return personDao.getPersonById(id);
    }

    
    @Transactional

    public void createPerson(Person person, Boolean isDirector , Integer movie_id) {
        personDao.createPerson(person, isDirector , movie_id);
    }

    @Transactional

    public void updatePersonById(Person person, Integer person_id) {
        personDao.updatePersonById(person , person_id );
    }

    public List<Person> getPersonByName(String person_name) {
        return personDao.getPersonByName(person_name);
    }
    
    
    
    @Transactional


    public void deletePersonById(Integer person_id) {
        personDao.deletePersonById(person_id);
    }
	
	

	
	
	
	
	
	
	
	
	
	

}
