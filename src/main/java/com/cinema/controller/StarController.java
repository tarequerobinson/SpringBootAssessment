package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.entities.Movie;
import com.cinema.services.DirectorService;
import com.cinema.services.StarService;

@RequestMapping ("/stars")
@RestController
public class StarController {
	

	private StarService starService;

	StarController ( StarService starService){
	this.starService = starService ;
	}
	
	@GetMapping ("/findMoviesByStarId/{person_id}")
	public ResponseEntity<List<Movie>> findMoviesByStarId (@PathVariable Integer person_id) {
		List <Movie> movies = starService.findMoviesByStarId(person_id);
        return ResponseEntity.ok(movies);

	}


}
