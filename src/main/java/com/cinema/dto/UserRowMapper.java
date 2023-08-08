
  package com.cinema.dto;
  
  import java.sql.ResultSet; import java.sql.SQLException; import
  java.util.List; import java.util.ArrayList;
  
  
  import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cinema.entities.User;
  
@Component
  public class UserRowMapper implements RowMapper<User> {
  
  @Override public User mapRow (ResultSet rs, int rowNum) throws SQLException {
  User user = new User(); 
  user.setPassword(rs.getString("password"));
  user.setuserName(rs.getString("user_name"));
  user.setUser_id(rs.getInt("user_id"));
  user.setRole(rs.getString("role"));
  return  user; 
  
  // Map other properties accordingly return user; 
  
  }
  
  public List<User> mapUserRows(ResultSet rs, int rowNum) throws SQLException {
  
  
  List<User> users = new ArrayList();
  
  do { User user = new User(); user.setPassword(rs.getString("password"));
  user.setuserName(rs.getString("user_name"));
  user.setUser_id(rs.getInt("user_id"));
  user.setPassword(rs.getString("role")); 
  users.add(user); 
  } while (rs.next());
  return  users; 

  // Map other properties accordingly return users; 
  
  } 
  }
  
 