package com.cinema.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.Movie;
import com.cinema.entities.Person;

@Repository
public interface MovieDao{
	
	public List<Movie> findByMovieTitle(String  movie_title);
	public void createMovie(Movie movie);
	public void deleteMovieById(Integer movie_id);
	public void updateMovieById(Movie movie ,Integer movie_id);

	List<Movie> findAllMovies(int size, int offset, String sortBy, String sortOrder);
	Movie findMovieById(Integer movie_id);


}
