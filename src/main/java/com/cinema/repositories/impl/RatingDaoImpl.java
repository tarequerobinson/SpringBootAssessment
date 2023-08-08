
  package com.cinema.repositories.impl;
  
  import java.util.List;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper; import
  org.springframework.jdbc.core.JdbcTemplate; import
  org.springframework.stereotype.Repository;

import com.cinema.dao.RatingDao;
import com.cinema.dao.UserDao;
import com.cinema.entities.Rating;
import com.cinema.entities.User; import
  org.springframework.transaction.annotation.Transactional; import
  com.cinema.dto.UserRowMapper ;
  
  
  
  @Repository
  
  public class RatingDaoImpl implements RatingDao {
  
  
  public final JdbcTemplate jdbcTemplate; 
  public final UserRowMapper userRowMapper;
  
  RatingDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper){
  this.jdbcTemplate= jdbcTemplate; this.userRowMapper = userRowMapper;
  
  }
  
  
  

@Override
public Rating getRatingByMovieId (Integer movie_id) {
	// TODO Auto-generated method stub
	
	  String sql = "SELECT * FROM ratings WHERE movie_id = ? "; 
	  
	  Rating rating = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Rating.class) , movie_id ); 
	  return rating;

}

  
  
  
  
  }
 