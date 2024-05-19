package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Cart {
	
	private List<Game> games;
	
	public Double getTotal() {
		
		double total = 0.0;
		
		for (Game g : this.games) {
			total += g.getPrice();
		}
		return total;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public Cart() {
		this.games = new ArrayList<Game>();
	}

}
