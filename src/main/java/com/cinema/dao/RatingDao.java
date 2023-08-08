package com.cinema.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinema.entities.Rating;
import com.cinema.entities.User;


@Repository
public interface RatingDao {
	public Rating getRatingByMovieId (Integer movie_id);

}
