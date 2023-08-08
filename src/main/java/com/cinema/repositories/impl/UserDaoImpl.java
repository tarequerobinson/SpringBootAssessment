
  package com.cinema.repositories.impl;
  
  import java.util.List;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper; import
  org.springframework.jdbc.core.JdbcTemplate; import
  org.springframework.stereotype.Repository;
  
  import com.cinema.dao.UserDao; import com.cinema.entities.User; import
  org.springframework.transaction.annotation.Transactional; import
  com.cinema.dto.UserRowMapper ;
  
  
  
  @Repository
  
  public class UserDaoImpl implements UserDao {
  
  
  public final JdbcTemplate jdbcTemplate; public final UserRowMapper
  userRowMapper;
  
  UserDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper){
  this.jdbcTemplate= jdbcTemplate; this.userRowMapper = userRowMapper;
  
  }
  
  public List<User> getAllUsers(){
  
  String sql = "SELECT * FROM users_table"; 
  List<User> users = jdbcTemplate.query(sql, userRowMapper); return users ; }
  
  @Override public User getUserById(Integer user_id) { 
	  
	
  String sql = "SELECT * FROM users_table" +"WHERE user_id = ? ";
  User user = jdbcTemplate.queryForObject(sql, userRowMapper, user_id);   
  return (user); }
  
  @Override public User getUserByUsername(String user_name) { 
	  
	  String sql = "SELECT * FROM users_table" +" WHERE user_name = ? "; 
	  User user =  jdbcTemplate.queryForObject(sql, userRowMapper, user_name); // return users ;
  
	  return (user); 
  
  }
  
  @Override
  
  @Transactional
  public void deleteUserById(Integer user_id) {
	  

  
  String sql = "DELETE FROM users_table WHERE user_id = ?";
  jdbcTemplate.update(sql, user_id);
  System.out.println("User deleted successfully!");
  
  }
  
  @Override
  
  @Transactional
  
  public void createUser(User user) { // TODO Auto-generated method stub
  
  String sql = "INSERT INTO users_table (user_name , password , role)" +
  "VALUES (?, ?, ?)"; 
  jdbcTemplate.update(sql, user.getUsername(), user.getPassword() , user.getRole()); 
  System.out.println("User " + user.getUsername()+ " with the role of " + user.getRole() + "created successfully!");
  
  }
  
  @Override
  
  @Transactional
  
  public void updateUser(User user, Integer user_id) {
  
  String sql =
  "UPDATE users_table SET user_name = ?, password = ?, role = ? WHERE user_id = ?"
  ;
  
  jdbcTemplate.update(sql, user.getUsername(), user.getPassword(),
  user.getRole(), user.getUser_id());
  System.out.println("User updated successfully!");
  
  }
  
  
  
  
  }
 