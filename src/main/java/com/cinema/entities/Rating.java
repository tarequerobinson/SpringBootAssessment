package com.cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "ratings")
public class Rating {
	
	@Id
	@Column(name = "movie_id") 
	private Long movie_id;
	
	@Column(name = "rating") 
	private Double rating;
	
	@Column(name = "votes") 
	private Integer votes;
	
	public Long getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Integer getVotes() {
		return votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}

}
