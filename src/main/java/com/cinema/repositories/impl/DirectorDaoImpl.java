package com.cinema.repositories.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cinema.dao.DirectorDao;
import com.cinema.dto.MovieRowMapper;
import com.cinema.dto.UserRowMapper;
import com.cinema.entities.Director;
import com.cinema.entities.Movie;

@Repository
public class DirectorDaoImpl implements DirectorDao{
	
	private JdbcTemplate jdbcTemplate;
	private MovieRowMapper movieRowMapper;

	DirectorDaoImpl(JdbcTemplate jdbcTemplate, MovieRowMapper movieRowMapper){
		  this.jdbcTemplate= jdbcTemplate;
		  this.movieRowMapper = movieRowMapper;
		  
		  }	
	
	
	public List<Movie>findMoviesByDirectorId (Integer  person_id){
		System.out.println("Fetching all movies directed by person with id: " + person_id);
		String sql = "SELECT movies. * FROM movies "
				+ "JOIN directors ON directors.movie_id = movies.id "
				+ "WHERE directors.person_id = ?" ;
		List<Movie> movies = jdbcTemplate.query(sql , movieRowMapper ,  person_id); 
		 for (Movie movie : movies) {
		        System.out.println("Movie ID: " + movie.getMovie_id());
		        System.out.println("Title: " + movie.getMovie_title());
		        System.out.println("year: " + movie.getMovie_year());
		        // Add more fields as needed
		        System.out.println("------------------------");
		    }
		System.out.println(" all movies directed found " );

		
		return movies;
	}



}
