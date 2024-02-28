package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query("SELECT mv FROM Movie mv WHERE (mv.genre.id = :genreId OR :genreId IS NULL)"
			+ " ORDER BY TITLE")
	Page<Movie> findMoviesWithGenre(Long genreId, Pageable pageable);
	
	@Query("SELECT rev FROM Review rev"
			+ " LEFT JOIN Movie mov"
			+ " ON rev.movie.id = mov.id"
			+ " INNER JOIN User usr"
			+ " ON rev.user.id = usr.id "
			+ " WHERE rev.movie.id = :movieId")
	List<Review> findReviewsByMovie(Long movieId);
}