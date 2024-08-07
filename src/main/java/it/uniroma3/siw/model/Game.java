package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Long id;
	
	@NotBlank
	public String name;
	
	public String description;
	
	
	public String copertina;
	
	@NotNull
    @Min(1980)
    @Max(2024)
	public Integer releaseDate;
	
	public Double price;
	
	public String category;
	
	private List<String> images = new ArrayList<>();
	
	@ManyToOne
	public Developer developer;
	
	@OneToMany(mappedBy = "game",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Review> reviews;
	
	

	public Game() {
		this.images = new ArrayList<String>();
		this.reviews = new ArrayList<Review>();
		
	}

	public List<String> getImages() {
	    return images;
	}

	public void setImages(List<String> images) {
	    this.images = images;
	}

	public void addImages(String imagePath) {
	    this.images.add(imagePath);
	}
	
	public Integer getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Integer releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

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

	

	public String getCopertina() {
		return copertina;
	}

	public void setCopertina(String copertina) {
		this.copertina = copertina;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
}
