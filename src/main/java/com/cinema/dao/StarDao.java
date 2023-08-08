package com.cinema.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.Director;
import com.cinema.entities.Movie;

@Repository
public interface StarDao{
	//Returns all the movies a star is involved in
	List<Movie> findMoviesByStarId(Integer person_id);

}
