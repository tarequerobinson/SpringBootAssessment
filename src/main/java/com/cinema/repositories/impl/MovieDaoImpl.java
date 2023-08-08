
  package com.cinema.repositories.impl;
  
  import java.util.List;
  
  import org.springframework.dao.EmptyResultDataAccessException; 
  import org.springframework.jdbc.core.BeanPropertyRowMapper; 
  import org.springframework.jdbc.core.JdbcTemplate; 
  import org.springframework.stereotype.Repository;
  
  import com.cinema.dao.MovieDao; 
  import com.cinema.dto.MovieRowMapper; 
  import com.cinema.entities.Movie;
import com.cinema.entities.Person;
  
  @Repository public class MovieDaoImpl implements MovieDao{
  
  
  private final JdbcTemplate jdbcTemplate; 
  private final MovieRowMapper movieRowMapper;
  
  
  public MovieDaoImpl(JdbcTemplate jdbcTemplate , MovieRowMapper movieRowMapper) 
  { 
	  this.jdbcTemplate = jdbcTemplate; 
	  this.movieRowMapper =
  movieRowMapper ; 
	  }
  
  
  @Override public Movie findMovieById(Integer movie_id) {
	  
	 
  
  System.out.println("Getting movie with id: " +
  movie_id); String sql = "SELECT * FROM movies WHERE id = ?"; Movie movie =
  jdbcTemplate.queryForObject(sql, movieRowMapper, movie_id );
  System.out.println("Retrieved movie with id: " + movie_id + " and name: " +
  movie.getMovie_title()); return movie ;
  
  }
  
  
  @Override public List<Movie> findAllMovies(int size, int offset, String sortBy, String sortOrder) { 
	  
	
  try {
  
  String sql = "SELECT * FROM movies"
           + " ORDER BY " + sortBy + " " + sortOrder + " "
           + " LIMIT ? OFFSET ?";


  
  
  List<Movie> movies = jdbcTemplate.query(sql, movieRowMapper , size , offset );
  System.out.println("ALL MOVIES FOUND "); return movies; 
  } catch
  (EmptyResultDataAccessException ex) 
  
  { System.out.println("NOT FOUND ");
  return null; 
  }
  
  }
  
  
  @Override public List<Movie> findByMovieTitle(String movie_title) {

  
  String sql =
  "SELECT * FROM movies WHERE title = ? "; List<Movie> movies =
  jdbcTemplate.query(sql, movieRowMapper, movie_title );
  
  return (movies); }
  
  
  @Override public void createMovie(Movie movie) { 
	 
  
  try { System.out.println("Creating person with id: " +
  movie.getMovie_id()); String sql = "INSERT INTO movies (id, title, year)" +
  "VALUES (?, ?, ?)"; jdbcTemplate.update(sql, movie.getMovie_id(),
  movie.getMovie_title(), movie.getMovie_year()); System.out.println("Movie "+
  movie.getMovie_title() + " created successfully!"); } catch (Exception e) {
  
  System.out.println("Movie failed to be created ");
  
  
  } }
  
  @Override public void deleteMovieById(Integer id) {
  
  System.out.println("Deleting person with id: " + id); String sql =
  "DELETE FROM movies WHERE id = ?"; int deletedRows = jdbcTemplate.update(sql,
  id); System.out.println("Deleted " + deletedRows +
  " movie(s) successfully!"); }
  
  
  
  @Override public void updateMovieById(Movie movie, Integer movie_id) {
  System.out.println("Updating movie with id: " + movie_id ); String sql =
  "UPDATE movies SET title = ?, year = ? WHERE id = ?";
  jdbcTemplate.update(sql, movie.getMovie_title(), movie.getMovie_year(),
  movie_id); System.out.println("Movie with id: " + movie_id +
  " updated successfully!"); // TODO Auto-generated method stub
  
  }



  
  
  
  
  
  }
 