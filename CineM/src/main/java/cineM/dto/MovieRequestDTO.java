package cineM.dto;

import jakarta.validation.constraints.*;

public class MovieRequestDTO {

	@NotBlank(message="Başlık boş olamaz.")
	private String title;
	@NotBlank(message="Yönetmen boş olamaz.")
	private String director;
	@Min(value = 0)
	@Max(value=10)
	private double rating;
	@NotBlank(message="The category cant be blank..")
	private String category;
	
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
