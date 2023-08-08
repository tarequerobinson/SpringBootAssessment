package com.cinema.repositories.impl;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.dao.PersonDao;
import com.cinema.dto.PersonRowMapper;
import com.cinema.entities.Person;

@Repository 
public class PersonDaoImpl implements PersonDao {
	
	 private final JdbcTemplate jdbcTemplate;
	 
	 private final PersonRowMapper personRowMapper ;

	    public PersonDaoImpl(DataSource dataSource , PersonRowMapper personRowMapper ) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	        this.personRowMapper = personRowMapper;
	    }

	    
	    @Override

	   public List<Person>  getAllPersons (int size, int offset, String sortBy, String sortOrder){
	        try
	        {
	        	String sql = "SELECT * FROM people "
	 	               + " ORDER BY " + sortBy + " " + sortOrder + " "
	                   + " LIMIT ? OFFSET ?";

	        
	        List<Person> persons = jdbcTemplate.query(sql, personRowMapper , size , offset);
	    	System.out.println("ALL PERSONS FOUND ");
	        return persons ;
	        }
	        
	        catch (EmptyResultDataAccessException ex) {
	        	System.out.println("NOT FOUND ");
	        	return null;
	        }
   
	   }
	    @Override
	public Person getPersonById (Integer id) {
	    System.out.println("Getting user with id : " + id);
        String sql = "SELECT * FROM people WHERE id = ? ";
        Person person = jdbcTemplate.queryForObject(sql, personRowMapper, id);
        System.out.println("User found : "+person.getPerson_name() + person.getPerson_birthDay() + person.getId());
		return person;


	}
	    
	    @Override
	public  List<Person> getPersonByName (String  person_name) {
	    	String sql = "SELECT * FROM people WHERE name = ?";
	    	 List<Person> persons = jdbcTemplate.query(sql, personRowMapper, person_name);
			return persons;	
	}
	
	
	    @Override
	    @Transactional

	public void deletePersonById (Integer  id) {
	        System.out.println("Deleting person with id: " + id);
	        String sql = "DELETE FROM people WHERE id = ?";
	        int deletedRows = jdbcTemplate.update(sql, id);
	        System.out.println("Deleted " + deletedRows + " person(s) successfully!");		
	}
	    
	    
	    private boolean movieExists(int movieId) {
	        String sql = "SELECT COUNT(*) FROM movies WHERE id = ?";
	        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, movieId);
	        return count > 0;
	    }

	
	    @Override
	    @Transactional
	    
	    //isDirector will tell if the person being created is a star or director. If isDirector is True then 
	    //the person is saved to the 
	public void createPerson(Person person , Boolean isDirector , Integer movie_id  ) {
	    	if (movieExists(movie_id)) {
	    	if (isDirector = true) {
	    			System.out.println("Creating person with id: " + person.getId() + "and name:" + person.getPerson_name());
	    			String sql = "INSERT INTO people ( id, name, birth)"
	    						+ " VALUES ( ? , ?, ?) ";
	    			jdbcTemplate.update(sql,person.getId() , person.getPerson_name(), person.getPerson_birthDay());
	    			System.out.println(person.getPerson_name() + "created successfully!");
	    			
	    	        String directorSql = "INSERT INTO directors (person_id, movie_id) VALUES (?, ?)";
	    	        jdbcTemplate.update(directorSql, person.getId(),  movie_id );
	    	        System.out.println("Director information saved successfully!");

	    	}
	    	else {
	    		
	    		
	    		System.out.println("Creating person with id: " + person.getId() + "and name:" + person.getPerson_name());
    			String sql = "INSERT INTO people ( id, name, birth)"
    						+ " VALUES ( ? , ?, ?) ";
    			jdbcTemplate.update(sql,person.getId() , person.getPerson_name(), person.getPerson_birthDay());
    			System.out.println(person.getPerson_name() + "created successfully!");
    			
    	        String directorSql = "INSERT INTO stars (person_id, movie_id) VALUES (?, ?)";
    	        jdbcTemplate.update(directorSql, person.getId(),  movie_id );
    	        System.out.println("Star information saved successfully!");
	    		
	    		
	    		
	    	}
	    	
	    	
	    	}
	    	else {
	    		
    	        System.out.println("Failed to save person .. movie does not exist!");
	    	}
	
	}
	
	@Override
    @Transactional

	public void updatePersonById (Person person, Integer id) {
	    System.out.println("Updating person with id: " + person.getId());
        String sql = "UPDATE people SET name = ?, birth = ? WHERE id = ?";
        jdbcTemplate.update(sql, person.getPerson_name(), person.getPerson_birthDay(), id);
        System.out.println("Person with id:" + person.getId() + "updated successfully!");
	}
