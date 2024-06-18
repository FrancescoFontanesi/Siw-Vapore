package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
public class Review {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@NotNull
    @Min(0)
    @Max(5)
	public Integer rating;
	
	public String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    public Game game;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return text;
	}

	public void setReview(String review) {
		this.text = review;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Review() {
		super();
	}

	public Review(String text, Integer rating, Game game) {
		this.text = text;
		this.rating = rating;
		this.game = game;
	}
	
}
