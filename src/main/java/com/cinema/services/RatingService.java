package com.cinema.services;

import org.springframework.stereotype.Service;

import com.cinema.dao.RatingDao;
import com.cinema.entities.Rating;

@Service
public class RatingService {
	
	RatingDao ratingDao;
	public RatingService(RatingDao ratingDao) {
		this.ratingDao	= ratingDao;
	}

	

	
	public Rating  getRatingByMovieId (Integer movie_id) {
		
		return ratingDao.getRatingByMovieId(movie_id) ;
		
	}

}
