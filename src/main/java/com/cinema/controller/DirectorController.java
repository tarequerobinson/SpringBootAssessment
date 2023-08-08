package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.entities.Movie;
import com.cinema.services.DirectorService;

@RestController
@RequestMapping ("/directors")
public class DirectorController {
	
	
	private DirectorService directorService;

	DirectorController ( DirectorService DirectorService){
	this.directorService = DirectorService ;
	}
	
	@GetMapping ("/findMoviesByDirectorId/{person_id}")
	public ResponseEntity<List<Movie>> findMoviesByDirectorId (@PathVariable Integer person_id) {
		List <Movie> movies = directorService.findMoviesByDirectorId(person_id);
        return ResponseEntity.ok(movies);

	}

}
