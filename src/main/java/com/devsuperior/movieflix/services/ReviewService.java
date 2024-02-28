package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.ReviewInsertDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	public ReviewDTO saveReview(ReviewInsertDTO dto) {
		User user = authService.authenticated();
		Review entity = new Review();
		Movie movie = new Movie();
		movie.setId(dto.getMovieId());
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}
}
