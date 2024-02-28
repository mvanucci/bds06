package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class ReviewInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String text;
	private Long movieId;
	
	public ReviewInsertDTO() {}
	
	public ReviewInsertDTO(String text, Long movieId) {
		super();
		this.text = text;
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
