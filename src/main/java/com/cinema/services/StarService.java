package com.cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.dao.StarDao;
import com.cinema.entities.Movie;

@Service
public class StarService {
	
	
	private StarDao starDao;

	StarService (StarDao starDao) {
		this.starDao = starDao;	
	}
	
	
	public List <Movie> findMoviesByStarId (Integer person_id) {
		return starDao.findMoviesByStarId(person_id);
		
		
	}
	
	
	

}
