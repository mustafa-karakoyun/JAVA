package cineM.model;


import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String director;
	private double rating;
	private String category;
	
	public Movie() {
		
	}
	// Movie.java sınıfının içindeki constructor tam olarak bu sırada OLMAK ZORUNDA:
	public Movie(String title, String category, String director, double rating) {
	    this.title = title;
	    this.category = category;
	    this.director = director;
	    this.rating = rating;
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
