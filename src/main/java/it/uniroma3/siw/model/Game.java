package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	
	@NotBlank
	private String name;
	
	private String description;
	
	private List<String> images;
	
	@NotNull
    @Min(1980)
    @Max(2024)
	private Integer releaseDate;
	
	private Double price;
	
	@ManyToMany
	private List<Category> categories;
	
	@ManyToOne
	private Developer developer;
	
	@OneToMany(mappedBy = "game")
	private List<Review> reviews;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getreleaseDate() {
		return releaseDate;
	}

	public void setreleaseDate(Integer releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Iterable<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Iterable<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Game() {
		
	}
	
	
	
	
}
