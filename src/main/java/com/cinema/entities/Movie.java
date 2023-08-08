package com.cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "movies")
public class Movie {
	
	
	@Id
	@Column (name = "id")
	private Integer movie_id;
	
	@Column (name = "title", nullable = false)
	private String movie_title;
	
	
	@Column (name = "year")
	private Integer movie_year;
	
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public Integer getMovie_year() {
		return movie_year;
	}
	public void setMovie_year(Integer movie_year) {
		this.movie_year = movie_year;
	}
	
	

}
