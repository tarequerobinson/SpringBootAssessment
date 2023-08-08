package com.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.dao.DirectorDao;
import com.cinema.entities.Movie;

@Service
public class DirectorService {
	
	
	private DirectorDao directorDao;

	DirectorService (DirectorDao  directorDao){
		this.directorDao = directorDao;
		
		
	}
	
	
	public List <Movie> findMoviesByDirectorId (Integer person_id){
		
		return directorDao.findMoviesByDirectorId(person_id);
		
		
	}

}
