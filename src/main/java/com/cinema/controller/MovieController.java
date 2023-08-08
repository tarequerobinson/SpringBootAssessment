package com.cinema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import com.cinema.entities.Movie;
import com.cinema.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private MovieService movieService;

	MovieController (MovieService movieService ) {
		this.movieService = movieService;
		
	}
	
    @GetMapping ("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(defaultValue = "11") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
){
    	
    	List<Movie> movies = movieService.findAllMovies(size, page, sortBy, sortOrder);
        return ResponseEntity.ok(movies);
	
    }
    
    
    
    @GetMapping ("/getMovieByName/{movie_name}")
    public ResponseEntity<List<Movie>> getMovieByName(@PathVariable String movie_name) throws JsonProcessingException{
    	List<Movie> movies = movieService.findByMovieTitle(movie_name);
        return ResponseEntity.ok(movies);

	
    }
    
    
    @GetMapping ("/getMovieById/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) throws JsonProcessingException{
        //ObjectMapper objectMapper = new ObjectMapper();
    	Movie movie = movieService.findMovieById(id);
       // Gson gson = new Gson();
     //  String json = objectMapper.writeValueAsString(movie);
        
        return ResponseEntity.ok(movie);

     //   return ResponseEntity.ok().body(json);
	
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/createNewMovie")
	public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
    	
    	movieService.createMovie(movie);
		 return ResponseEntity.ok("Movie successfully created!");
    	
    }
    
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping ("/deleteMovieById/{id}")
  	public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
      		movieService.deleteById(id);
  		 return ResponseEntity.ok("Movie successfully deleted!");
      	
      }
    
    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping ("/updateMovieById/{id}")
  	public ResponseEntity<String> updateMovieById(@PathVariable Integer id ,  @RequestBody Movie movie ) {
      	movieService.updateMovieById(movie , id);
  		 return ResponseEntity.ok("Movie successfully updated!");
      	
      }



}
