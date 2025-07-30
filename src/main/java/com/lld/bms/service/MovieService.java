package com.lld.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.bms.exceptions.MovieNotFoundException;
import com.lld.bms.models.Movie;
import com.lld.bms.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie saveMovie(Movie Movie) {
		return movieRepository.save(Movie);
	}
	
	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}
	
	public Movie getMovieById(int MovieId) {
		
		return movieRepository.findById(MovieId).orElseThrow(() -> new MovieNotFoundException("Movie with id " +MovieId+ " not found"));
	}
	
	public void deleteMovieById(int MovieId) {
		movieRepository.deleteById(MovieId);
	}

}
