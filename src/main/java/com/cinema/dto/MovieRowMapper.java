package com.cinema.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.cinema.entities.Movie;
import java.util.ArrayList;

public class MovieRowMapper implements RowMapper<Movie> {
	
   
    

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	
        
        Movie movie = new Movie();
        movie.setMovie_id(rs.getInt("id"));
        movie.setMovie_title(rs.getString("title"));
        movie.setMovie_year(rs.getInt("year"));

        // Map other properties accordingly
        return movie;
    		
    		
    }

    
    public List<Movie> mapRows(ResultSet rs, int rowNum) throws SQLException {

    	List<Movie> movies = new ArrayList<>();
    	do {
    		
    		Movie movie = new Movie();
            movie.setMovie_id(rs.getInt("id"));
            movie.setMovie_title(rs.getString("title"));
            movie.setMovie_year(rs.getInt("year"));
            movies.add(movie);
    		
    	}
    	while (rs.next()); 
     	
    	return movies;
    }
    	
    
    
    
    }


