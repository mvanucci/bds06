package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MoviePageableDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
		MovieDetailsDTO dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<MoviePageableDTO>> findMovieWithGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable
			){
		Page<MoviePageableDTO> list = movieService.findMoviesWithGenre(genreId, pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviewsByMovie(@PathVariable Long id){
		List<ReviewDTO> list = movieService.findReviewsByMovie(id);
		return ResponseEntity.ok().body(list);
	}
}
