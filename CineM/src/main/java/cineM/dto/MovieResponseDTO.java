package cineM.dto;

import cineM.model.Movie;

public class MovieResponseDTO {

	private Long id;
	private String title;
	private String director;
	private double rating;
	private String category;
	
	public MovieResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	// DTO'nun kendi yapıcı metodu (Constructor)
	public MovieResponseDTO(Movie movie) {
	    this.id = movie.getId();
	    this.title = movie.getTitle();
	    this.category = movie.getCategory();
	    this.director = movie.getDirector();
	    this.rating = movie.getRating();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director=director;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating=rating;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	
}
