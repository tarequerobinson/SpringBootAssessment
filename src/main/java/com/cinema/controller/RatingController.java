package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.entities.Movie;
import com.cinema.entities.Rating;
import com.cinema.services.DirectorService;
import com.cinema.services.RatingService;
import com.cinema.services.StarService;

@RequestMapping ("/rating")
@RestController
public class RatingController {
	

	private RatingService ratingService;

	RatingController ( RatingService ratingService){
	this.ratingService = ratingService ;
	}
	
	@GetMapping ("/findMovieRatingByMovieId/{movie_id}")
	public ResponseEntity<Rating> findMoviesByStarId (@PathVariable Integer movie_id) {
		Rating rating = ratingService.getRatingByMovieId(movie_id);
        return ResponseEntity.ok(rating);

	}


}
