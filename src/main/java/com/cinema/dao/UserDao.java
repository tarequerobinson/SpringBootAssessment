package com.cinema.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.User;


@Repository
public interface UserDao {
	public List<User> getAllUsers ();
	public User getUserById (Integer id);
	public User getUserByUsername (String  id);
	public void deleteUserById (Integer  id);
	public void createUser(com.cinema.entities.User user );
	public void updateUser (com.cinema.entities.User user, Integer id) ;

}
