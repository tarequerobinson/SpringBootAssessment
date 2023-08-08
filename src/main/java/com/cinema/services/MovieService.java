package com.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.dao.MovieDao;
import com.cinema.entities.Movie;

@Service
public class MovieService {
	
	
	//Dependecny inversion we dont want the Movie Service to rely heavily on the implementation of the MovieDao methods 
	private final MovieDao movieDao;

	public MovieService(MovieDao movieDao) {
		this.movieDao	= movieDao;
	}
	
	
	
	public List<Movie> findByMovieTitle(String  movie_title) {
		return movieDao.findByMovieTitle(movie_title);
	}
	
	
	public void createMovie(Movie movie) {
		
		 movieDao.createMovie(movie);

	}
	
	
	
	public void deleteById(Integer movie_id) {
		
		 movieDao.deleteMovieById(movie_id);
	}
	
	
	public List<Movie> findAllMovies(int size, int offset, String sortBy, String sortOrder) {
		return  movieDao.findAllMovies( size,  offset,  sortBy,  sortOrder);
	}
	
	
	public Movie findMovieById(Integer movie_id){
		
		return  movieDao.findMovieById(movie_id);

		
	}
	
	public void updateMovieById(Movie movie ,Integer movie_id) {
		
		 movieDao.updateMovieById(movie ,movie_id);
	}



	
	
	
	

}
