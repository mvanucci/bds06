package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MoviePageableDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {
		Optional<Movie> opt = movieRepository.findById(id);
		Movie entity = opt.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDetailsDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<MoviePageableDTO> findMoviesWithGenre(Long genreId, Pageable pageable){
		Long idGenre = (genreId == 0) ? null : genreId;
		Page<Movie> list = movieRepository.findMoviesWithGenre(idGenre, pageable);
		return list.map(m -> new MoviePageableDTO(m));
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsByMovie(Long movieId){
		List<Review> list = movieRepository.findReviewsByMovie(movieId);
		return list.stream().map(r -> new ReviewDTO(r)).collect(Collectors.toList());
	}
}
