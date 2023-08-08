package com.cinema.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.Director;
import com.cinema.entities.Movie;

@Repository
public interface DirectorDao{
	
	
	
	//Returns all the movies a director is involved in
	List<Movie> findMoviesByDirectorId(Integer person_id);

	

}
