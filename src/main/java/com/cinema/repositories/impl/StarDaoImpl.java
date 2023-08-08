package com.cinema.repositories.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cinema.dao.StarDao;
import com.cinema.dto.MovieRowMapper;
import com.cinema.entities.Movie;

@Service

public class StarDaoImpl implements StarDao {

	
	private JdbcTemplate jdbcTemplate;
	private MovieRowMapper movieRowMapper;

	public StarDaoImpl(JdbcTemplate jdbcTemplate , MovieRowMapper movieRowMapper) 
	{ this.jdbcTemplate = jdbcTemplate;
		this.movieRowMapper = movieRowMapper ; 
	}
	
	
	@Override
	public List<Movie> findMoviesByStarId(Integer person_id) {
		// TODO Auto-generated method stub
		
		System.out.println("Fetching all movies directed by person with id: " + person_id);
		String sql = "SELECT movies. * FROM movies "
				+ "JOIN stars ON stars.movie_id = movies.id "
				+ "WHERE stars.person_id = ?" ;
		List<Movie> movies = jdbcTemplate. query(sql , movieRowMapper ,  person_id); 
		 for (Movie movie : movies) {
		        System.out.println("Movie ID: " + movie.getMovie_id());
		        System.out.println("Title: " + movie.getMovie_title());
		        System.out.println("year: " + movie.getMovie_year());
		        // Add more fields as needed
		        System.out.println("------------------------");
		    }
		System.out.println(" all movies starred in found " );

		
		return movies;
		
	}

}
