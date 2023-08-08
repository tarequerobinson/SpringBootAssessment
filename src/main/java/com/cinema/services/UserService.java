package com.cinema.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.dao.UserDao;
import com.cinema.entities.User;

@Service
public class UserService {
	
	
	public final UserDao userDao; 
	
	public UserService(UserDao userDao){
		this.userDao= userDao;
		
	}
	
	
	public List<User> getAllUsers () {
		
		return userDao.getAllUsers();
	}
	
	// method for deleting User OBJECT by id 
	//Get
	public User getUserById (Integer id){	
		return userDao.getUserById(id) ;
	}
	
	// method for deleting User OBJECT by username 
	//Get 
	public User getUserByUsername (String  id){	
		return userDao.getUserByUsername(id) ;
	}
	
	// method for deleting User OBJECT by Id 
	//Delete
    @Transactional

	public void deleteUserById (Integer  id){	
		 userDao.deleteUserById(id) ;
	}
	
	// method for creating User OBJECT by Id 
	//Post
    @Transactional

	public void createUser(User user ){
		userDao.createUser(user);
	}
	
	
//	// Method offering the service to update User using the userId to find the User to be updated
//	//PUT
//	//Accepts a user object and a user_id which identifies the User Object to be updated
//		public User updateUser (User user, Long user_id) {
//			User updatedUser = new User() ;
//			updatedUser.setUser_id(user.getUser_id());
//			updatedUser.setPassword(user.getPassword());
//			updatedUser.setuserName(user.getuserName());
//			updatedUser.setEmail(user.getEmail());
//			updatedUser.setRoles(user.getRoles());
//			return userDao.save(updatedUser);
//		}
	

}
