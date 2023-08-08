package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.entities.Person;
import com.cinema.services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	
	    private final PersonService personService;

	    public PersonController(PersonService personService) {
	        this.personService = personService;
	    }

	    
	    @GetMapping ("/getAllPersons")
	    public ResponseEntity<List<Person>> getAllPersons( 
	    		@RequestParam(defaultValue = "1") int offset,
	            @RequestParam(defaultValue = "100") int size,
	            @RequestParam(defaultValue = "name") String sortBy,
	            @RequestParam(defaultValue = "asc") String sortOrder){
	    	
	    	List<Person> persons = personService.getAllPersons(size, offset, sortBy, sortOrder);
	    	
	    	//To avoid getting null values when querying the database
	    	if (!persons.isEmpty()) {
	            // If persons list is not empty, return it with status 200 (OK)
	    		
	            return ResponseEntity.ok(persons);
	        } else {
	            // If persons list is empty, return 404 (NOT FOUND) with an appropriate message
	            return ResponseEntity.notFound().build();
	        }
	    	
	      //  return ResponseEntity.ok(persons);

	    }
	    
	    @PostMapping ("/createNewPerson")
		public ResponseEntity<String> createNewPerson(@RequestBody Person person , @RequestParam Boolean isDirector ,@RequestParam Integer movie_id) {
	    //	try {

	    	personService.createPerson(person , isDirector ,  movie_id );
			 return ResponseEntity.ok("Person successfully created!");}
	    	//catch ( Exception e) {
	      //      return ResponseEntity.notFound().build();

	    	//}
	    	

	    	
	    //}
	    
	    @GetMapping  ("/getPersonById/{person_id}")
	    public ResponseEntity<Person> getPersonById(@PathVariable  Integer person_id) throws JsonProcessingException{
	    	
	    	Person person = personService.getPersonById(person_id);
		        return ResponseEntity.ok(person);
	    		
	    }
	    
	    // I used <List<Person>> as the the return value as there may be more than one person with the same name
	    @GetMapping  ("/getPersonByName/{person_name}")
	    public ResponseEntity<List<Person>> getPersonByName(@PathVariable  String person_name) throws JsonProcessingException{
	    	
	    	List<Person> persons = personService.getPersonByName(person_name);

	    	if (!persons.isEmpty()) {

		        return ResponseEntity.ok(persons);
		        
	    	}
	    	else {
	    		
	            return ResponseEntity.notFound().build();

	    		
	    	}
	    		
	    }

	    
	    @DeleteMapping  ("/deletePersonById/{id}")
		public ResponseEntity<String> deletePersonById (@PathVariable Integer  id) {
			personService.deletePersonById(id);
			 return ResponseEntity.ok("Person successfully deleted!");
		}
		
	    

	    @DeleteMapping  ("/updatePersonById/{id}")

		public ResponseEntity<String> deletePersonById ( @RequestBody Person person , @PathVariable Integer  id ) {
	    	
				personService.updatePersonById( person , id);
			 return ResponseEntity.ok("Person successfully deleted!");

		}


	


}