//	
//	// select all fields from the stars tabel and movies table
//	// then join the movies table with the stars table using the movieId field 
//	//then join the directors table with the movies table using movieId
//	// then thw Where ensures that the records returned are such that the star's personId or director's personId i sthe same as the 
//	//person id passed as a parameter 
//	public void getPersonAndMoviesInvolvedIn(Long person_id) {
//	    String sql = "SELECT s.*, m.* "
//	               + "FROM stars s "
//	               + "INNER JOIN movies m ON s.movie_id = m.movie_id "
//	               + "INNER JOIN directors d ON m.movie_id = d.movie_id "
//	               + "WHERE s.person_id = ? OR d.person_id = ?";
//	    jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class), person_id, person_id);
//	    System.out.println("Successfully retrieved person and movie information!");
//	}
//	
    //List<Person> getPersonAndMoviesInvolvedIn(Long personId, int size, int offset, String sortBy, String sortOrder);

	public Person getPersonAndMoviesInvolvedIn(Integer personId, int size, int offset, String sortBy, String sortOrder) {
	    String sql = "SELECT s.*, m.* "
	               + "FROM stars s "
	               + "LEFT JOIN movies m ON s.movie_id = m.movie_id "
	               + "WHERE s.person_id = ? "
	               + "UNION "
	               + "SELECT d.*, m.* "
	               + "FROM directors d "
	               + "LEFT JOIN movies m ON d.movie_id = m.movie_id "
	               + "WHERE d.person_id = ? "
	               + "ORDER BY " + sortBy + " " + sortOrder + " "
                   + "LIMIT ? OFFSET ?";

	     Person person = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Person.class), personId, personId,sortBy, sortOrder,size, offset  );
		 System.out.println("Successfully retrieved person and movie information!");

	     return person;
	     
	}


	



	
	
	
	
	/*
	 * @Autowired PersonRepo personRepo;
	 * 
	 * // method for deleting Person OBJECT by id //Get public Optional<Person>
	 * getPersonById (Long person_id){ return personRepo.findById(person_id) ; }
	 * 
	 * // method for deleting Person OBJECT by username //Get public
	 * Optional<Person> getPersonByName (String person_name){ return
	 * personRepo.findByPersonName(person_name) ; }
	 * 
	 * // method for deleting Person OBJECT by Id //Delete public void
	 * deletePersonById (Long person_id){ personRepo.deleteById(person_id) ; }
	 * 
	 * // method for creating Person OBJECT //Post public void createPerson(Person
	 * person ){ personRepo.save(person); }
	 * 
	 * 
	 * // Method offering the service to update User using the userId to find the
	 * User to be updated //PUT //Accepts a user object and a user_id which
	 * identifies the User Object to be updated public Person updateUser (Person
	 * person, Long person_id) { Person updatedPerson = new Person() ;
	 * updatedPerson.setPerson_birthDay(person.getPerson_birthDay());
	 * updatedPerson.setPerson_name(person.getPerson_name());
	 * updatedPerson.setPerson_id(person.getPerson_id());
	 * 
	 * return personRepo.save(updatedPerson); }
	 */
}
