package com.cinema.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import org.springframework.jdbc.core.RowMapper;

import com.cinema.entities.Person;

public class PersonRowMapper implements RowMapper<Person> {
    
	
	@Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setPerson_name(rs.getString("name"));
        person.setPerson_birthDay(rs.getInt("birth"));

        // Map other properties accordingly
        return person;
    }
    
    public List<Person> mapRows(ResultSet rs, int rowNum) throws SQLException {
    	
    	
        List<Person> persons = new ArrayList();

        do {
           Person person = new Person();

        person.setId(rs.getInt("id"));
        person.setPerson_name(rs.getString("name"));
        person.setPerson_birthDay(rs.getInt("birth"));
        persons.add(person);
        } while (rs.next());

        // Map other properties accordingly
        return persons;
    }
}

